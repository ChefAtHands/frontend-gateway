package com.chefathands.gateway.dto;

import java.util.List;

public class RecommendationResponseDTO {
    private List<RecipeDTO> recipes;
    private int totalResults;
    
    // Getters and setters
    public List<RecipeDTO> getRecipes() { return recipes; }
    public void setRecipes(List<RecipeDTO> recipes) { this.recipes = recipes; }
    
    public int getTotalResults() { return totalResults; }
    public void setTotalResults(int totalResults) { this.totalResults = totalResults; }
}
