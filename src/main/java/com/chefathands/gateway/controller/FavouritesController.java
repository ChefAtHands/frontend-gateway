package com.chefathands.gateway.controller;

import com.chefathands.gateway.client.FavouritesServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{userId}/favourites")
public class FavouritesController {
    
    private final FavouritesServiceClient favouritesServiceClient;
    
    public FavouritesController(FavouritesServiceClient favouritesServiceClient) {
        this.favouritesServiceClient = favouritesServiceClient;
    }
    
    @GetMapping
    public ResponseEntity<String> getFavourites(@PathVariable String userId) {
        String response = favouritesServiceClient.getFavourites(userId);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping
    public ResponseEntity<String> addFavourite(@PathVariable String userId, @RequestBody String body) {
        String response = favouritesServiceClient.addFavourite(userId, body);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{recipeId}")
    public ResponseEntity<Void> removeFavourite(@PathVariable String userId, @PathVariable String recipeId) {
        favouritesServiceClient.removeFavourite(userId, recipeId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{recipeId}/exists")
    public ResponseEntity<String> isFavourite(@PathVariable String userId, @PathVariable String recipeId) {
        String response = favouritesServiceClient.isFavourite(userId, recipeId);
        return ResponseEntity.ok(response);
    }
}
