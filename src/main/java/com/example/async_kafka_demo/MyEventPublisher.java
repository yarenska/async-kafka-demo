package com.example.async_kafka_demo;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class MyEventPublisher {
    private ApplicationEventPublisher publisher; //Spring default, publishes the event who is listening

    public MyEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publish(String payload) {
        publisher.publishEvent(new MyEvent(payload));
        System.out.println("Published event: " + payload);
    }
}
