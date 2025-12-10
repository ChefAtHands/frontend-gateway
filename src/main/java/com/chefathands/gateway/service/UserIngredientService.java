package com.chefathands.gateway.service;

import com.chefathands.gateway.client.IngredientServiceClient;
import com.chefathands.gateway.client.LoggingServiceClient;
import com.chefathands.gateway.dto.UserIngredientDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserIngredientService {
    
    private final IngredientServiceClient ingredientServiceClient;
    private final LoggingServiceClient loggingServiceClient;
    
    public UserIngredientService(IngredientServiceClient ingredientServiceClient,
                                 LoggingServiceClient loggingServiceClient) {
        this.ingredientServiceClient = ingredientServiceClient;
        this.loggingServiceClient = loggingServiceClient;
    }
    
    public List<UserIngredientDTO> getUserIngredients(Integer userId) {
        List<UserIngredientDTO> ingredients = ingredientServiceClient.getUserIngredients(userId);
        //loggingServiceClient.log("USER_INGREDIENTS_FETCHED", userId, "Fetched " + ingredients.size() + " ingredients");
        return ingredients;
    }
    
    public UserIngredientDTO addIngredient(Integer userId, UserIngredientDTO ingredient) {
        UserIngredientDTO added = ingredientServiceClient.addIngredient(userId, ingredient);
        //loggingServiceClient.log("INGREDIENT_ADDED", userId, "Added ingredient: " + ingredient.getIngredientId());
        return added;
    }
    
    public UserIngredientDTO updateIngredient(Integer userId, Integer userIngredientId, UserIngredientDTO ingredient) {
        UserIngredientDTO updated = ingredientServiceClient.updateIngredient(userId, userIngredientId, ingredient);
        //loggingServiceClient.log("INGREDIENT_UPDATED", userId, "Updated user_ingredient: " + userIngredientId + " to quantity: " + ingredient.getQuantity());
        return updated;
    }
    
    public void removeIngredient(Integer userId, Integer userIngredientId) {
        ingredientServiceClient.removeIngredient(userId, userIngredientId);
        //loggingServiceClient.log("INGREDIENT_REMOVED", userId, "Removed user_ingredient: " + userIngredientId);
    }
}