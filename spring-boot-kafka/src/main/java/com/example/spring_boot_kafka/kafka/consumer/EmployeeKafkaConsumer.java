package com.example.spring_boot_kafka.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeKafkaConsumer {

    @KafkaListener(topics = "${kafka.emp.topic}", groupId = "employeeGroup")
    public void consume(String message){
        log.info(String.format("Message Received ==> %s", message));
    }
}
