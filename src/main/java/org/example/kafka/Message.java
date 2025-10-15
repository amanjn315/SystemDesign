package org.example.kafka;

/**
 * @author amanjain
 **/
public class Message {
    private final String message;

    public Message(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

    @Override
    public String toString(){
        return "Message{" +
                "message='" + message + '\'' +
                '}';
    }
}
