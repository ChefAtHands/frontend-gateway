package com.chefathands.gateway.controller;

import com.chefathands.gateway.dto.UserIngredientDTO;
import com.chefathands.gateway.service.UserIngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/ingredients")
public class UserIngredientController {
    
    private final UserIngredientService userIngredientService;
    
    public UserIngredientController(UserIngredientService userIngredientService) {
        this.userIngredientService = userIngredientService;
    }
    
    @GetMapping
    public ResponseEntity<List<UserIngredientDTO>> getUserIngredients(@PathVariable Integer userId) {
        List<UserIngredientDTO> ingredients = userIngredientService.getUserIngredients(userId);
        return ResponseEntity.ok(ingredients);
    }
    
    @PostMapping
    public ResponseEntity<UserIngredientDTO> addIngredient(
            @PathVariable Integer userId,
            @RequestBody UserIngredientDTO ingredient) {
        UserIngredientDTO added = userIngredientService.addIngredient(userId, ingredient);
        return ResponseEntity.ok(added);
    }

    @PatchMapping("/{userIngredientId}")
    public ResponseEntity<UserIngredientDTO> updateIngredient(
            @PathVariable Integer userId,
            @PathVariable Integer userIngredientId,
            @RequestBody UserIngredientDTO ingredient) {
        UserIngredientDTO updated = userIngredientService.updateIngredient(userId, userIngredientId, ingredient);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{userIngredientId}")
    public ResponseEntity<Void> removeIngredient(
            @PathVariable Integer userId,
            @PathVariable Integer userIngredientId) {
        userIngredientService.removeIngredient(userId, userIngredientId);
        return ResponseEntity.noContent().build();
    }
}