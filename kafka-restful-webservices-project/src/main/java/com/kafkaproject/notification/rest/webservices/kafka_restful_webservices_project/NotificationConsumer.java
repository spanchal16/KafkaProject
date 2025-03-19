package com.kafkaproject.notification.rest.webservices.kafka_restful_webservices_project;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;

    public NotificationConsumer(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @KafkaListener(topics = "notifications", groupId = "notification-group")
    public void consumeNotifications(String message) {
        NotificationModel notificationModel = new NotificationModel();
        notificationModel.setMessage(message);
        notificationRepository.save(notificationModel);
    }
}
