package com.chefathands.gateway.controller;

import com.chefathands.gateway.dto.IngredientDTO;
import com.chefathands.gateway.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {
    
    private final IngredientService ingredientService;
    
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
    
    @GetMapping
    public ResponseEntity<List<IngredientDTO>> getAllIngredients() {
        List<IngredientDTO> ingredients = ingredientService.getAllIngredients();
        return ResponseEntity.ok(ingredients);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<IngredientDTO> getIngredientById(@PathVariable Integer id) {
        IngredientDTO ingredient = ingredientService.getIngredientById(id);
        return ResponseEntity.ok(ingredient);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<IngredientDTO>> searchIngredients(@RequestParam("name") String name) {
        List<IngredientDTO> ingredients = ingredientService.searchIngredientsByName(name);
        return ResponseEntity.ok(ingredients);
    }
    
    @PostMapping
    public ResponseEntity<IngredientDTO> createIngredient(@RequestBody IngredientDTO ingredient) {
        IngredientDTO created = ingredientService.createIngredient(ingredient);
        return ResponseEntity.ok(created);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Integer id) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.noContent().build();
    }
}