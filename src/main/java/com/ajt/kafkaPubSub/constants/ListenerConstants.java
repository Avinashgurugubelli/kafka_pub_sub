package com.ajt.kafkaPubSub.constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListenerConstants {
    public static final List<String> availableListeners = Arrays.asList("test");

    public static final Map<String, String> listenersIdMapper = new HashMap<String, String>() {{
        put(availableListeners.get(0).toLowerCase(), "testTopicListener");
    }};
}
