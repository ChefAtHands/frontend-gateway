package com.chefathands.gateway.service;

import com.chefathands.gateway.client.LoggingServiceClient;
import com.chefathands.gateway.client.RecommendationServiceClient;
import com.chefathands.gateway.dto.RecommendationRequestDTO;
import com.chefathands.gateway.dto.RecommendationResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService {
    
    private final RecommendationServiceClient recommendationServiceClient;
    private final LoggingServiceClient loggingServiceClient;
    
    public RecommendationService(
            RecommendationServiceClient recommendationServiceClient,
            LoggingServiceClient loggingServiceClient) {
        this.recommendationServiceClient = recommendationServiceClient;
        this.loggingServiceClient = loggingServiceClient;
    }
    
    public RecommendationResponseDTO getRecommendationsForUser(
            Integer userId, String category, Integer minProtein) {
        
        loggingServiceClient.log("GET_RECOMMENDATIONS", userId, 
            "Category: " + category + ", MinProtein: " + minProtein);
        
        RecommendationResponseDTO response = recommendationServiceClient
            .getRecommendations(userId, category, minProtein);
        
        return response;
    }
    
    public RecommendationResponseDTO getRecommendationsWithCustomIngredients(
            RecommendationRequestDTO request) {
        
        loggingServiceClient.log("GET_CUSTOM_RECOMMENDATIONS", null, 
            "Custom ingredients: " + request.getIngredients().size());
        
        return recommendationServiceClient.getRecommendationsWithCustomIngredients(request);
    }
}
