package com.erp.school.notification.entity;

import com.erp.school.common.entity.BaseEntity;
import com.erp.school.notification.enums.NotificationType;
import jakarta.persistence.*;

@Entity
@Table(name = "notification")
public class Notification extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "message", columnDefinition = "TEXT", nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private NotificationType type;

    @Column(name = "target_audience")
    private String targetAudience;

    @Column(name = "target_id")
    private Long targetId;

    @Column(name = "is_read")
    private Boolean isRead = false;

    @Column(name = "sent_by")
    private String sentBy;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public NotificationType getType() { return type; }
    public void setType(NotificationType type) { this.type = type; }

    public String getTargetAudience() { return targetAudience; }
    public void setTargetAudience(String targetAudience) { this.targetAudience = targetAudience; }

    public Long getTargetId() { return targetId; }
    public void setTargetId(Long targetId) { this.targetId = targetId; }

    public Boolean getIsRead() { return isRead; }
    public void setIsRead(Boolean isRead) { this.isRead = isRead; }

    public String getSentBy() { return sentBy; }
    public void setSentBy(String sentBy) { this.sentBy = sentBy; }
}