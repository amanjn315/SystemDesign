package org.example.library;

import java.util.List;

/**
 * @author amanjain
 **/
public class Author extends User {
    List<Book> books;

    public Author(String userId, String name, List<Book> books) {
        super(userId, name);
        this.books = books;
    }
}
