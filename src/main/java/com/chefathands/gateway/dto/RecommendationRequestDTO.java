package com.chefathands.gateway.dto;

import java.util.List;

public class RecommendationRequestDTO {
    private Integer userId;  // ADD THIS
    private List<IngredientDTO> ingredients;
    private String category;
    private Integer minProtein;
    private Integer maxCalories;
    
    // Getters and setters
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    
    public List<IngredientDTO> getIngredients() { return ingredients; }
    public void setIngredients(List<IngredientDTO> ingredients) { this.ingredients = ingredients; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public Integer getMinProtein() { return minProtein; }
    public void setMinProtein(Integer minProtein) { this.minProtein = minProtein; }
    
    public Integer getMaxCalories() { return maxCalories; }
    public void setMaxCalories(Integer maxCalories) { this.maxCalories = maxCalories; }
}