package org.example.onlinechat;

/**
 * @author amanjain
 **/
public class ChatService {
    static public void main(String[] args){
        User user1 = new User(1, "Aman");
        User user2 = new User(2, "Ansul");
        User user3 = new User(3, "Dev");
        User user4 = new User(4, "Akash");

        PrivateChat privateChat = new PrivateChat(1, user1, user2);
        GroupChat groupChat = new GroupChat(2);
        groupChat.addUser(user1);
        groupChat.addUser(user2);
        groupChat.addUser(user3);
        groupChat.addUser(user4);

        privateChat.addMessage(user1, "Hi, my name is Aman.");
        privateChat.addMessage(user2, "Hi, my name is Ansul.");
        privateChat.addMessage(user1, "How you doing today?");
        privateChat.addMessage(user2, "I am good, how about you?");
        privateChat.displayMessages();

        groupChat.addMessage(user1, "Hi All!");
        groupChat.addMessage(user2, "Hi Aman");
        groupChat.addMessage(user3, "Hi Aman and Ansul");
        groupChat.addMessage(user4, "Hi Aman, Ansul and Dev");
        groupChat.displayMessages();
    }
}
