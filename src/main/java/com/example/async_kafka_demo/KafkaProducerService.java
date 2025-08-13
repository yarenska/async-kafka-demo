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
        // We can define the topic name in the application properties or application yaml file but for the simplicity
        // I define it in here
        kafkaTemplate.send("my-event-topic", message)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        System.err.println("Kafka error: " + ex.getMessage());
                    } else {
                        // Here, we print out the message along with the offset that we sent to topic
                        System.out.println("Message sent to Kafka = [" + message +
                                "] with offset = [" + result.getRecordMetadata().offset() + "]");
                    }
                });
    }
}
