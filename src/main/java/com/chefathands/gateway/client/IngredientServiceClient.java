package com.chefathands.gateway.client;

import com.chefathands.gateway.dto.UserIngredientDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class IngredientServiceClient {
    
    private final RestTemplate restTemplate;
    
    @Value("${services.ingredient.url}")
    private String ingredientServiceUrl;
    
    public IngredientServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public List<UserIngredientDTO> getUserIngredients(Integer userId) {
        String url = ingredientServiceUrl + "/api/users/" + userId + "/ingredients";
        ResponseEntity<List<UserIngredientDTO>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<UserIngredientDTO>>() {}
        );
        return response.getBody();
    }
    
    public UserIngredientDTO addIngredient(Integer userId, UserIngredientDTO ingredient) {
        String url = ingredientServiceUrl + "/api/users/" + userId + "/ingredients";
        return restTemplate.postForObject(url, ingredient, UserIngredientDTO.class);
    }

    public UserIngredientDTO updateIngredient(Integer userId, Integer userIngredientId, UserIngredientDTO ingredient) {
        try {
            String url = ingredientServiceUrl + "/api/users/" + userId + "/ingredients/" + userIngredientId;
            
            System.out.println("Request Quantity: " + ingredient.getQuantity());
            
            // Create request body with ONLY "quantity" field
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("quantity", ingredient.getQuantity());
            
            System.out.println("Request Body: " + requestBody);
            
            // Create HTTP entity with the Map
            HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody);
            
            // Send PATCH request
            ResponseEntity<UserIngredientDTO> response = restTemplate.exchange(
                url, 
                HttpMethod.PATCH, 
                request, 
                UserIngredientDTO.class
            );
            
            return response.getBody();
        } catch (Exception e) {
            System.err.println("Exception Type: " + e.getClass().getName());
            System.err.println("Exception Message: " + e.getMessage());
            e.printStackTrace();
            throw e; // Re-throw to see it in GlobalExceptionHandler
        }
    }
    
    public void removeIngredient(Integer userId, Integer userIngredientId) {
        String url = ingredientServiceUrl + "/api/users/" + userId + "/ingredients/" + userIngredientId;
        restTemplate.delete(url);
    }
}