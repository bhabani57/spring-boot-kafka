package com.example.orderservice.controller;

import com.example.base_domains.dto.Order;
import com.example.base_domains.dto.OrderEvent;
import com.example.orderservice.kafka.TopicProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    private TopicProducer topicProducer;

    @PostMapping("/orders")
    public String placeOrder(@RequestBody Order order){
        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("order status is in pending state");
        orderEvent.setOrder(order);

        topicProducer.sendMessage(orderEvent);

        return "Order placed successfully ...";
    }

}
