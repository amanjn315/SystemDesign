package org.example.onlinechat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author amanjain
 **/
public class PrivateChat extends Chat {
    public PrivateChat(int chatId, User user1, User user2) {
        super(chatId, Arrays.asList(user1, user2));
    }
}
