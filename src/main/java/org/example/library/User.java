package org.example.library;

/**
 * @author amanjain
 **/
public class User {
    String userId;
    String name;
    int maxAllowedBooks = 5;
    int booksBorrowed = 0;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public void setBooksBorrowed(int booksBorrowed) {
        this.booksBorrowed = booksBorrowed;
    }

    public boolean allowedToBorrowMore(){
        return booksBorrowed < maxAllowedBooks;
    }
}
