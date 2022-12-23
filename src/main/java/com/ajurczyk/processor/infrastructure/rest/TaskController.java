package com.ajurczyk.processor.infrastructure.rest;

import com.ajurczyk.processor.domain.Task;
import com.ajurczyk.processor.domain.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/tasks", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public UUID createTask(@RequestParam String pattern,
                           @RequestParam String input) {
        return taskService.createTask(pattern, input);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{task-id}")
    public Task getTaskById(@PathVariable("task-id") UUID taskId) {
        return taskService.getById(taskId);
    }
}
