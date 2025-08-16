package com.example.restaurant.controller;

import com.example.restaurant.exception.RestaurantNotFoundException;
import com.example.restaurant.service.RestaurantService;
import com.example.restaurant.entity.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/restaurants")
@AllArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getRestaurants();
    }

    @PostMapping
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.addRestaurant(restaurant);
    }

    @DeleteMapping("/{restaurantName}")
    public void removeRestaurant(@PathVariable String restaurantName) throws RestaurantNotFoundException {
        restaurantService.removeRestaurant(restaurantName);
    }
}
