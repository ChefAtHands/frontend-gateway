package com.chefathands.gateway.dto;

public class NutritionDTO {
    private Integer calories;
    private Integer protein;
    private Integer carbs;
    private Integer fat;
    
    // Getters and setters
    public Integer getCalories() { return calories; }
    public void setCalories(Integer calories) { this.calories = calories; }
    
    public Integer getProtein() { return protein; }
    public void setProtein(Integer protein) { this.protein = protein; }
    
    public Integer getCarbs() { return carbs; }
    public void setCarbs(Integer carbs) { this.carbs = carbs; }
    
    public Integer getFat() { return fat; }
    public void setFat(Integer fat) { this.fat = fat; }
}
