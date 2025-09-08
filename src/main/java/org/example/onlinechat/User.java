package org.example.onlinechat;

import java.util.HashMap;

/**
 * @author amanjain
 **/
public class User {
    int userId;
    String name;
    HashMap<Integer, User> friends;
    HashMap<Integer, User> friendsToPrivateChats;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
