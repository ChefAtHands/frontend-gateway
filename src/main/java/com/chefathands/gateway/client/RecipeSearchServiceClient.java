package com.chefathands.gateway.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RecipeSearchServiceClient {
    
    private final RestTemplate restTemplate;
    private final String recipeSearchServiceUrl;
    
    public RecipeSearchServiceClient(
            RestTemplate restTemplate,
            @Value("${services.recipe-search.url}") String recipeSearchServiceUrl) {
        this.restTemplate = restTemplate;
        this.recipeSearchServiceUrl = recipeSearchServiceUrl;
    }
    
    public Object getRecipeById(String id) {
        String url = recipeSearchServiceUrl + "/api/recipes/" + id;
        return restTemplate.getForObject(url, Object.class);
    }
    
    public Object searchRecipes(Object searchRequest) {
        String url = recipeSearchServiceUrl + "/api/recipes/search";
        return restTemplate.postForObject(url, searchRequest, Object.class);
    }
}
