package com.ajt.kafkaPubSub.core;

import com.ajt.kafkaPubSub.models.NotificationModel;
import com.ajt.kafkaPubSub.utils.JsonUtil;
import com.ajt.kafkaPubSub.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaMsgProducer {
    private static final Logger log = LoggerFactory.getLogger(KafkaMsgProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, NotificationModel> notificationKafkaTemplate;

    public void sendMessage(String topicName, String message) {

        try {
            ListenableFuture<SendResult<String,String>> future = this.kafkaTemplate.send(topicName, message);

            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

                @Override
                public void onSuccess(SendResult<String, String> result) {
                    String msg = "Message published to kafka topic with offset=[" + result.getRecordMetadata()
                            .offset() + "]";
                    log.info(msg);
                }

                @Override
                public void onFailure(Throwable ex) {
                    String msg = "Unable to publish message to kafka topic due to : " + ex.getMessage();
                    log.error(msg);
                }
            });
        }
        catch (Exception ex) {
            String msg = "Unable to publish message to kafka topic due to : " + ex.getMessage();
            log.error(msg);
        }
    }

    public void sendNotificationMessage(String topicName, NotificationModel message) {
        this.notificationKafkaTemplate.send(topicName, message);
    }
}
