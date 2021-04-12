package com.ajt.kafkaPubSub.services;

import com.ajt.kafkaPubSub.constants.ListenerConstants;
import com.ajt.kafkaPubSub.constants.ListenerState;
import com.ajt.kafkaPubSub.exception.ApiRequestException;
import com.ajt.kafkaPubSub.models.ListenerApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class KafkaListenersControllerReqProcessor {

    private static final Logger log = LoggerFactory.getLogger(KafkaListenersControllerReqProcessor.class);

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry ;

    public List<ListenerApiResponse> processStart(List<String> listenersList) {
        List<ListenerApiResponse> response = new ArrayList<ListenerApiResponse>();
        for (int i = 0; i < listenersList.size(); i++) {
            try {
                MessageListenerContainer container = this.kafkaListenerEndpointRegistry.getListenerContainer(ListenerConstants.listenersIdMapper.get(listenersList.get(i).toLowerCase()));
                if (!container.isRunning()) {
                    Date date = new Date();
                    container.start();
                    log.info(listenersList.get(i)+ " message listener container started successfully");
                    response.add(new ListenerApiResponse(listenersList.get(i), ListenerState.STARTED, "started successfully", date.toString()));
                } else {
                    log.info(listenersList.get(i)+ " message listener container already in running state");
                    Date date = new Date();
                    response.add(new ListenerApiResponse(listenersList.get(i), ListenerState.RUNNING, "Already in running state", date.toString()));
                }
            } catch (Exception ex) {
                log.error(ex.getMessage(), ex.fillInStackTrace());
                throw new ApiRequestException("Some error occurred while starting the listener: "+ listenersList.get(i) +"\n Full error message:  "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
            }
        }
        return response;
    }

    public List<ListenerApiResponse> processStop(List<String> listenersList) {
        List<ListenerApiResponse> response = new ArrayList<ListenerApiResponse>();
        for (int i = 0; i < listenersList.size(); i++) {
            try {
                MessageListenerContainer container = this.kafkaListenerEndpointRegistry.getListenerContainer(ListenerConstants.listenersIdMapper.get(listenersList.get(i).toLowerCase()));
                if (container.isRunning()) {
                    Date date = new Date();
                    container.stop();
                    log.info(listenersList.get(i)+ " message listener container stopped successfully");
                    response.add(new ListenerApiResponse(listenersList.get(i), ListenerState.STOPPED, "Stopped successfully", date.toString()));
                } else {
                    Date date = new Date();
                    log.info(listenersList.get(i)+ " message listener container already in stopped state");
                    response.add(new ListenerApiResponse(listenersList.get(i), ListenerState.STOPPED, "Already in stopped state", date.toString()));
                }
            } catch (Exception ex) {
                log.error(ex.getMessage(), ex.fillInStackTrace());
                throw new ApiRequestException("Some error occurred while stopping the listener: "+ listenersList.get(i) +"\n Full error message:  "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
            }
        }
        return response;
    }

    public List<ListenerApiResponse> processStatusCheck(List<String> listenersList) {
        List<ListenerApiResponse> response = new ArrayList<ListenerApiResponse>();
        for (int i = 0; i < listenersList.size(); i++) {
            try {
                MessageListenerContainer container = this.kafkaListenerEndpointRegistry.getListenerContainer(ListenerConstants.listenersIdMapper.get(listenersList.get(i).toLowerCase()));
                if (container.isRunning()) {
                    log.info(listenersList.get(i)+ " message listener container in running state");
                    Date date = new Date();
                    response.add(new ListenerApiResponse(listenersList.get(i), ListenerState.RUNNING, "In running state", date.toString()));
                } else {
                    log.info(listenersList.get(i)+ " message listener container in stopped state");
                    Date date = new Date();
                    response.add(new ListenerApiResponse(listenersList.get(i), ListenerState.STOPPED, "In halt state", date.toString()));
                }
            } catch (Exception ex) {
                log.error(ex.getMessage(), ex.fillInStackTrace());
                throw new ApiRequestException("Some error occurred while checking the listener status: "+ listenersList.get(i) +"\n Full error message:  "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
            }
        }
        return response;
    }
}
