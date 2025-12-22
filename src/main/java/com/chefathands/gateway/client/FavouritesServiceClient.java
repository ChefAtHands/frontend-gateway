package com.chefathands.gateway.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FavouritesServiceClient {
    
    private final RestTemplate restTemplate;
    private final String favouritesServiceUrl;
    
    public FavouritesServiceClient(RestTemplate restTemplate,
                                  @Value("${favourites.service.url}") String favouritesServiceUrl) {
        this.restTemplate = restTemplate;
        this.favouritesServiceUrl = favouritesServiceUrl;
    }
    
    public String getFavourites(String userId) {
        String url = favouritesServiceUrl + "/users/" + userId + "/favourites";
        return restTemplate.getForObject(url, String.class);
    }
    
    public String addFavourite(String userId, String body) {
        String url = favouritesServiceUrl + "/users/" + userId + "/favourites";
        return restTemplate.postForObject(url, body, String.class);
    }
    
    public void removeFavourite(String userId, String recipeId) {
        String url = favouritesServiceUrl + "/users/" + userId + "/favourites/" + recipeId;
        restTemplate.delete(url);
    }
    
    public String isFavourite(String userId, String recipeId) {
        String url = favouritesServiceUrl + "/users/" + userId + "/favourites/" + recipeId + "/exists";
        return restTemplate.getForObject(url, String.class);
    }
}
