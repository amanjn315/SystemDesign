package org.example.zomato;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * @author amanjain
 **/
public class Order {
    private final String id;
    private final List<OrderItem> orderItems;
    private final BigDecimal totalPrice;
    private final String customerId;
    private final String restaurantId;
    private final String deliveryAddressId;
    private String deliveryPartnerId;
    private OrderStatus orderStatus;

    public Order(List<OrderItem> orderItems, BigDecimal totalPrice, String customerId, String restaurantId, String deliveryAddressId) {
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.deliveryAddressId = deliveryAddressId;
        this.id = UUID.randomUUID().toString();
    }

    public void setDeliveryPartnerId(String deliveryPartnerId) {
        this.deliveryPartnerId = deliveryPartnerId;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
