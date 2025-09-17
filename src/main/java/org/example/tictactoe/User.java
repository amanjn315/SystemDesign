package org.example.tictactoe;

/**
 * @author amanjain
 **/
public class User {
    String name;
    int id;
    Character pattern;

    public User(int id, Character pattern, String name) {
        this.name = name;
        this.id = id;
        this.pattern = pattern;
    }

    public String getName() {
        return name;
    }

    public Character getPattern() {
        return pattern;
    }
}
