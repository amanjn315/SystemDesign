package org.example.zomato;

/**
 * @author amanjain
 **/
public enum OrderStatus {
    PENDING,          // Initial state, waiting for restaurant to accept
    REJECTED,         // Restaurant rejected the order
    PREPARING,        // Restaurant accepted and is preparing the food
    READY_FOR_PICKUP, // Food is ready, waiting for a delivery partner
    ASSIGNED,         // A delivery partner has been assigned (but not yet accepted)
    PICKED_UP,        // Delivery partner has accepted and picked up the food
    OUT_FOR_DELIVERY, // Delivery partner is on the way
    DELIVERED,        // Order successfully delivered
    CANCELLED         // Customer cancelled the order (only allowed in PENDING)
}
