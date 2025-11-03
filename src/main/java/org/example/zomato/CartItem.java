package org.example.zomato;

import java.util.UUID;

/**
 * @author amanjain
 **/
public class CartItem {
    private final String id;
    private final MenuItem menuItem;
    private int quantity;

    public CartItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.id = UUID.randomUUID().toString();
    }

    public void updateQuantity(int quantity){
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }
}
