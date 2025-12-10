package com.chefathands.gateway.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class LoggingServiceClient {
    
    private final RestTemplate restTemplate;
    
    @Value("${services.logging.url}")
    private String loggingServiceUrl;
    
    public LoggingServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public void log(String action, Integer userId, String message) {
        try {
            String url = loggingServiceUrl + "/api/logs";
            
            Map<String, Object> logEntry = new HashMap<>();
            logEntry.put("action", action);
            logEntry.put("userId", userId);
            logEntry.put("message", message);
            logEntry.put("timestamp", System.currentTimeMillis());
            
            restTemplate.postForObject(url, logEntry, String.class);
        } catch (Exception e) {
            
            System.err.println("Logging service unavailable for action: " + action);
        }
    }
}