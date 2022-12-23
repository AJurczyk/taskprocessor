package com.ajurczyk.processor.common;

import lombok.Getter;

@Getter
public enum ErrorCode {
    //    500 http
    INTERNAL_SERVER("Internal server error"),

    //    400 http
    BAD_REQUEST("Bad request"),

    //    404 http
    RESOURCE_NOT_FOUND("Resource not found");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
