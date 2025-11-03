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
    private BigDecimal price;
    private boolean isAvailable;

    public MenuItem(String name, String description, BigDecimal price, boolean isAvailable) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
        this.id = UUID.randomUUID().toString();
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
