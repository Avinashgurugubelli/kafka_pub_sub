package com.ajt.kafkaPubSub.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration()
@ConfigurationProperties("app.ajt")
public class AjtConfig {
    private String kafkaDemoTopic;
    private String kafkaBootstrapServer;
    private String kafkaDemoTopicGroupId;
}
