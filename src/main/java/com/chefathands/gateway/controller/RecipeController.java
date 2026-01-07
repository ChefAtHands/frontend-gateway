package com.chefathands.gateway.controller;

import com.chefathands.gateway.client.RecipeSearchServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    
    private final RecipeSearchServiceClient recipeSearchServiceClient;
    
    public RecipeController(RecipeSearchServiceClient recipeSearchServiceClient) {
        this.recipeSearchServiceClient = recipeSearchServiceClient;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipeById(@PathVariable String id) {
        Object recipe = recipeSearchServiceClient.getRecipeById(id);
        return ResponseEntity.ok(recipe);
    }
    
    @PostMapping("/search")
    public ResponseEntity<?> searchRecipes(@RequestBody Object searchRequest) {
        Object results = recipeSearchServiceClient.searchRecipes(searchRequest);
        return ResponseEntity.ok(results);
    }
}
