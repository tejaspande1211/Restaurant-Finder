package com.example.restaurant.entity;

import com.example.restaurant.exception.ItemNotFoundException;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Entity
@Table(name = "restaurant")
@NoArgsConstructor
public class Restaurant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    @Column(name = "opening_time")
    public LocalTime openingTime;
    @Column(name = "closing_time")
    public LocalTime closingTime;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> menu = new ArrayList<>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        LocalTime currentTime = getCurrentTime();
        return currentTime.isAfter(openingTime) && currentTime.isBefore(closingTime);
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws ItemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new ItemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("com.example.restaurant.entity.Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    public Integer getCost(List<String> selectedItemNames){
        Integer cost = 0;
        for(String selectedItemName : selectedItemNames){
            Item item = findItemByName(selectedItemName);
            if(nonNull(item)){
                cost += item.getPrice();
            }
        }
        return cost;
    }
}
