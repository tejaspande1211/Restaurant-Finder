package entity;

import com.example.restaurant.entity.Restaurant;
import com.example.restaurant.exception.ItemNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;

    @BeforeEach
    void setup(){
        createRestaurant();
    }

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        restaurant.openingTime = LocalTime.now().minusMinutes(60);
        restaurant.closingTime = LocalTime.now().plusMinutes(60);
        assertTrue(restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        restaurant.openingTime = LocalTime.now().minusMinutes(60);
        restaurant.closingTime = LocalTime.now().minusMinutes(1);
        assertFalse(restaurant.isRestaurantOpen());
    }

    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws ItemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(ItemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }

    private void createRestaurant() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,
                closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }


    @Test
    void cost_value_should_be_388(){
        Integer actualCost = restaurant.getCost(Arrays.asList("Sweet corn soup", "Vegetable lasagne"));
        assertEquals(388, actualCost);
    }
}