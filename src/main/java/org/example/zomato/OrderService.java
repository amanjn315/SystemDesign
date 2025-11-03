package org.example.zomato;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author amanjain
 **/
public class OrderService {
    private Map<String, Order> orderMap;
    private Map<String, Customer> customerMap;
    private Map<String, DeliveryPartner> deliveryPartnerMap;
    private Map<String, Restaurant> restaurantMap;

    public Order placeOrder(Customer customer, Cart cart, Address deliveryAddress){
        if (cart.getCartItems().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty.");
        }
        for (CartItem cartItem : cart.getCartItems().values()) {
            if (!cartItem.getMenuItem().isAvailable()) {
                throw new IllegalArgumentException("Item not available: " + cartItem.getMenuItem().getName());
            }
        }

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (CartItem cartItem : cart.getCartItems().values()) {
            // We assume OrderItem constructor is smart and copies data
            OrderItem orderItem = new OrderItem(cartItem);
            orderItems.add(orderItem);
            totalPrice = totalPrice.add(orderItem.getPriceAtPurchase().multiply(BigDecimal.valueOf(orderItem.getQuanity())));
        }

        Order order = new Order(
                orderItems,
                totalPrice,
                customer.getId(),
                cart.getRestaurantId(),
                deliveryAddress.toString()
        );

        orderMap.put(order.getId(), order);
        cart.clearCart();
        return order;
    }

    public void restaurantAcceptOrder(String orderId, String restaurantId){
        Order order = orderMap.get(orderId);
        if (order == null) {
            System.out.println("Invalid Order ID: " + orderId);
            return;
        }
        if (!order.getRestaurantId().equals(restaurantId)) {
            System.out.println("Error: Restaurant " + restaurantId + " is not authorized to accept this order.");
            return;
        }
        if (order.getOrderStatus() == OrderStatus.PENDING) {
            order.setOrderStatus(OrderStatus.PREPARING);
        } else {
            System.out.println("Invalid request: Order is not in PENDING state.");
        }
    }

    public void restaurantOrderPrepared(String orderId){
        Order order = orderMap.get(orderId);

        if (order == null) {
            System.out.println("Invalid Order ID: " + orderId);
            return;
        }

        if(order.getOrderStatus() == OrderStatus.PREPARING){
            order.setOrderStatus(OrderStatus.READY_FOR_PICKUP);
        } else {
            System.out.println("Invalid request for the order with order id [" + orderId + "]");
        }
    }

    public void assignDeliveryPartner(String orderId, String partnerId){
        Order order = orderMap.get(orderId);

        if (order == null) {
            System.out.println("Invalid Order ID: " + orderId);
            return;
        }

        if(order.getOrderStatus() == OrderStatus.READY_FOR_PICKUP){
            order.setDeliveryPartnerId(partnerId);
            order.setOrderStatus(OrderStatus.ASSIGNED);
        } else {
            System.out.println("Invalid request for the order with order id [" + orderId + "]");
        }
    }

    public void cancelOrder(String orderId, String customerId){
        Order order = orderMap.get(orderId);

        if (order == null) {
            System.out.println("Invalid Order ID: " + orderId);
            return;
        }

        if (!order.getCustomerId().equals(customerId)) {
            System.out.println("Error: Customer " + customerId + " is not authorized to cancel this order.");
            return;
        }

        if (order.getOrderStatus() == OrderStatus.PENDING) {
            order.setOrderStatus(OrderStatus.CANCELLED);
        } else {
            System.out.println("Invalid request: Order can only be cancelled while in PENDING state.");
        }
    }
}
