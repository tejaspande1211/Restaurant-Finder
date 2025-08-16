package com.example.restaurant.exception;

public class RestaurantNotFoundException extends Throwable {
    public RestaurantNotFoundException(String restaurantName) {
        super(restaurantName);
    }
}
