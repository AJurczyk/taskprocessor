package com.ajurczyk.processor.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TaskResult {
    private volatile String match;

    private volatile Integer position;

    private volatile Integer typos;
}
