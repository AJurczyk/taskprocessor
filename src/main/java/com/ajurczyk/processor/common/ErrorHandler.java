package com.ajurczyk.processor.common;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
@ControllerAdvice
public class ErrorHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorDto notFound(NotFoundException ex) {
        return mapToErrorDtoWithDetails(ex.getErrorCode(), ex.getDetails());
    }
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDto anyException(Exception ex) {
        LOG.error(ex.getMessage(), ex);
        return mapToErrorDto(ErrorCode.INTERNAL_SERVER);
    }

	@ExceptionHandler({BadRequestException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorDto badRequest() {
		return mapToErrorDto(ErrorCode.BAD_REQUEST);
	}

    private ErrorDto mapToErrorDto(ErrorCode errorCode) {
        return new ErrorDto(errorCode);
    }

    private ErrorDto mapToErrorDtoWithDetails(ErrorCode errorCode, String details) {
        ErrorDto error = mapToErrorDto(errorCode);
        error.setDetails(details);
        return error;
    }
}
