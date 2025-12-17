package com.chefathands.gateway.client;

import com.chefathands.gateway.dto.LogDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class LoggingServiceClient {
    
    private final RestTemplate restTemplate;
    
    @Value("${services.logging.url}")
    private String loggingServiceUrl;
    
    public LoggingServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    // Internal logging (non-blocking)
    public void log(String action, Integer userId, String message) {
        try {
            String url = loggingServiceUrl + "/logs";
            
            LogDTO logEntry = new LogDTO();
            logEntry.setLevel("INFO");
            logEntry.setMessage(action + " - User: " + userId + " - " + message);
            
            restTemplate.postForObject(url, logEntry, LogDTO.class);
        } catch (Exception e) {
            System.err.println("⚠️  Logging service unavailable for action: " + action);
        }
    }
    
    // Gateway endpoints
    public List<LogDTO> getAllLogs() {
        String url = loggingServiceUrl + "/logs";
        ResponseEntity<List<LogDTO>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<LogDTO>>() {}
        );
        return response.getBody();
    }
    
    public List<LogDTO> getLogsByLevel(String level) {
        String url = loggingServiceUrl + "/logs/level/" + level;
        ResponseEntity<List<LogDTO>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<LogDTO>>() {}
        );
        return response.getBody();
    }
    
    public LogDTO createLog(LogDTO logDTO) {
        String url = loggingServiceUrl + "/logs";
        return restTemplate.postForObject(url, logDTO, LogDTO.class);
    }
    
    public LogDTO logInfo(String message) {
        String url = loggingServiceUrl + "/logs/info";
        return restTemplate.postForObject(url, message, LogDTO.class);
    }
    
    public LogDTO logWarn(String message) {
        String url = loggingServiceUrl + "/logs/warn";
        return restTemplate.postForObject(url, message, LogDTO.class);
    }
    
    public LogDTO logError(String message) {
        String url = loggingServiceUrl + "/logs/error";
        return restTemplate.postForObject(url, message, LogDTO.class);
    }
}