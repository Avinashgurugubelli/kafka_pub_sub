package com.ajt.kafkaPubSub.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiReqException(ApiRequestException ex) {
        ApiError apiError = new ApiError();
        // setting up api exception to return to end user
        apiError.message = ex.getApiError().message;
        apiError.code = ex.getApiError().code;
        apiError.type = ex.getApiError().type;
        return new ResponseEntity<>(apiError, ex.getStatus());
    }
}
