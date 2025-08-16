package com.example.restaurant.service;

import com.example.restaurant.entity.Restaurant;
import com.example.restaurant.exception.RestaurantNotFoundException;
import com.example.restaurant.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Restaurant findRestaurantByName(String restaurantName) throws RestaurantNotFoundException {
        return restaurantRepository.findByName(restaurantName)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantName));
    }

    public Restaurant addRestaurant(Restaurant newRestaurant) {
        return restaurantRepository.save(newRestaurant);
    }

    public void removeRestaurant(String restaurantName) throws RestaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurantRepository.delete(restaurantToBeRemoved);
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }
}
