package com.chefathands.gateway.controller;

import com.chefathands.gateway.client.NotificationServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NotificationController {
    
    private final NotificationServiceClient notificationServiceClient;
    
    public NotificationController(NotificationServiceClient notificationServiceClient) {
        this.notificationServiceClient = notificationServiceClient;
    }
    
    @GetMapping("/users/{userId}/notifications")
    public ResponseEntity<String> getUserNotifications(
            @PathVariable String userId,
            @RequestParam(required = false) Boolean unreadOnly) {
        String response = notificationServiceClient.getUserNotifications(userId, unreadOnly);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/notifications")
    public ResponseEntity<String> createNotification(@RequestBody String body) {
        String response = notificationServiceClient.createNotification(body);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/users/{userId}/notifications/{notificationId}/read")
    public ResponseEntity<String> markAsRead(@PathVariable String userId, @PathVariable String notificationId) {
        String response = notificationServiceClient.markAsRead(userId, notificationId);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/users/{userId}/notifications/read-all")
    public ResponseEntity<Void> markAllAsRead(@PathVariable String userId) {
        notificationServiceClient.markAllAsRead(userId);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/users/{userId}/notifications/{notificationId}")
    public ResponseEntity<Void> deleteNotification(@PathVariable String userId, @PathVariable String notificationId) {
        notificationServiceClient.deleteNotification(userId, notificationId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/users/{userId}/notifications/unread-count")
    public ResponseEntity<String> getUnreadCount(@PathVariable String userId) {
        String response = notificationServiceClient.getUnreadCount(userId);
        return ResponseEntity.ok(response);
    }
}
