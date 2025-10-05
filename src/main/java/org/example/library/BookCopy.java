package org.example.library;

import java.time.LocalDate;

/**
 * @author amanjain
 **/
public class BookCopy {
    Book book;
    String id;
    int rackId;
    User borrowedBy;
    LocalDate dueDate;

    public BookCopy(Book book, String id, int rackId, User borrowedBy, LocalDate dueDate) {
        this.book = book;
        this.id = id;
        this.rackId = rackId;
        this.borrowedBy = borrowedBy;
        this.dueDate = dueDate;
    }

    public Book getBook() {
        return book;
    }

    public String getId() {
        return id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public User getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(User borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public int getRackId() {
        return rackId;
    }
}
