package com.example.kafka.producer;

import com.example.handler.WikiMediaChangesHandler;
import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.StreamException;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@Slf4j
public class WikiMediaChangesProducer {

    @Value("${kafka.wikimedia.topic}")
    private String wikimediaTopic;

    @Value("${url}")
    private String url;

    private KafkaTemplate<String, String> kafkaTemplate;

    public WikiMediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws StreamException {
       // to read real time stream data from wikimedia, we use wikimedia
        BackgroundEventHandler backgroundEventHandler = new WikiMediaChangesHandler(kafkaTemplate, wikimediaTopic);
        EventSource.Builder builder = new EventSource.Builder(URI.create(url));
        BackgroundEventSource.Builder eventSourceBuild = new BackgroundEventSource.Builder(backgroundEventHandler, builder);
        BackgroundEventSource eventSource = eventSourceBuild.build();
        eventSource.start();
    }
}
