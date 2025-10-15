package org.example.kafka;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author amanjain
 **/
public class Queue {
    Map<String, Topic> topics = new ConcurrentHashMap<>();

    public void createTopic(String topicName) {
        topics.putIfAbsent(topicName, new Topic(topicName));
        System.out.println("Topic [" + topicName + "] created.");
    }

    public void produce(String topicName, Message message) {
        Topic topic = topics.get(topicName);
        if(topic != null){
            topic.addMessage(message);
        } else {
            System.out.println("WARN: Topic [" + topicName + "] does not exist.");
        }
    }

    public Topic getTopicUsingTopicName(String topicName){
        return topics.get(topicName);
    }

    public Message peekMessage(String topicName, int offset) {
        Topic topic = topics.get(topicName);
        if(topic != null){
            return topic.peekMessage(offset);
        }
        return null;
    }
}
