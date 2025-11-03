package org.example.zomato;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author amanjain
 **/
public class OrderItem {
    private final String id;
    private final String menuItemId;
    private final int quanity;
    private final BigDecimal priceAtPurchase;

    public OrderItem(CartItem cartItem) {
        this.menuItemId = cartItem.getMenuItem().getId();
        this.quanity = cartItem.getQuantity();
        this.priceAtPurchase = cartItem.getMenuItem().getPrice();
        this.id = UUID.randomUUID().toString();
    }

    public int getQuanity() {
        return quanity;
    }

    public BigDecimal getPriceAtPurchase() {
        return priceAtPurchase;
    }
}
