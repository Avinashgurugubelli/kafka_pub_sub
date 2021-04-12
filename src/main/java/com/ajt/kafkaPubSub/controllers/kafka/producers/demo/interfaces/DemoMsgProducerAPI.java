package com.ajt.kafkaPubSub.controllers.kafka.producers.demo.interfaces;

import com.ajt.kafkaPubSub.exception.ApiError;
import com.ajt.kafkaPubSub.models.NotificationModel;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@Api(
        tags = "Kafka producer services",
        description = ""
)
@ApiResponses({
        @ApiResponse(code = 400, message = "Bad request", response = ApiError.class),
        @ApiResponse(code = 401, message = "Authentication Failed", response = ApiError.class),
        @ApiResponse(code = 415, message = "Unsupported Content Type", response = ApiError.class),
        @ApiResponse(code = 500, message = "Internal server error", response = ApiError.class),
        @ApiResponse(code = 501, message = "Service under development - Not Implemented", response = ApiError.class),
        @ApiResponse(code = 503, message = "Service Unavailable", response = ApiError.class),
        @ApiResponse(code = 504, message = "Service Timeout Error", response = ApiError.class)
})
@RestController
public interface DemoMsgProducerAPI {

    @RequestMapping(value = "/v1/kafka/producer/demo", method = RequestMethod.POST)
    @ApiOperation(
            value = "Send Sample message to kafka topic.",
            response = String.class,
            tags = "Kafka producer services"
    )
    public Object sendMsg(
            @RequestBody(required = true) NotificationModel reqBody
    );
}
