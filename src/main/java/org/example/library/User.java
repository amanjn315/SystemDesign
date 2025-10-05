package org.example.library;

import java.util.ArrayList;
import java.util.List;

/**
 * @author amanjain
 **/
public class User {
    String userId;
    String name;
    int maxAllowedBooks = 5;
    List<BookCopy> borrowedBookCopies;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        borrowedBookCopies = new ArrayList<>();
    }

    public List<BookCopy> getBookCopiesBorrowed() {
        return borrowedBookCopies;
    }

    public boolean allowedToBorrowMore(){
        return borrowedBookCopies.size() < maxAllowedBooks;
    }

    public void addBorrowedBookCopy(BookCopy bookCopy){
        borrowedBookCopies.add(bookCopy);
    }

    public void removeBorrowedBookCopy(BookCopy bookCopy){
        borrowedBookCopies.remove(bookCopy);
    }
}
