package org.example.onlinechat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author amanjain
 **/
public class Chat {
    int chatId;
    List<User> users;
    List<Message> messages;

    public Chat(int chatId, List<User> users) {
        this.chatId = chatId;
        this.users = users;
        this.messages = new ArrayList<>();
    }

    public Chat(int chatId) {
        this.chatId = chatId;
        this.users = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public void addMessage(User user, String text){
        Message message = new Message(text, user);
        messages.add(message);
        return;
    }

    public void displayMessages(){
        if(messages.isEmpty()){
            System.out.println("No messages found.");
            return;
        }
        System.out.println("Chat Id " + chatId + " messages:");
        for(Message message : messages){
            System.out.println("User " + message.getUser().getName() + ": " + message.getMessage());
        }
        return;
    }
}
