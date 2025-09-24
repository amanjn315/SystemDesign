package org.example.lru;

/**
 * @author amanjain
 **/
public class Node {
    String query;
    String results;
    Node prev;
    Node next;

    public Node(String query, String results){
        this.query = query;
        this.results = results;
    }
}
