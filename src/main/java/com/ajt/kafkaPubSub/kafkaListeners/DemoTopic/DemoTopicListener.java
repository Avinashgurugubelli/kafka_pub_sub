package com.ajt.kafkaPubSub.kafkaListeners.DemoTopic;

import com.ajt.kafkaPubSub.models.NotificationModel;
import com.ajt.kafkaPubSub.utils.JsonUtil;
import com.ajt.kafkaPubSub.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DemoTopicListener {

    private static final Logger log = LoggerFactory.getLogger(DemoTopicListener.class);

    @KafkaListener( id = "testTopicListener", topics = "${app.ajt.kafkaDemoTopic}", containerFactory = "notificationKafkaListenerContainerFactory", groupId = "${app.ajt.kafkaDemoTopicGroupId}")
    public void consume(NotificationModel message) {
        try {
            if(message != null) {
                log.info("Messaged received and passed to JSON string convertor");
                System.out.println("Received notification message: " + JsonUtil.convertObjToJsonStr(message));
            }
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception.fillInStackTrace());
        }
    }
}
