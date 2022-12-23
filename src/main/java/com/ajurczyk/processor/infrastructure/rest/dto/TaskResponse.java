package com.ajurczyk.processor.infrastructure.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskResponse {
    private UUID uuid;
    // TODO: 20/12/2022 enum
    private String match;
    private String status;
    private Integer position;
    private Integer typos;

    // TODO: 20/12/2022 chyba usunac
    public TaskResponse(UUID uuid) {
        this.uuid = uuid;
    }
}
