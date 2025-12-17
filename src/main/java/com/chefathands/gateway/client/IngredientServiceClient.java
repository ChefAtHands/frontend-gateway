package com.chefathands.gateway.client;

import com.chefathands.gateway.dto.IngredientDTO;
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
    
    // ========== Ingredient CRUD ==========
    
    public List<IngredientDTO> getAllIngredients() {
        String url = ingredientServiceUrl + "/api/ingredients";
        ResponseEntity<List<IngredientDTO>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<IngredientDTO>>() {}
        );
        return response.getBody();
    }
    
    public IngredientDTO getIngredientById(Integer id) {
        String url = ingredientServiceUrl + "/api/ingredients/" + id;
        return restTemplate.getForObject(url, IngredientDTO.class);
    }
    
    public List<IngredientDTO> searchIngredientsByName(String name) {
        String url = ingredientServiceUrl + "/api/ingredients/search?name=" + name;
        ResponseEntity<List<IngredientDTO>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<IngredientDTO>>() {}
        );
        return response.getBody();
    }
    
    public IngredientDTO createIngredient(IngredientDTO ingredient) {
        String url = ingredientServiceUrl + "/api/ingredients";
        return restTemplate.postForObject(url, ingredient, IngredientDTO.class);
    }
    
    public void deleteIngredient(Integer id) {
        String url = ingredientServiceUrl + "/api/ingredients/" + id;
        restTemplate.delete(url);
    }
    
    // ========== User Ingredients (Pantry) ==========
    
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
    
    public UserIngredientDTO addIngredient(Integer userId, UserIngredientDTO userIngredient) {
       
        String url = ingredientServiceUrl + "/api/users/" + userId + "/ingredients";
        return restTemplate.postForObject(url, userIngredient, UserIngredientDTO.class);
    }
    
    public UserIngredientDTO updateIngredient(Integer userId, Integer userIngredientId, UserIngredientDTO userIngredient) {
        
        String url = ingredientServiceUrl + "/api/users/" + userId + "/ingredients/" + userIngredientId;
        
        // Send only quantity in request body (matches controller expectation)
        Map<String, Object> request = new HashMap<>();
        request.put("quantity", userIngredient.getQuantity());
        
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request);
        ResponseEntity<UserIngredientDTO> response = restTemplate.exchange(
            url,
            HttpMethod.PATCH,
            entity,
            UserIngredientDTO.class
        );
        return response.getBody();
    }
    
    public void removeIngredient(Integer userId, Integer userIngredientId) {
        
        String url = ingredientServiceUrl + "/api/users/" + userId + "/ingredients/" + userIngredientId;
        restTemplate.delete(url);
    }
}