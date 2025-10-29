package org.example.amazon;

import java.util.List;
import java.util.UUID;

/**
 * @author amanjain
 **/
public class User {
    private final String userId;
    private List<Address> addresses;
    private final ShoppingCart shoppingCart;

    public User() {
        this.userId = UUID.randomUUID().toString();
        shoppingCart = new ShoppingCart(userId);
    }

    public String getUserId() {
        return userId;
    }

    public ShoppingCart getShoppingCart(){
        return this.shoppingCart;
    }
}
