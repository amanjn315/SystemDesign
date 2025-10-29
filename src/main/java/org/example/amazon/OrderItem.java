package org.example.amazon;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author amanjain
 **/
public class OrderItem {
    private final String orderItemId;
    private final String productId;
    private final String productNameAtPurchase;
    private final Integer quantity;
    private final BigDecimal priceAtPurchase;
    private LocalDateTime createdAt;

    public OrderItem(Product product, Integer quantity) {
        this.productId = product.getProductId();
        this.productNameAtPurchase = product.getProductName();
        this.priceAtPurchase = product.getPrice(); // <-- Copy the price
        this.quantity = quantity;

        this.createdAt = LocalDateTime.now();
        this.orderItemId = UUID.randomUUID().toString();
    }

    public String getProductId() { return productId; }
    public Integer getQuantity() { return quantity; }
    public BigDecimal getPriceAtPurchase() { return priceAtPurchase; }

    public BigDecimal getOrderItemTotalPrice(){
        return this.priceAtPurchase.multiply(BigDecimal.valueOf(this.quantity));
    }
}
