package com.example.async_kafka_demo;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MyEventListener {

    private final KafkaProducerService kafkaProducer;

    public MyEventListener(KafkaProducerService kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @EventListener
    @Async
    public void listen(MyEvent event) {
        System.out.println("🔄 Async listener received event: " + event.getPayload());
        kafkaProducer.sendEvent(event.getPayload());
    }
}
