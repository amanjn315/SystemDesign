package org.example.onlinechat;

import java.time.LocalDateTime;

/**
 * @author amanjain
 **/
public class Message {
    String message;
    LocalDateTime timestamp;
    User user;

    public Message(String message, User user) {
        this.user = user;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }
}
