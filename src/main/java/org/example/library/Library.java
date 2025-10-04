package org.example.library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author amanjain
 **/
public class Library {
    private Map<Integer, Rack> racks;
    private Map<String, User> users;
    private Map<String, BookCopy> borrowedBookCopies;

    public Library(int rackCount) {
        this.racks = new HashMap<>(10);
        for(int i = 1; i <= 10; i++){
            racks.put(i, new Rack(i));
        }
        users = new HashMap<>();
        borrowedBookCopies = new HashMap<>();
    }

    public boolean validBookId(String bookId){
        for(Rack rack : racks.values()){
            if(rack.containsBook(bookId)){
                return true;
            }
        }
        return false;
    }

    public boolean validBookCopyId(String bookCopyId){
        for(Rack rack : racks.values()){
            if(rack.containsBookCopy(bookCopyId)){
                return true;
            }
        }
        return false;
    }

    public void addBook(Book book, List<String> bookCopyIds){
        if(bookCopyIds.size() > racks.size()){
            System.out.println("Rack not available.");
            return;
        }
        List<Integer> booksAddedToRack = new ArrayList<>();
        for(String bookCopyId : bookCopyIds){
            for(Rack rack : racks.values()){
                if(!rack.containsBook(book.getBookId())){
                    rack.addBook(book, bookCopyId);
                    booksAddedToRack.add(rack.getId());
                    break;
                }
            }
        }
        System.out.println("Added Book to racks: " + booksAddedToRack.toString());
    }

    public void removeBook(String bookCopyId, int rackId){
        Rack rack = racks.get(rackId);
        if(rack.containsBookCopy(bookCopyId)){
            rack.removeBookCopy(bookCopyId);
        } else {
            System.out.println("Invalid Book Copy ID");
        }
    }

    public void borrowBook(String bookId, String userId, LocalDate dueDate){
        User user = users.get(userId);
        if(user == null){
            user = new User(userId, userId);
            users.put(userId, user);
        }

        if(!validBookId(bookId)){
            System.out.println("Invalid Book Id.");
            return;
        }

        if(!user.allowedToBorrowMore()){
            System.out.println("Overlimit");
            return;
        }

        for(Rack rack : racks.values()){
            if(rack.containsBook(bookId)){
                BookCopy bookCopy = rack.removeByBookId(bookId);
                bookCopy.borrowedBy = user;
                bookCopy.dueDate = dueDate;
                borrowedBookCopies.put(bookCopy.getId(), bookCopy);
                return;
            }
        }

        System.out.println("Not Available");
    }

    public void borrowBookCopy(String bookCopyId, String userId, LocalDate dueDate){
        User user = users.get(userId);
        if(user == null){
            user = new User(userId, userId);
            users.put(userId, user);
        }

        if(!validBookCopyId(bookCopyId)){
            System.out.println("Invalid Book Copy Id");
            return;
        }

        if(!user.allowedToBorrowMore()){
            System.out.println("Overlimit");
            return;
        }

        for(Rack rack : racks.values()){
            if(rack.containsBookCopy(bookCopyId)){
                BookCopy bookCopy = rack.removeBookCopy(bookCopyId);
                bookCopy.borrowedBy = user;
                bookCopy.dueDate = dueDate;
                borrowedBookCopies.put(bookCopy.getId(), bookCopy);
                return;
            }
        }
    }

    public void returnBookCopy(String bookCopyId, int rackId){
        if(!borrowedBookCopies.containsKey(bookCopyId)){
            System.out.println("Invalid Book Copy Id");
            return;
        }

        BookCopy bookCopy = borrowedBookCopies.get(bookCopyId);
        borrowedBookCopies.remove(bookCopyId);
        Rack rack = racks.get(rackId);
        rack.addBook(bookCopy.getBook(), bookCopyId);
    }

    public void printBorrowed(String userId){
        User user = users.get(userId);
        for(BookCopy bookCopy : borrowedBookCopies.values()){
            if(bookCopy.getBorrowedBy().equals(user)){
                System.out.println("Book Copy: " + bookCopy.getId() + " " + bookCopy.getDueDate());
            }
        }
        System.out.println();
    }

    public static void main(String[] args){
        Library library = new Library(10);
        library.addBook(new Book("1", "book1", null, null), List.of("book_copy1", "book_copy2", "book_copy3"));
        library.addBook(new Book("2", "book2", null, null), List.of("book_copy4","book_copy5","book_copy6","book_copy7","book_copy8","book_copy9","book_copy10"));
        library.addBook(new Book("3", "book3", null, null), List.of("book_copy11","book_copy12","book_copy13"));
    }
}
