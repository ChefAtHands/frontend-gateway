package com.chefathands.gateway.controller;

import com.chefathands.gateway.dto.RecommendationRequestDTO;
import com.chefathands.gateway.dto.RecommendationResponseDTO;
import com.chefathands.gateway.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {
    
    private final RecommendationService recommendationService;
    
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }
    
    @GetMapping
    public ResponseEntity<RecommendationResponseDTO> getRecommendations(
            @RequestParam Integer userId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer minProtein) {
        RecommendationResponseDTO response = recommendationService
            .getRecommendationsForUser(userId, category, minProtein);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping
    public ResponseEntity<RecommendationResponseDTO> getCustomRecommendations(
            @RequestBody RecommendationRequestDTO request) {
        RecommendationResponseDTO response = recommendationService
            .getRecommendationsWithCustomIngredients(request);
        return ResponseEntity.ok(response);
    }
}
