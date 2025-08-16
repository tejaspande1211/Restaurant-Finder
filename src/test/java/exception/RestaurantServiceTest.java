package exception;

import com.example.restaurant.entity.Item;
import com.example.restaurant.entity.Restaurant;
import com.example.restaurant.exception.RestaurantNotFoundException;
import com.example.restaurant.repository.RestaurantRepository;
import com.example.restaurant.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RestaurantServiceTest {

    public static final String RESTAURANT_NAME = "Amelie's cafe";
    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantService restaurantService;

    Restaurant restaurant = new Restaurant(RESTAURANT_NAME, "French",
            LocalTime.parse("09:00"), LocalTime.parse("22:00"));


    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws RestaurantNotFoundException {
        when(restaurantRepository.findByName(RESTAURANT_NAME)).thenReturn(java.util.Optional.of(restaurant));
        Restaurant actualRestaurantResult = restaurantService.findRestaurantByName(RESTAURANT_NAME);
        assertEquals(restaurant.getName(), actualRestaurantResult.getName());
        assertEquals(restaurant.getMenu(), actualRestaurantResult.getMenu());
        assertEquals(restaurant.openingTime, actualRestaurantResult.openingTime);
        assertEquals(restaurant.closingTime, actualRestaurantResult.closingTime);
        assertEquals(restaurant.isRestaurantOpen(), actualRestaurantResult.isRestaurantOpen());
    }

    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception(){
        assertThrows(RestaurantNotFoundException.class, () -> restaurantService.findRestaurantByName("Dominos"));
    }

    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws RestaurantNotFoundException {
        when(restaurantRepository.findByName(RESTAURANT_NAME)).thenReturn(Optional.of(restaurant));
        restaurantService.removeRestaurant(RESTAURANT_NAME);
        verify(restaurantRepository).delete(restaurant);
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() {
        assertThrows(RestaurantNotFoundException.class,()-> restaurantService.removeRestaurant("Pantry d'or"));

    }

    @Test
   public void getting_all_restaurants_should_return_list_of_restaurants() {
        when(restaurantRepository.findAll()).thenReturn(Collections.singletonList(restaurant));
        java.util.List<Restaurant> actualRestaurants = restaurantService.getRestaurants();
        assertEquals(1, actualRestaurants.size());
        assertEquals(RESTAURANT_NAME, actualRestaurants.get(0).getName());
    }
}