package com.chefathands.gateway.controller;

import com.chefathands.gateway.client.LoggingServiceClient;
import com.chefathands.gateway.dto.LogDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {
    
    private final LoggingServiceClient loggingServiceClient;
    
    public LogController(LoggingServiceClient loggingServiceClient) {
        this.loggingServiceClient = loggingServiceClient;
    }
    
    @GetMapping
    public ResponseEntity<List<LogDTO>> getAllLogs() {
        List<LogDTO> logs = loggingServiceClient.getAllLogs();
        return ResponseEntity.ok(logs);
    }
    
    @GetMapping("/level/{level}")
    public ResponseEntity<List<LogDTO>> getLogsByLevel(@PathVariable String level) {
        List<LogDTO> logs = loggingServiceClient.getLogsByLevel(level);
        return ResponseEntity.ok(logs);
    }
    
    @PostMapping
    public ResponseEntity<LogDTO> createLog(@RequestBody LogDTO logDTO) {
        LogDTO created = loggingServiceClient.createLog(logDTO);
        return ResponseEntity.ok(created);
    }
    
    @PostMapping("/info")
    public ResponseEntity<LogDTO> logInfo(@RequestBody String message) {
        LogDTO created = loggingServiceClient.logInfo(message);
        return ResponseEntity.ok(created);
    }
    
    @PostMapping("/warn")
    public ResponseEntity<LogDTO> logWarn(@RequestBody String message) {
        LogDTO created = loggingServiceClient.logWarn(message);
        return ResponseEntity.ok(created);
    }
    
    @PostMapping("/error")
    public ResponseEntity<LogDTO> logError(@RequestBody String message) {
        LogDTO created = loggingServiceClient.logError(message);
        return ResponseEntity.ok(created);
    }
}