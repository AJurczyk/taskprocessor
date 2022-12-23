package com.ajurczyk.processor.common;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private final ErrorCode errorCode = ErrorCode.RESOURCE_NOT_FOUND;
    private final String details;

    public NotFoundException(String details) {
	    super(ErrorCode.RESOURCE_NOT_FOUND.getMessage());
        this.details = details;
    }
}
