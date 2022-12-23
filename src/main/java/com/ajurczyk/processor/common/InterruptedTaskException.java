package com.ajurczyk.processor.common;

import lombok.Getter;

@Getter
public class InterruptedTaskException extends RuntimeException {
    private final ErrorCode errorCode = ErrorCode.INTERNAL_SERVER;
    private final String details;

    public InterruptedTaskException(String details) {
	    super(ErrorCode.INTERNAL_SERVER.getMessage());
        this.details = details;
    }
}
