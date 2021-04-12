package com.ajt.kafkaPubSub.controllers.kafka.listener.interfaces;

import com.ajt.kafkaPubSub.exception.ApiError;
import com.ajt.kafkaPubSub.models.ListenerApiResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;



@ApiResponses({
        @ApiResponse(code = 400, message = "Bad request", response = ApiError.class),
        @ApiResponse(code = 401, message = "Authentication Failed", response = ApiError.class),
        @ApiResponse(code = 415, message = "Unsupported Content Type", response = ApiError.class),
        @ApiResponse(code = 500, message = "Internal server error", response = ApiError.class),
        @ApiResponse(code = 501, message = "Service under development - Not Implemented", response = ApiError.class),
        @ApiResponse(code = 503, message = "Service Unavailable", response = ApiError.class),
        @ApiResponse(code = 504, message = "Service Timeout Error", response = ApiError.class)
})
public interface KafkaListenerAPI {

    @RequestMapping(value = "/kafkaListener/start", method = RequestMethod.GET)
    @ApiOperation(
            value = "Starts the given kafka listener",
            response = ListenerApiResponse.class,
            tags = ""
    )
    public List<ListenerApiResponse> start(
            @ApiParam(
                    name = "listeners",
                    value = "possible values (To set a list of values, provide a comma-separated list)",
                    example = ""
            )
            @RequestHeader(required = true) String listeners
    );

    @RequestMapping(value = "/kafkaListener/stop", method = RequestMethod.GET)
    @ApiOperation(
            value = "Halt's the given kafka listener",
            response = ListenerApiResponse.class,
            tags = ""
    )
    public List<ListenerApiResponse> stop(
            @ApiParam(
                    name = "listeners",
                    value = "possible values (To set a list of values, provide a comma-separated list)",
                    example = ""
            )
            @RequestHeader(required = true) String listeners
    );

    @RequestMapping(value = "/kafkaListener/status", method = RequestMethod.GET)
    @ApiOperation(
            value = "returns the given Kafka listener status",
            response = ListenerApiResponse.class,
            tags = ""
    )
    public List<ListenerApiResponse> status(
            @ApiParam(
                    name = "listeners",
                    value = "possible values (To set a list of values, provide a comma-separated list)",
                    example = ""
            )
            @RequestHeader(required = true) String listeners
    );
}
