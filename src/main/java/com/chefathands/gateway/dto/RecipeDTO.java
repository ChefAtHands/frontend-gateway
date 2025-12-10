package com.chefathands.gateway.dto;

import java.util.List;

public class RecipeDTO {
    private Long id;
    private String title;
    private String image;
    private List<String> usedIngredients;
    private List<String> missedIngredients;
    private String instructions;
    private NutritionDTO nutrition;
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    
    public List<String> getUsedIngredients() { return usedIngredients; }
    public void setUsedIngredients(List<String> usedIngredients) { this.usedIngredients = usedIngredients; }
    
    public List<String> getMissedIngredients() { return missedIngredients; }
    public void setMissedIngredients(List<String> missedIngredients) { this.missedIngredients = missedIngredients; }
    
    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }
    
    public NutritionDTO getNutrition() { return nutrition; }
    public void setNutrition(NutritionDTO nutrition) { this.nutrition = nutrition; }
}
