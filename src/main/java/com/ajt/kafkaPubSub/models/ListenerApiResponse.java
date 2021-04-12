package com.ajt.kafkaPubSub.models;

import com.ajt.kafkaPubSub.constants.ListenerState;

public class ListenerApiResponse {
    public String listenerName;
    public ListenerState state;
    public String message;
    public String timeStamp;

    public ListenerApiResponse(String listenerName, ListenerState state, String message, String timeStamp) {
        this.listenerName = listenerName;
        this.state = state;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "JMSApiResponse{" +
                "listenerName='" + listenerName + '\'' +
                ", state=" + state +
                ", message='" + message + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }
}
