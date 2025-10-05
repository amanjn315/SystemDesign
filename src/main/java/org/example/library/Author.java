package org.example.library;

import java.util.ArrayList;
import java.util.List;

/**
 * @author amanjain
 **/
public class Author {
    String authorId;
    String name;
    List<Book> books;

    public Author(String authorId, String name) {
        this.authorId = authorId;
        this.name = name;
        this.books = new ArrayList<>();
    }

    public void addBook(Book book){
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
