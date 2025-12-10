package com.chefathands.gateway.client;

import com.chefathands.gateway.dto.RecommendationRequestDTO;
import com.chefathands.gateway.dto.RecommendationResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RecommendationServiceClient {
    
    private final RestTemplate restTemplate;
    
    @Value("${services.recommendation.url}")
    private String recommendationServiceUrl;
    
    public RecommendationServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public RecommendationResponseDTO getRecommendations(Integer userId, String category, Integer minProtein) {
        UriComponentsBuilder builder = UriComponentsBuilder
            .fromHttpUrl(recommendationServiceUrl + "/api/recommendations")
            .queryParam("userId", userId);
        
        if (category != null) {
            builder.queryParam("category", category);
        }
        if (minProtein != null) {
            builder.queryParam("minProtein", minProtein);
        }
        
        return restTemplate.getForObject(builder.toUriString(), RecommendationResponseDTO.class);
    }
    
    public RecommendationResponseDTO getRecommendationsWithCustomIngredients(RecommendationRequestDTO request) {
        String url = recommendationServiceUrl + "/api/recommendations/custom";
        return restTemplate.postForObject(url, request, RecommendationResponseDTO.class);
    }
}
