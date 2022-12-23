package com.ajurczyk.processor.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Task {
    private UUID uuid;
    private String pattern;
    private String input;
    private volatile Status status;
    private volatile Integer progress;

    private TaskResult result;
}
