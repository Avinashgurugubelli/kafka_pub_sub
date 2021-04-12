package com.ajt.kafkaPubSub.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "code", "type", "message"
})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiError {
    public Integer code = null;
    public String type = null;
    public String message = null;
}