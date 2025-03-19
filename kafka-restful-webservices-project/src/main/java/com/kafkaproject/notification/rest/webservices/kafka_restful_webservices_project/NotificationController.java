package com.kafkaproject.notification.rest.webservices.kafka_restful_webservices_project;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "http://localhost:4200")
public class NotificationController {
    private final NotificationProducer producer;
    private final NotificationRepository repository;

    public NotificationController(NotificationProducer producer,
                                  NotificationRepository repository) {

        this.producer = producer;
        this.repository = repository;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody Map<String, String> payload) {
        String message = payload.get("message");  // Extract message from JSON

        if (message == null || message.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Message cannot be empty!");
        }
        producer.sendNotification(message);  // Send to Kafka
        return ResponseEntity.ok("Notification sent successfully!");
    }

    @GetMapping
    public List<NotificationModel> getAllNotifications() {
        return repository.findAll();
    }
}
