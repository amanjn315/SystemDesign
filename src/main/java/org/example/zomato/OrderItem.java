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

    public OrderItem(String menuItemId, int quanity, BigDecimal priceAtPurchase) {
        this.menuItemId = menuItemId;
        this.quanity = quanity;
        this.priceAtPurchase = priceAtPurchase;
        this.id = UUID.randomUUID().toString();
    }
}
