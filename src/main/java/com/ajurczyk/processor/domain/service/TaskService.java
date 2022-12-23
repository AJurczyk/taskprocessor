package com.ajurczyk.processor.domain.service;

import com.ajurczyk.processor.common.NotFoundException;
import com.ajurczyk.processor.domain.Task;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.ajurczyk.processor.domain.Status.IN_PROGRESS;
import static com.ajurczyk.processor.domain.Status.NEW;

@Service
public class TaskService {

    private final BestMatchService bestMatchService;

    private final List<Task> tasks = new ArrayList<>();

    private final ExecutorService executorService = Executors.newFixedThreadPool(100);

    public TaskService(BestMatchService bestMatchService) {
        this.bestMatchService = bestMatchService;
    }

    public UUID createTask(String pattern, String input) {
        Task newTask = Task.builder()
                        .uuid(UUID.randomUUID())
                        .pattern(pattern)
                        .input(input)
                        .status(NEW)
                        .progress(0)
                        .build();
        tasks.add(newTask);
        return newTask.getUuid();
    }

    @Scheduled(fixedRate = 5000)
    public void pool() {
        executorService.submit(() -> tasks.stream()
                        .filter(task -> task.getStatus().equals(NEW))
                        .forEach(task -> {
                            task.setStatus(IN_PROGRESS);
                            bestMatchService.match(task);
                        }));
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getById(UUID taskId) {
        return tasks.stream()
                        .filter(e -> e.getUuid().equals(taskId))
                        .findFirst()
                        .orElseThrow(() -> new NotFoundException("Task with id %s not found".formatted(taskId)));
    }
}
