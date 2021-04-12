package com.ajt.kafkaPubSub.controllers.kafka.listener;

import com.ajt.kafkaPubSub.constants.ListenerConstants;
import com.ajt.kafkaPubSub.exception.ApiRequestException;
import com.ajt.kafkaPubSub.utils.Util;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListenerApiReqValidator {
    public static List<String> validateListeners(String listeners) {
        List<String> listenersList = Util.removeEmptyNullValuesInList(Util.commaSeparatedStringToList(listeners));
        if(Util.isListNullOrEmpty(listenersList)) {
            throw new ApiRequestException("listener is mandatory", HttpStatus.BAD_REQUEST, null);
        }
        else {
            for (int i = 0; i < listenersList.size(); i++) {
                if(Util.contains(ListenerConstants.availableListeners, listenersList.get(i))) {
                    continue;
                }
                else {
                    throw new ApiRequestException("Invalid listener: "+ listenersList.get(i), HttpStatus.BAD_REQUEST, null);
                }
            }
            return listenersList;
        }
    }
}
