package org.example.amazon;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author amanjain
 **/
public class Order {
    private final String orderId;
    private final String userId;
    private final List<OrderItem> orderItems;
    private final Address shippingAddress;
    private OrderStatus orderStatus;
    private final BigDecimal totalOrderPrice;
    private final LocalDateTime createdAt;

    public Order(User user, List<OrderItem> orderItems, Address shippingAddress) {
        this.orderId = UUID.randomUUID().toString();
        this.userId = user.getUserId();
        this.orderItems = orderItems;
        this.shippingAddress = shippingAddress; // Store the copy
        this.orderStatus = OrderStatus.PENDING; // Set initial status
        this.createdAt = LocalDateTime.now();

        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : orderItems) {
            total = total.add(item.getOrderItemTotalPrice());
        }
        this.totalOrderPrice = total;
    }

    public BigDecimal getTotalOrderPrice() {
        return this.totalOrderPrice;
    }

    public String getOrderId() { return orderId; }
    public List<OrderItem> getOrderItems() { return orderItems; }
    public OrderStatus getOrderStatus() { return orderStatus; }
    public void setOrderStatus(OrderStatus status) { this.orderStatus = status; }
}
