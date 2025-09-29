package com.example.spring_boot_kafka.kafka.consumer;

import com.example.spring_boot_kafka.payload.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JSONKafkaConsumer {

    @KafkaListener(topics = "${kafka.emp.topic.json}", groupId = "employeeGroup")
    public void consume(User user){
        System.out.println("id="+ user.getId());
        System.out.println("First Name="+ user.getFirstName());
        System.out.println("Last Name="+ user.getLastName());

    }
}
