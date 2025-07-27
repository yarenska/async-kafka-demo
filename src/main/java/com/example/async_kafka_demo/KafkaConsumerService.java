package com.example.async_kafka_demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "my-event-topic", groupId = "my-consumer-group")
    public void consume(String message) {
        System.out.println("📥 Received from Kafka: " + message);
    }
}
