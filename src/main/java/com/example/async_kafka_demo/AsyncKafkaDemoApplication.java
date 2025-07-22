package com.example.async_kafka_demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AsyncKafkaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsyncKafkaDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(MyEventPublisher publisher) {
		CommandLineRunner runner = args -> {
			//Creating threads to publish multiple events
			// with the given payload in every 10 seconds
			for(int i = 0; i < 5; i++) {
				Thread.sleep(10000);
				publisher.publish("Event " + i);
			}
		};

		return runner;
	}
}
