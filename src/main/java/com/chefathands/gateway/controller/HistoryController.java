package com.chefathands.gateway.controller;

import com.chefathands.gateway.client.HistoryServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{userId}/history")
public class HistoryController {
    
    private final HistoryServiceClient historyServiceClient;
    
    public HistoryController(HistoryServiceClient historyServiceClient) {
        this.historyServiceClient = historyServiceClient;
    }
    
    @GetMapping
    public ResponseEntity<String> getHistory(@PathVariable String userId) {
        String history = historyServiceClient.getHistory(userId);
        return ResponseEntity.ok(history);
    }
    
    @PostMapping
    public ResponseEntity<String> addHistory(@PathVariable String userId, @RequestBody String body) {
        String result = historyServiceClient.addHistory(userId, body);
        return ResponseEntity.ok(result);
    }
    
    @DeleteMapping("/{recipeId}")
    public ResponseEntity<Void> removeHistory(@PathVariable String userId, @PathVariable String recipeId) {
        historyServiceClient.removeHistory(userId, recipeId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{recipeId}/exists")
    public ResponseEntity<String> isInHistory(@PathVariable String userId, @PathVariable String recipeId) {
        String exists = historyServiceClient.isInHistory(userId, recipeId);
        return ResponseEntity.ok(exists);
    }
}
