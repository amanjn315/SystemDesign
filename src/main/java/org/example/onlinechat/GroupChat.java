package org.example.onlinechat;

import java.util.Arrays;

/**
 * @author amanjain
 **/
public class GroupChat extends Chat {
    public GroupChat(int chatId) {
        super(chatId);
    }

    public void addUser(User user){
        if(super.users.contains(user)){
            System.out.println("User " + user.getName() + " already exist in the group chat.");
            return;
        }
        super.users.add(user);
        System.out.println("User " + user.getName() + " successfully added to the group chat.");
        return;
    }

    public void removeUser(User user){
        if(!super.users.contains(user)){
            System.out.println("User " + user.getName() + " doesn't exist in the group chat.");
            return;
        }
        super.users.remove(user);
        System.out.println("User " + user.getName() + " successfully removed from the group chat.");
        return;
    }
}
