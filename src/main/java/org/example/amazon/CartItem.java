package org.example.amazon;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author amanjain
 **/
public class CartItem {
    private final String cartItemId;
    private final Product product;
    private Integer quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CartItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
        this.createdAt = LocalDateTime.now();
        this.cartItemId = UUID.randomUUID().toString();;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        this.updatedAt = LocalDateTime.now();
    }

    public String getCartItemId() {
        return cartItemId;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getCartItemTotalPrice(){
        BigDecimal price = this.product.getPrice();
        return price.multiply(BigDecimal.valueOf(this.quantity));
    }
}
