package com.example.orderservice.kafka;

import com.example.base_domains.dto.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TopicProducer {

    private NewTopic newTopic;

    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public TopicProducer(NewTopic newTopic, KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.newTopic = newTopic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(OrderEvent orderEvent){
        log.info(String.format("Order Event ==>", orderEvent.getMessage()));
        Message<OrderEvent> message = MessageBuilder
                                        .withPayload(orderEvent)
                                        .setHeader(KafkaHeaders.TOPIC, newTopic.name())
                                        .build();

        kafkaTemplate.send(message);
    }
}
