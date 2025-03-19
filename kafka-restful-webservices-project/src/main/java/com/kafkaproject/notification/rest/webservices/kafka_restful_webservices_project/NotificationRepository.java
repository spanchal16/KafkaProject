package com.kafkaproject.notification.rest.webservices.kafka_restful_webservices_project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationModel, Long> {
}
