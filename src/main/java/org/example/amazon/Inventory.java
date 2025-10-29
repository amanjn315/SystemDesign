package org.example.amazon;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author amanjain
 **/
public class Inventory {
    private final String inventoryId;
    private final ConcurrentHashMap<String, AtomicInteger> productStock;

    public Inventory() {
        this.inventoryId = UUID.randomUUID().toString();
        this.productStock = new ConcurrentHashMap<>();
    }

    public void addProductQuantity(Product p, int quantity){
        String productId = p.getProductId();
        // Get the current stock for this product, or create it if it doesn't exist
        productStock.putIfAbsent(productId, new AtomicInteger(0));
        // Atomically add the new quantity
        productStock.get(productId).addAndGet(quantity);
    }

    public boolean reduceProductQuantity(Product p, int quantity){
        String productId = p.getProductId();

        // Ensure the product exists in our stock map
        productStock.putIfAbsent(productId, new AtomicInteger(0));
        AtomicInteger currentStock = productStock.get(productId);

        // Loop using "compare-and-set" (CAS)
        // This is a lock-free way to do updates
        while (true) {
            int current = currentStock.get();
            if (current < quantity) {
                // Not enough stock
                return false;
            }
            int next = current - quantity;
            // "Compare-And-Set"
            // If the current value is STILL `current`, then update it to `next`
            if (currentStock.compareAndSet(current, next)) {
                // Success!
                return true;
            }
            // If it failed, another thread changed the stock.
            // The loop will just try again with the new value.
        }
    }

    public int getProductQuantity(Product product) {
        AtomicInteger quantity = productStock.getOrDefault(product.getProductId(), new AtomicInteger(0));
        return quantity.get();
    }
}
