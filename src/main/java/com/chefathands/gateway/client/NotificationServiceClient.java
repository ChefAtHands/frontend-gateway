package com.chefathands.gateway.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class NotificationServiceClient {
    
    private final RestTemplate restTemplate;
    private final String notificationServiceUrl;
    
    public NotificationServiceClient(RestTemplate restTemplate,
                                    @Value("${notification.service.url}") String notificationServiceUrl) {
        this.restTemplate = restTemplate;
        this.notificationServiceUrl = notificationServiceUrl;
    }
    
    public String getUserNotifications(String userId, Boolean unreadOnly) {
        UriComponentsBuilder builder = UriComponentsBuilder
            .fromHttpUrl(notificationServiceUrl + "/users/" + userId + "/notifications");
        
        if (unreadOnly != null) {
            builder.queryParam("unreadOnly", unreadOnly);
        }
        
        return restTemplate.getForObject(builder.toUriString(), String.class);
    }
    
    public String createNotification(String body) {
        String url = notificationServiceUrl + "/notifications";
        return restTemplate.postForObject(url, body, String.class);
    }
    
    public String markAsRead(String userId, String notificationId) {
        String url = notificationServiceUrl + "/users/" + userId + "/notifications/" + notificationId + "/read";
        return restTemplate.postForObject(url, null, String.class);
    }
    
    public void markAllAsRead(String userId) {
        String url = notificationServiceUrl + "/users/" + userId + "/notifications/read-all";
        restTemplate.postForObject(url, null, Void.class);
    }
    
    public void deleteNotification(String userId, String notificationId) {
        String url = notificationServiceUrl + "/users/" + userId + "/notifications/" + notificationId;
        restTemplate.delete(url);
    }
    
    public String getUnreadCount(String userId) {
        String url = notificationServiceUrl + "/users/" + userId + "/notifications/unread-count";
        return restTemplate.getForObject(url, String.class);
    }
}
