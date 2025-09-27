package com.example.spring_boot_kafka.controller;

import com.example.spring_boot_kafka.kafka.producer.KafkaProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka/msg")
public class MessageController {

    private KafkaProducer kafkaProducer;

    public MessageController(KafkaProducer kafkaProducer){
        this.kafkaProducer = kafkaProducer;
    }


    @GetMapping("/publish/{msg}")
    public ResponseEntity<String> publish(@PathVariable("msg") String message){
        kafkaProducer.sendMessage(message);
        return new ResponseEntity<>("Message Sent to topic", HttpStatus.OK);
    }



}
