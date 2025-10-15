package org.example.kafka;

/**
 * @author amanjain
 **/
public class Producer {
    private final String name;
    private final Queue queue;

    public Producer(String name, Queue queue) {
        this.name = name;
        this.queue = queue;
    }

    public void send(String topicName, Message message) {
        System.out.println("Producer [" + name + "] publishing to topic [" + topicName + "]: " + message.getMessage());
        queue.produce(topicName, message);
    }
}
