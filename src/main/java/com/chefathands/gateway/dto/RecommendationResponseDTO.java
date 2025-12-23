package com.chefathands.gateway.dto;

import java.util.List;

public class RecommendationResponseDTO {
    private List<RecipeDTO> recipes;
    private Integer total;      // ADD THIS
    private Integer offset;     // ADD THIS
    private Integer limit;      // ADD THIS
    
    // Getters and setters
    public List<RecipeDTO> getRecipes() { return recipes; }
    public void setRecipes(List<RecipeDTO> recipes) { this.recipes = recipes; }
    
    public Integer getTotal() { return total; }
    public void setTotal(Integer total) { this.total = total; }
    
    public Integer getOffset() { return offset; }
    public void setOffset(Integer offset) { this.offset = offset; }
    
    public Integer getLimit() { return limit; }
    public void setLimit(Integer limit) { this.limit = limit; }
}