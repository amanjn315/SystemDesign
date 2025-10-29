package org.example.amazon;

import java.math.BigDecimal;

/**
 * @author amanjain
 **/
public class Product {
    private final String productId;
    private final String productName;
    private final String productDesc;
    BigDecimal price;

    public Product(String productId, String productName, String productDesc) {
        this.productId = productId;
        this.productName = productName;
        this.productDesc = productDesc;
    }

    public void setPrice(BigDecimal price){
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public BigDecimal getPrice(){
        return this.price;
    }
}
