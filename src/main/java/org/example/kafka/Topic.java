package org.example.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author amanjain
 **/
public class Topic {
    private final String name;
    private final List<Message> messages;
    private final AtomicInteger messageOffset;

    public Topic(String name) {
        this.name = name;
        this.messages = new CopyOnWriteArrayList<>();
        this.messageOffset = new AtomicInteger(0);
    }

    public void addMessage(Message message) {
        messages.add(message);
        messageOffset.incrementAndGet();
    }

    public String getName() {
        return name;
    }

    public int getCurrentOffset() {
        return messageOffset.get();
    }

    public Message peekMessage(int offset) {
        if (offset < messages.size()) {
            return messages.get(offset);
        }
        return null;
    }
}
