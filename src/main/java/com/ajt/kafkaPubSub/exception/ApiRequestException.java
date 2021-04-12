package com.ajt.kafkaPubSub.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiRequestException extends RuntimeException {
    private HttpStatus status = HttpStatus.BAD_REQUEST;
    private ApiError apiError = new ApiError();
    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
        this.apiError.message = message;
        this.apiError.type = status.getReasonPhrase();
        this.apiError.code = status.value();
    }
    public ApiRequestException(String message, HttpStatus httpStatus, Throwable cause) {
        super(message, cause);
        this.apiError.message = message;
        this.apiError.type = httpStatus.getReasonPhrase();
        this.apiError.code = httpStatus.value();
    }
    public ApiRequestException(String message, HttpStatus httpStatus, String errorType, Throwable cause) {
        super(message, cause);
        this.apiError.message = message;
        this.apiError.type = errorType;
        this.apiError.code = httpStatus.value();
    }
}
