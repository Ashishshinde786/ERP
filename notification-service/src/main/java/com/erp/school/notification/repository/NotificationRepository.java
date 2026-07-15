package com.erp.school.notification.repository;

import com.erp.school.notification.entity.Notification;
import com.erp.school.notification.enums.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByTargetAudienceOrderByCreatedAtDesc(String targetAudience);
    List<Notification> findByTypeOrderByCreatedAtDesc(NotificationType type);
    List<Notification> findByIsReadOrderByCreatedAtDesc(Boolean isRead);
    List<Notification> findByTargetIdAndTargetAudienceOrderByCreatedAtDesc(Long targetId, String audience);
    long countByIsRead(Boolean isRead);
    List<Notification> findAllByOrderByCreatedAtDesc();
}