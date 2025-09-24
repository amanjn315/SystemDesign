package org.example.lru;

/**
 * @author amanjain
 **/
public class LinkedList {
    private Node head;
    private Node tail;

    public Node getTail(){
        return this.tail;
    }

    public void moveToFront(Node node){
        if(node == head)
            return;

        if(node.prev != null){
            node.prev.next = node.next;
        }
        if(node.next != null){
            node.next.prev = node.prev;
        }
        if(node == tail){
            tail = node.prev;
        }

        node.next = head;
        node.prev = null;
        if(head != null){
            head.prev = node;
        }
        head = node;

        if(tail == null){
            tail = head;
        }
    }

    public void appendToFront(Node node){
        if(head == null){
            head = node;
            tail = node;
        } else {
            head.prev = node;
            node.next = head;
            head = node;
        }
    }

    public void removeFromTail(){
        if(tail == null){
            return;
        }
        if(head == tail){
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
    }
}
