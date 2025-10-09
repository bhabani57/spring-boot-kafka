package com.example.emailservice.kafka;

import com.example.base_domains.dto.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderConsumer {

    @KafkaListener(topics = "${email.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent orderEvent){
        log.info(String.format("Email Event received in email service ==> %s", orderEvent.toString()));
        log.info("email {}", orderEvent.getOrder().getOrderId());
    }
}
