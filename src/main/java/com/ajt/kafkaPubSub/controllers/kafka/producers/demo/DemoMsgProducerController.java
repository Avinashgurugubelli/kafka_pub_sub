package com.ajt.kafkaPubSub.controllers.kafka.producers.demo;



import com.ajt.kafkaPubSub.config.AjtConfig;
import com.ajt.kafkaPubSub.controllers.kafka.producers.demo.interfaces.DemoMsgProducerAPI;
import com.ajt.kafkaPubSub.core.KafkaMsgProducer;
import com.ajt.kafkaPubSub.exception.ApiRequestException;
import com.ajt.kafkaPubSub.models.NotificationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoMsgProducerController implements DemoMsgProducerAPI {

    @Autowired
    private KafkaMsgProducer kafkaMsgProducer;

    @Autowired
    private AjtConfig ajtConfig;

    @Override
    public Object sendMsg(NotificationModel reqBody) {
        try {
          this.kafkaMsgProducer.sendNotificationMessage(this.ajtConfig.getKafkaDemoTopic(), reqBody);
          return "Message added to topic successfully";
        }
        catch (Exception ex) {
            throw new ApiRequestException(ex.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }
}
