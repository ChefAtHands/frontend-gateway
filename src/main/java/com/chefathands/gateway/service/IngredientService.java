package com.chefathands.gateway.service;

import com.chefathands.gateway.client.IngredientServiceClient;
import com.chefathands.gateway.client.LoggingServiceClient;
import com.chefathands.gateway.dto.IngredientDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    
    private final IngredientServiceClient ingredientServiceClient;
    private final LoggingServiceClient loggingServiceClient;
    
    public IngredientService(IngredientServiceClient ingredientServiceClient,
                            LoggingServiceClient loggingServiceClient) {
        this.ingredientServiceClient = ingredientServiceClient;
        this.loggingServiceClient = loggingServiceClient;
    }
    
    public List<IngredientDTO> getAllIngredients() {
        List<IngredientDTO> ingredients = ingredientServiceClient.getAllIngredients();
        // Optional: log this action
        // loggingServiceClient.log("INGREDIENTS_FETCHED", null, "Fetched " + ingredients.size() + " ingredients");
        return ingredients;
    }
    
    public IngredientDTO getIngredientById(Integer id) {
        IngredientDTO ingredient = ingredientServiceClient.getIngredientById(id);
        return ingredient;
    }
    
    public List<IngredientDTO> searchIngredientsByName(String name) {
        List<IngredientDTO> ingredients = ingredientServiceClient.searchIngredientsByName(name);
        // Optional: log search action
        // loggingServiceClient.log("INGREDIENT_SEARCH", null, "Searched for: " + name + ", found " + ingredients.size() + " results");
        return ingredients;
    }
    
    public IngredientDTO createIngredient(IngredientDTO ingredient) {
        IngredientDTO created = ingredientServiceClient.createIngredient(ingredient);
        //loggingServiceClient.log("INGREDIENT_CREATED", null, "Created ingredient: " + created.getName());
        return created;
    }
    
    public void deleteIngredient(Integer id) {
        ingredientServiceClient.deleteIngredient(id);
        //loggingServiceClient.log("INGREDIENT_DELETED", null, "Deleted ingredient ID: " + id);
    }
}
