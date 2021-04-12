package com.ajt.kafkaPubSub.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonUtil {

    // Creating Object of ObjectMapper define in Jakson Api
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    public static <T> String convertObjToJsonStr(T obj) {
        try {
            // get object as a json string
            String jsonStr =  objectMapper.writeValueAsString(obj);
            return jsonStr;
        }
        catch (IOException ex) {
            log.error("IOException occurred while converting object to JSON, full message: "+ ex.getMessage());
            return "";
        }
    }
}
