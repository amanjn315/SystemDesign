package org.example.zomato;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author amanjain
 **/
public class MenuItem {
    private final String id;
    private final String name;
    private final String description;
    private final String restaurantId;
    private BigDecimal price;
    private boolean isAvailable;

    public MenuItem(String name, String description, BigDecimal price, String restaurantId, boolean isAvailable) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurantId = restaurantId;
        this.isAvailable = isAvailable;
        this.id = UUID.randomUUID().toString();
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getId() {
        return id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getName() {
        return this.name;
    }
}
