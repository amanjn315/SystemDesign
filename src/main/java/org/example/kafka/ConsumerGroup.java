package org.example.kafka;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author amanjain
 **/
public class ConsumerGroup {
    private final String id;
    private final List<Consumer> consumers;
    private final Map<String, AtomicInteger> topicOffsets;
    private final ExecutorService executorService;

    public ConsumerGroup(String id, int consumerCount, Queue queue) {
        this.id = id;
        this.consumers = new CopyOnWriteArrayList<>();
        this.topicOffsets = new ConcurrentHashMap<>();
        this.executorService = Executors.newFixedThreadPool(consumerCount);

        for (int i = 0; i < consumerCount; i++) {
            Consumer consumer = new Consumer("Consumer" + (i + 1), this, queue);
            consumers.add(consumer);
            executorService.submit(consumer);
        }
    }

    public String getId() {
        return id;
    }

    public List<Consumer> getConsumers(){
        return this.consumers;
    }

    // This is the core logic for ensuring "consume once" behavior
    public AtomicInteger getTopicOffset(String topicName) {
        // computeIfAbsent ensures that the AtomicInteger is created only once for each topic
        return topicOffsets.computeIfAbsent(topicName, k -> new AtomicInteger(0));
    }

    public void shutdown() {
        executorService.shutdownNow();
    }
}
