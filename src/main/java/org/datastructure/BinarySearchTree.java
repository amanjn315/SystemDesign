package org.datastructure;

/**
 * @author amanjain
 **/
public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {
    private Node<T> root;

    @Override
    public Tree<T> insert(T data) {
        root = insert(data, root);
        return this;
    }

    public Node<T> insert(T data, Node<T> node){
        if(node == null){
            return new Node<>(data);
        }
        if(data.compareTo(node.getData()) < 0){
            node.setLeftChild(insert(data, node.getLeftChild()));
        }
        else {
            node.setRightChild(insert(data, node.getRightChild()) );
        }
        return node;
    }

    @Override
    public void delete(T data) {
        root = delete(data, root);
    }

    public Node<T> delete(T data, Node<T> node){
        if(node == null){
            return null;
        }
        if(data.compareTo(node.getData()) < 0){
            node.setLeftChild(delete(data, node.getLeftChild()));
        }
        else if(data.compareTo(node.getData()) > 0) {
            node.setRightChild(delete(data, node.getRightChild()));
        } else {
            if(node.getLeftChild() == null){
                return node.getRightChild();
            } else if(node.getRightChild() == null){
                return node.getLeftChild();
            }
            node.setData(getMax(node.getLeftChild()));
            node.setLeftChild(delete(node.getData(), node.getLeftChild()));
        }
        return node;
    }

    @Override
    public void traverse() {
        traverseInOrder(root);
    }

    @Override
    public T getMax() {
        if(isEmpty()){
            return null;
        }
        return getMax(root);
    }

    public T getMax(Node<T> node){
        while(node.getRightChild() != null){
            node = node.getRightChild();
        }
        return node.getData();
    }

    @Override
    public T getMin() {
        if(isEmpty()){
            return null;
        }
        return getMin(root);
    }

    public T getMin(Node<T> node){
        while(node.getLeftChild() != null){
            node = node.getLeftChild();
        }
        return node.getData();
    }

    @Override
    public boolean isEmpty(){
        return root == null;
    }

    public void traverseInOrder(Node<T> node){
        if(node != null){
            traverseInOrder(node.getLeftChild());
            System.out.println(node.getData());
            traverseInOrder(node.getRightChild());
        }
    }
}
