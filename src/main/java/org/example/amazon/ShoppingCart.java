package org.example.amazon;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author amanjain
 **/
public class ShoppingCart {
    private final String shoppingCartId;
    private Map<String, CartItem> cartItemMap;
    private final String userId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ShoppingCart(String userId) {
        this.shoppingCartId = UUID.randomUUID().toString();
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
        cartItemMap = new HashMap<>();
    }

    public void addCartItem(Product product, Integer quantity){
        if(cartItemMap.containsKey(product.getProductId())){
            CartItem cartItem = cartItemMap.get(product.getProductId());
            cartItem.setQuantity(quantity);
        } else {
            CartItem cartItem = new CartItem(product, quantity);
            cartItemMap.put(product.getProductId(), cartItem);
        }
        this.updatedAt = LocalDateTime.now();
    }

    public String getShoppingCartId() {
        return shoppingCartId;
    }

    public String getUserId() {
        return userId;
    }

    public Map<String, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void clearCart() {
        cartItemMap = new HashMap<>();
        updatedAt = LocalDateTime.now();
    }
}
