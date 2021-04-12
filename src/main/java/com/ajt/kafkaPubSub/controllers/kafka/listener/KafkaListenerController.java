package com.ajt.kafkaPubSub.controllers.kafka.listener;

import com.ajt.kafkaPubSub.controllers.kafka.listener.interfaces.KafkaListenerAPI;
import com.ajt.kafkaPubSub.exception.ApiRequestException;
import com.ajt.kafkaPubSub.models.ListenerApiResponse;
import com.ajt.kafkaPubSub.services.KafkaListenersControllerReqProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KafkaListenerController implements KafkaListenerAPI {

    private static final Logger log = LoggerFactory.getLogger(KafkaListenerController.class);

    @Autowired
    private KafkaListenersControllerReqProcessor reqProcessor;

    @Autowired
    private ListenerApiReqValidator listenerApiReqValidator;

    @Override
    public List<ListenerApiResponse> start(String listeners) {
        this.log.debug("------------------------Request received: KAFKA listener START------------------------------");
        log.debug("passed request input: " + listeners);
        try {
            log.debug("Request payload validation started:");
            List<String> listenersList = listenerApiReqValidator.validateListeners(listeners);
            if (listenersList.size() > 0) {
                log.info("Request payload validated successfully, list of listeners: "+ listenersList.toString());
                List<ListenerApiResponse> response = reqProcessor.processStart(listenersList);
                log.debug("Processed response: "+ response.toString());
                log.info("------------------------Request processed successfully------------------------------");
                return response;
            } else {
                log.debug("Request payload validation un successful, returning null");
                return null;
            }
        } catch (ApiRequestException ex) {
            this.log.debug("------------------------KAFKA listener start request Processed with exceptions------------------------------");
            throw ex;
        } catch (Exception ex) {
            this.log.debug("------------------------KAFKA listener start request Processed with exceptions------------------------------");
            throw new ApiRequestException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @Override
    public List<ListenerApiResponse> stop(String listeners) {
        this.log.debug("------------------------Request received: KAFKA listener STOP------------------------------");
        log.debug("passed request input: " + listeners);
        try {
            log.debug("Request payload validation started:");
            List<String> listenersList = listenerApiReqValidator.validateListeners(listeners);
            if (listenersList.size() > 0) {
                log.info("Request payload validated successfully, list of listeners: "+ listenersList.toString());
                List<ListenerApiResponse> response = reqProcessor.processStop(listenersList);
                log.debug("Processed response: "+ response.toString());
                log.info("------------------------Request processed successfully-------------------------------");
                return response;
            } else {
                log.debug("Request payload validation un successful, returning null");
                return null;
            }
        } catch (ApiRequestException ex) {
            this.log.debug("------------------------KAFKA listener stop request Processed with exceptions------------------------------");
            throw ex;
        } catch (Exception ex) {
            this.log.debug("------------------------KAFKA listener stop request Processed with exceptions------------------------------");
            throw new ApiRequestException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @Override
    public List<ListenerApiResponse> status(String listeners) {
        this.log.debug("------------------------Request received: KAFKA listener STATUS------------------------------");
        log.debug("passed request input: " + listeners);
        try {
            log.debug("Request payload validation started:");
            List<String> listenersList = listenerApiReqValidator.validateListeners(listeners);
            if (listenersList.size() > 0) {
                log.info("Request payload validated successfully, list of listeners: "+ listenersList.toString());
                List<ListenerApiResponse> response = reqProcessor.processStatusCheck(listenersList);
                log.debug("Processed response: "+ response.toString());
                log.info("------------------------Request processed successfully------------------------------");
                return response;
            } else {
                return null;
            }
        } catch (ApiRequestException ex) {
            this.log.debug("------------------------KAFKA listener status request Processed with exceptions------------------------------");
            throw ex;
        } catch (Exception ex) {
            this.log.debug("------------------------KAFKA listener status request Processed with exceptions------------------------------");
            throw new ApiRequestException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
