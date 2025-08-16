package com.example.restaurant.exception;

public class ItemNotFoundException extends Throwable {
    public ItemNotFoundException(String itemName) {
        super(itemName);
    }
}
