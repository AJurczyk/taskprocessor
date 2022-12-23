package com.ajurczyk.processor.domain.service;

import com.ajurczyk.processor.common.BadRequestException;
import com.ajurczyk.processor.common.InterruptedTaskException;
import com.ajurczyk.processor.domain.Status;
import com.ajurczyk.processor.domain.Task;
import com.ajurczyk.processor.domain.TaskResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ajurczyk.processor.domain.Status.DONE;

@AllArgsConstructor
@Service
@Slf4j
public class BestMatchService {

    public void match(Task task) {
        log.info("Task " + task.getUuid() + " started");
        if (task.getPattern().length() > task.getInput().length()) {
            task.setStatus(Status.FAILED);
            throw new BadRequestException("Pattern cannot be longer than input");
        }
        List<String> strings = splitString(task.getInput(), task.getPattern().length());
        int bestExactMatch = 0;
        TaskResult result = new TaskResult();
        for (int i = 0; i < strings.size(); i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                task.setStatus(Status.FAILED);
                throw new InterruptedTaskException("Something went wrong");
            }
            int progres = calculateProgress(strings.size(), i);
            log.info("Task " + task.getUuid() + ". Progress: " + progres + "%");
            task.setProgress(progres);
            int exactMatch = 0;
            for (int j = 0; j < task.getPattern().length(); j++) {
                if (strings.get(i).charAt(j) == task.getPattern().charAt(j)) {
                    exactMatch++;
                }
            }
            if (bestExactMatch < exactMatch) {
                bestExactMatch = exactMatch;
                result.setPosition(i);
                result.setTypos(task.getPattern().length() - exactMatch);
            }
        }
        task.setProgress(100);
        task.setStatus(DONE);
        if (bestExactMatch > 0) {
            result.setMatch(strings.get(result.getPosition()));
            task.setResult(result);
        }
        log.info("Task " + task.getUuid() + " finished. Result: " + result);
    }

    private int calculateProgress(int size, int currentItem) {
        return Math.round(((float) currentItem / (float) size) * 100);
    }

    private List<String> splitString(String text, int length) {
        List<String> results = new ArrayList<>();
        for (int i = 0; i + length <= text.length(); i++) {
            results.add(text.substring(i, i + length));
        }
        return results;
    }
}
