package org.example.library;

import java.util.ArrayList;
import java.util.List;

/**
 * @author amanjain
 **/
public class Publisher {
    String publisherId;
    String name;
    List<Book> books;

    public Publisher(String publisherId, String name) {
        this.publisherId = publisherId;
        this.name = name;
        this.books = new ArrayList<>();
    }

    public void addBooks(Book book){
        if(!books.contains(book)){
            books.add(book);
        }
    }

    public void removeBook(Book book){
        books.remove(book);
    }

    @Override
    public String toString(){
        return this.name;
    }
}
