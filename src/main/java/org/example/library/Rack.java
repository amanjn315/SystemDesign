package org.example.library;

import java.util.HashMap;
import java.util.Map;

/**
 * @author amanjain
 **/
public class Rack {
    int id;
    Map<String, BookCopy> bookCopies;

    public int getId() {
        return id;
    }

    public BookCopy getBookCopy(String bookCopyId) {
        return bookCopies.getOrDefault(bookCopyId, null);
    }

    public Rack(int id) {
        this.id = id;
        this.bookCopies = new HashMap<>();
    }

    public boolean containsBook(String bookId){
        for(BookCopy bookCopy : bookCopies.values()){
            if(bookCopy.getBook().getBookId().equals(bookId)){
                return true;
            }
        }
        return false;
    }

    public boolean containsBookCopy(String bookCopyId){
        return bookCopies.containsKey(bookCopyId);
    }

    public void addBook(Book book, String bookCopyId){
        BookCopy bookCopy = new BookCopy(book, bookCopyId, this.id, null, null);
        bookCopies.put(bookCopyId, bookCopy);
    }

    public BookCopy removeBookCopy(String bookCopyId){
        BookCopy bookCopy = bookCopies.get(bookCopyId);
        bookCopies.remove(bookCopyId);
        return bookCopy;
    }

    public BookCopy removeByBookId(String bookId){
        for(BookCopy bookCopy : bookCopies.values()){
            if(bookCopy.getBook().getBookId().equals(bookId)){
                removeBookCopy(bookCopy.getId());
                return bookCopy;
            }
        }
        System.out.println("Invalid Book Id.");
        return null;
    }

}
