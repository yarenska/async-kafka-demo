package com.example.async_kafka_demo;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(String message) {
        kafkaTemplate.send("my-event-topic", message)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        System.err.println("Kafka error: " + ex.getMessage());
                    } else {
                        System.out.println("Message sent to Kafka: " + result.getRecordMetadata());
                    }
                });
    }
}
