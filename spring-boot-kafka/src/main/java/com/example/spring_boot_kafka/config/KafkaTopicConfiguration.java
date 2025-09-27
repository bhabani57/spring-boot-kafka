package com.example.spring_boot_kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

    @Value("${kafka.emp.topic}")
    private String kafkaTopic;

    @Bean
    public NewTopic employeeTopic() {
        return TopicBuilder.name(kafkaTopic).build();
    }
}
