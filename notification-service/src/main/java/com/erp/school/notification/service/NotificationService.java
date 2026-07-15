package com.erp.school.notification.service;

import com.erp.school.notification.dto.NotificationRequest;
import com.erp.school.notification.entity.Notification;
import com.erp.school.notification.enums.NotificationType;
import com.erp.school.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    public Notification send(NotificationRequest request) {
        Notification notification = new Notification();
        notification.setTitle(request.getTitle());
        notification.setMessage(request.getMessage());
        notification.setType(parseType(request.getType()));
        notification.setTargetAudience(
            request.getTargetAudience() != null ? request.getTargetAudience().toUpperCase() : "ALL");
        notification.setTargetId(request.getTargetId());
        notification.setSentBy(request.getSentBy() != null ? request.getSentBy() : "SYSTEM");
        notification.setIsRead(false);
        return notificationRepository.save(notification);
    }

    public List<Notification> getAll() {
        return notificationRepository.findAllByOrderByCreatedAtDesc();
    }

    public Notification getById(Long id) {
        return notificationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Notification not found: " + id));
    }

    public List<Notification> getByAudience(String audience) {
        return notificationRepository.findByTargetAudienceOrderByCreatedAtDesc(audience.toUpperCase());
    }

    public List<Notification> getByType(String type) {
        return notificationRepository.findByTypeOrderByCreatedAtDesc(parseType(type));
    }

    public List<Notification> getUnread() {
        return notificationRepository.findByIsReadOrderByCreatedAtDesc(false);
    }

    @Transactional
    public Notification markAsRead(Long id) {
        Notification notification = getById(id);
        notification.setIsRead(true);
        return notificationRepository.save(notification);
    }

    @Transactional
    public void markAllAsRead() {
        List<Notification> unread = notificationRepository.findByIsReadOrderByCreatedAtDesc(false);
        unread.forEach(n -> n.setIsRead(true));
        notificationRepository.saveAll(unread);
    }

    @Transactional
    public void delete(Long id) {
        notificationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Notification not found: " + id));
        notificationRepository.deleteById(id);
    }

    public long countUnread() {
        return notificationRepository.countByIsRead(false);
    }

    private NotificationType parseType(String type) {
        try {
            return NotificationType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException(
                "Invalid notification type: '" + type + "'. Must be one of "
                + Arrays.toString(NotificationType.values()));
        }
    }
}