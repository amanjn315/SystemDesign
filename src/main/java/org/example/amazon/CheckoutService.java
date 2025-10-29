package org.example.amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author amanjain
 **/
public class CheckoutService {
    private final Inventory inventory;

    public CheckoutService(Inventory inventory) {
        this.inventory = inventory;
    }

    public Order placeOrder(User user, Address shippingAddress){
        ShoppingCart shoppingCart = user.getShoppingCart();
        Map<String, CartItem> cartItems = shoppingCart.getCartItemMap();

        // --- PHASE 1: VALIDATION ---
        // First, check if all items are in stock *before* changing anything.
        for (CartItem cartItem : cartItems.values()) {
            int requiredQuantity = cartItem.getQuantity();
            int availableStock = inventory.getProductQuantity(cartItem.getProduct());

            if (availableStock < requiredQuantity) {
                // Not enough stock. Fail the entire order.
                // In a real app, you'd throw a custom exception.
                System.out.println("Order failed: Not enough stock for " + cartItem.getProduct().getProductName());
                return null;
            }
        }

        // --- PHASE 2: COMMITMENT ---
        // All items are in stock! Now we can safely modify the inventory.
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cartItems.values()) {
            // 1. Actually reduce the stock in the inventory.
            // This can still fail in a high-concurrency system, but our atomic
            // reduceProductQuantity method should handle that.
            boolean success = inventory.reduceProductQuantity(cartItem.getProduct(), cartItem.getQuantity());
            if (!success) {
                // This is a rare race condition (someone bought the item in the
                // few milliseconds since we checked). We must roll back.
                // For simplicity here, we'll just fail. A more robust system
                // would roll back any *previous* reductions from this loop.
                System.out.println("Order failed due to a race condition on " + cartItem.getProduct().getProductName());
                // We *must* roll back any previous reductions in this loop (not shown for simplicity)
                return null;
            }

            // 2. Create the *immutable* OrderItem (using the corrected constructor).
            // This copies the product's price at the time of purchase.
            OrderItem orderItem = new OrderItem(cartItem.getProduct(), cartItem.getQuantity());
            orderItems.add(orderItem);
        }

        // 3. Create the final, immutable order
        Order order = new Order(user, orderItems, shippingAddress);

        // 4. Clear the user's shopping cart
        shoppingCart.clearCart();

        return order;
    }

}
