package org.example.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author amanjain
 **/
public class Consumer implements Runnable {
    private final String name;
    private final Queue queue;
    private final ConsumerGroup consumerGroup;
    List<Topic> topics = new ArrayList<>();

    public Consumer(String name, ConsumerGroup consumerGroup, Queue queue){
        this.name = name;
        this.consumerGroup = consumerGroup;
        this.queue = queue;
    }

    void subscribeToTopic(String topicName){
        Topic topic = queue.getTopicUsingTopicName(topicName);
        this.topics.add(topic);
    }

    public String getName(){
        return this.name;
    }

    @Override
    public void run(){
        System.out.println("Consumer [" + name + "] started.");
        try {
            while (!Thread.currentThread().isInterrupted()) {
                boolean messageFoundInLoop = false;
                for(Topic topic : topics){

                    AtomicInteger groupOffset = consumerGroup.getTopicOffset(topic.getName());

                    int currentOffset = groupOffset.get();
                    Message message = queue.peekMessage(topic.getName(), currentOffset);

                    if(message != null){
                        if (groupOffset.compareAndSet(currentOffset, currentOffset + 1)) {
                            // SUCCESS! We claimed this offset.
                            System.out.println(name + " from group [" + consumerGroup.getId() + "] consumed: " + message.getMessage());
                            messageFoundInLoop = true;
                            Thread.sleep(100);
                            break;
                        }
                    }
                }

                if (!messageFoundInLoop) {
                    Thread.sleep(100);
                }
            }
        } catch (InterruptedException e){
            System.out.println("Consumer [" + name + "] was interrupted.");
            Thread.currentThread().interrupt();
        }
    }
}
