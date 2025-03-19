package com.kafkaproject.notification.rest.webservices.kafka_restful_webservices_project;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

@Service
public class NotificationProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public NotificationProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(String message) {
        try {
            CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("notifications", message);
            future.whenComplete((result, exception) -> {
                if (exception == null) {
                    System.out.println("Message sent successfully: " + message);
                } else {
                    System.err.println("Failed to send message: " + exception.getMessage());
                }
            });
        } catch (Exception e) {
            System.err.println("Kafka error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
