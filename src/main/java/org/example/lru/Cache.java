package org.example.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * @author amanjain
 **/
public class Cache {
    private final int MAX_SIZE;
    private int size;
    private final Map<String, Node> lookup;
    private final LinkedList linkedList;

    public Cache(int maxSize){
        this.MAX_SIZE = maxSize;
        this.size = 0;
        this.lookup = new HashMap<>();
        this.linkedList = new LinkedList();
    }

    public String get(String query){
        Node node = lookup.get(query);
        if(node == null){
            return null;
        }
        linkedList.moveToFront(node);
        return node.results;
    }

    public void set(String query, String results){
        Node node = lookup.get(query);
        if(node != null){
            node.results = results;
            linkedList.moveToFront(node);
        } else {
            if(size == MAX_SIZE){
                Node tail = linkedList.getTail();
                if(tail != null){
                    lookup.remove(tail.query);
                    linkedList.removeFromTail();
                }
            } else {
                size++;
            }
            Node newNode = new Node(query, results);
            lookup.put(query, newNode);
            linkedList.appendToFront(newNode);
        }
    }
}
