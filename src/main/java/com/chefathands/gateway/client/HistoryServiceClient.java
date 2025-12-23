package com.chefathands.gateway.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HistoryServiceClient {
    
    private static final Logger logger = LoggerFactory.getLogger(HistoryServiceClient.class);
    
    private final RestTemplate restTemplate;
    
    @Value("${history.service.url:http://localhost:8088}")
    private String historyServiceUrl;
    
    public HistoryServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public String getHistory(String userId) {
        String url = historyServiceUrl + "/users/" + userId + "/history";
        logger.info("Calling history service: {}", url);
        return restTemplate.getForObject(url, String.class);
    }
    
    public String addHistory(String userId, String body) {
        String url = historyServiceUrl + "/users/" + userId + "/history";
        logger.info("Adding to history: {}", url);
        return restTemplate.postForObject(url, body, String.class);
    }
    
    public void removeHistory(String userId, String recipeId) {
        String url = historyServiceUrl + "/users/" + userId + "/history/" + recipeId;
        logger.info("Removing from history: {}", url);
        restTemplate.delete(url);
    }
    
    public String isInHistory(String userId, String recipeId) {
        String url = historyServiceUrl + "/users/" + userId + "/history/" + recipeId + "/exists";
        logger.info("Checking if in history: {}", url);
        return restTemplate.getForObject(url, String.class);
    }
}
