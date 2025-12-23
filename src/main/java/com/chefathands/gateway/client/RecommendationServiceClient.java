package com.chefathands.gateway.client;

import com.chefathands.gateway.dto.RecommendationRequestDTO;
import com.chefathands.gateway.dto.RecommendationResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RecommendationServiceClient {
    
    private static final Logger logger = LoggerFactory.getLogger(RecommendationServiceClient.class);
    
    private final RestTemplate restTemplate;
    
    @Value("${services.recommendation.url}")
    private String recommendationServiceUrl;
    
    public RecommendationServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public RecommendationResponseDTO getRecommendations(Integer userId, String category, Integer minProtein) {
        UriComponentsBuilder builder = UriComponentsBuilder
            .fromHttpUrl(recommendationServiceUrl + "/api/recommendations")
            .queryParam("userID", userId);  // Changed from "userId" to "userID"
        
        if (category != null) {
            builder.queryParam("type", category);  // Changed from "category" to "type" (matches Spoonacular)
        }
        if (minProtein != null) {
            builder.queryParam("minProtein", minProtein);
        }
        
        String url = builder.toUriString();
        logger.info("Calling recommendation service: {}", url);
        
        return restTemplate.getForObject(url, RecommendationResponseDTO.class);
    }
    
    public RecommendationResponseDTO getRecommendationsWithCustomIngredients(RecommendationRequestDTO request) {
        String url = recommendationServiceUrl + "/api/recommendations?userID=" + request.getUserId();
        logger.info("Calling recommendation service with custom ingredients: {}", url);
        
        return restTemplate.postForObject(url, request, RecommendationResponseDTO.class);
    }
}