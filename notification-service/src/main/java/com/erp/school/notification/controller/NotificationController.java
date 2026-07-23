package com.erp.school.notification.controller;

import com.erp.school.common.dto.ApiResponse;
import com.erp.school.notification.dto.NotificationRequest;
import com.erp.school.notification.entity.Notification;
import com.erp.school.notification.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public ResponseEntity<ApiResponse<Notification>> send(
            @Valid @RequestBody NotificationRequest request) {
        Notification saved = notificationService.send(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Notification sent: " + saved.getTitle(), saved));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Notification>>> getAll() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", notificationService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Notification>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", notificationService.getById(id)));
    }

    @GetMapping("/audience/{audience}")
    public ResponseEntity<ApiResponse<List<Notification>>> getByAudience(@PathVariable String audience) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", notificationService.getByAudience(audience)));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<ApiResponse<List<Notification>>> getByType(@PathVariable String type) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", notificationService.getByType(type)));
    }

    @GetMapping("/unread")
    public ResponseEntity<ApiResponse<List<Notification>>> getUnread() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", notificationService.getUnread()));
    }

    @GetMapping("/stats/unread-count")
    public ResponseEntity<ApiResponse<Long>> countUnread() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", notificationService.countUnread()));
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<ApiResponse<Notification>> markAsRead(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Marked as read", notificationService.markAsRead(id)));
    }

    @PutMapping("/read-all")
    public ResponseEntity<ApiResponse<Void>> markAllAsRead() {
        notificationService.markAllAsRead();
        return ResponseEntity.ok(new ApiResponse<>(true, "All notifications marked as read", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        notificationService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Notification deleted", null));
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Notification Service is running on port 8087");
    }
}