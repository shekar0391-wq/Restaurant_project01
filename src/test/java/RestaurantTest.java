import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    public void initializeRestaurantObject(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        initializeRestaurantObject();
        Restaurant spiedRestaurant = Mockito.spy(restaurant);
        Mockito.when(spiedRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("15:00:00"));
        System.out.println(spiedRestaurant.getCurrentTime());
        assertEquals(true, restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        initializeRestaurantObject();
        Restaurant spiedRestaurant = Mockito.spy(restaurant);
        Mockito.when(spiedRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("23:00:00"));
        assertFalse(restaurant.isRestaurantOpen());
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        initializeRestaurantObject();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        initializeRestaurantObject();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        initializeRestaurantObject();
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>Order Value<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void for_any_selected_items_order_value_should_not_be_equal_to_zero() {
        initializeRestaurantObject();
        List<String> itemNames = new ArrayList<>();
        itemNames.add("Sweet corn soup");
        itemNames.add("Vegetable lasagne");
        assertNotEquals(0,
                restaurant.displayOrderValue(itemNames));

    }

    @Test
    public void for_any_selected_items_order_value_should_be_calculated() {
        initializeRestaurantObject();
        List<String> itemNames = new ArrayList<>();
        itemNames.add("Sweet corn soup");
        itemNames.add("Vegetable lasagne");
        int orderValue;

        orderValue = restaurant.getItemPrice("Sweet corn soup");
        orderValue += restaurant.getItemPrice("Vegetable lasagne");

        assertEquals(orderValue,
                restaurant.displayOrderValue(itemNames));

    }

    //<<<<<<<<<<<<<<<<<<<<<<<Order Value>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}