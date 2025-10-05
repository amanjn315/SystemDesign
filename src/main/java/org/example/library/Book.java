package org.example.library;

import java.util.List;

/**
 * @author amanjain
 **/
public class Book {
    String bookId;
    String title;
    List<Author> authors;
    List<Publisher> publishers;

    public Book(String bookId, String title, List<Author> authors, List<Publisher> publishers) {
        this.bookId = bookId;
        this.title = title;
        this.authors = authors;
        for(Author author : authors){
            author.addBook(this);
        }
        this.publishers = publishers;
        for(Publisher publisher : publishers){
            publisher.addBooks(this);
        }
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Publisher> getPublishers() {
        return publishers;
    }
}
