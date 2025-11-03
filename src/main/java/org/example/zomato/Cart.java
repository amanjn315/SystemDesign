package org.example.zomato;

import java.util.*;

/**
 * @author amanjain
 **/
public class Cart {
    private final String cartId;
    // Maps menuItemId to CartItem
    private Map<String, CartItem> cartItems;
    private final String customerId;
    private String restaurantId;

    public Cart(String customerId) {
        this.customerId = customerId;
        this.cartItems = new HashMap<>();
        this.restaurantId = null;
        this.cartId = UUID.randomUUID().toString();
    }

    public void addCartItem(MenuItem menuItem, int quantity){
        String itemRestaurantId = menuItem.getRestaurantId();

        if(this.restaurantId == null){
            this.restaurantId = itemRestaurantId;
        }

        if (!this.restaurantId.equals(itemRestaurantId)) {
            // In a real app, you'd throw a custom exception
            System.out.println("Error: Cannot add items from a different restaurant.");
            return;
        }

        String menuItemId = menuItem.getId();

        if(cartItems.containsKey(menuItemId)){
            cartItems.get(menuItemId).updateQuantity(quantity);
        } else {
            CartItem cartItem = new CartItem(menuItem, quantity);
            cartItems.put(menuItemId, cartItem);
        }
    }

    public void removeCartItem(String menuItemId){
        cartItems.remove(menuItemId);

        // If the cart is now empty, reset the restaurant lock
        if (cartItems.isEmpty()) {
            this.restaurantId = null;
        }
    }

    public Map<String, CartItem> getCartItems() {
        return cartItems;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void clearCart() {
        this.cartItems = new HashMap<>();
        this.restaurantId = null;
    }
}
