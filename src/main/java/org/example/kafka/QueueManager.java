package org.example.kafka;

/**
 * @author amanjain
 **/
public class QueueManager {
    public static void main(String[] args) throws InterruptedException {
        Queue queue = new Queue();
        queue.createTopic("Topic1");
        queue.createTopic("Topic2");

        Producer producer1 = new Producer("Producer1", queue);
        Producer producer2 = new Producer("Producer2", queue);

        ConsumerGroup consumerGroup = new ConsumerGroup("ConsumerGroup1", 5, queue);

        Consumer consumer1 = consumerGroup.getConsumers().get(0);
        consumer1.subscribeToTopic("Topic1");
        consumer1.subscribeToTopic("Topic2");

        Consumer consumer2 = consumerGroup.getConsumers().get(1);
        consumer2.subscribeToTopic("Topic1");

        Consumer consumer3 = consumerGroup.getConsumers().get(2);
        consumer3.subscribeToTopic("Topic1");
        consumer3.subscribeToTopic("Topic2");

        Consumer consumer4 = consumerGroup.getConsumers().get(3);
        consumer4.subscribeToTopic("Topic1");
        consumer4.subscribeToTopic("Topic2");

        Consumer consumer5 = consumerGroup.getConsumers().get(4);
        consumer5.subscribeToTopic("Topic1");

        for (int i = 1; i <= 50; i++) {
            producer1.send("Topic1", new Message("OrderEvent_" + i));
            producer2.send("Topic2", new Message("OrderEvent_" + i));
        }

        producer1.send("Topic1", new Message("Message 1"));
        Thread.sleep(200); // Simulate some delay
        producer1.send("Topic1", new Message("Message 2"));
        Thread.sleep(200); // Simulate some delay
        producer2.send("Topic1", new Message("Message 3"));
        Thread.sleep(200); // Simulate some delay
        producer1.send("Topic2", new Message("Message 4"));
        Thread.sleep(200); // Simulate some delay
        producer2.send("Topic2", new Message("Message 5"));
        Thread.sleep(200); // Simulate some delay

        Thread.sleep(10000);

        System.out.println("\nShutting down consumer group...");
        consumerGroup.shutdown();
        System.out.println("System stopped.");
    }
}
