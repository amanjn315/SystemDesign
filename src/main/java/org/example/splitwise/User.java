package org.example.splitwise;

/**
 * @author amanjain
 **/
public class User {
    String userId;
    String name;
    String email;
    String number;

    public User(String userId, String name, String email, String number) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.number = number;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
