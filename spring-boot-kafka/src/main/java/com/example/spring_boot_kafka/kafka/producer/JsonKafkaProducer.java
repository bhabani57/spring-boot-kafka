package com.example.spring_boot_kafka.kafka.producer;

import com.example.spring_boot_kafka.payload.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JsonKafkaProducer {

    @Value("${kafka.emp.topic.json}")
    private String kafkaTopicJson;

    private KafkaTemplate<String, User> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User user){
        log.info(String.format("Message sent ==> %s", user.toString()));
        Message<User> message = MessageBuilder.withPayload(user)
                .setHeader(KafkaHeaders.TOPIC, kafkaTopicJson)
                .build();
        kafkaTemplate.send(message);
        //kafkaTemplate.send(kafkaTopic, user);
    }
}
