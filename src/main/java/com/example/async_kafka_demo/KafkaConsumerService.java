package com.example.async_kafka_demo;

import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @RetryableTopic // Default 3, adds "DLT" at the end of my topic.
    @KafkaListener(topics = "my-event-topic", groupId = "my-consumer-group")
    public void consume(String message,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                        @Header(KafkaHeaders.OFFSET) long offset) {
        try {
            System.out.println("Received from Kafka = [" + message +
                    "] from the topic = [" + topic + "] with offset = [" + offset + "]");
        } catch (Exception e) {
            System.out.println("Error receiving messages: "  + e.getMessage());;
        }
    }

    // An ack (acknowledgment) is a message sent by a consumer to a Kafka broker to indicate
    // that it has successfully processed a record. The consumer offset will be updated once the ack is sent.
    // If the consumer fails to read the message, we will send the message to dead letter queue
    @DltHandler
    public void listenDLQ(String message,
                          @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                          @Header(KafkaHeaders.OFFSET) long offset) {
        System.out.println("DLQ received from Kafka = [" + message +
                "] from the topic = [" + topic + "] with offset = [" + offset + "]");
    }

    // The messages are “stacked up” (accumulated) in the DLT just like in any other Kafka topic,
    // with their own partitions and offsets, until retention time expires or you manually clear them.

}
