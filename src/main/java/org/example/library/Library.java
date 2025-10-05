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
    private Map<String, BookCopy> availableBookCopies;
    private Map<String, BookCopy> borrowedBookCopies;

    public Library(int rackCount) {
        this.racks = new HashMap<>(10);
        for(int i = 1; i <= 10; i++){
            racks.put(i, new Rack(i));
        }
        users = new HashMap<>();
        borrowedBookCopies = new HashMap<>();
        availableBookCopies = new HashMap<>();
    }

    public boolean validBookId(String bookId){
        for(BookCopy bookCopy : availableBookCopies.values()){
            if(bookCopy.getBook().getBookId().equals(bookId)){
                return true;
            }
        }
        return false;
    }

    public boolean validBookCopyId(String bookCopyId){
        for(BookCopy bookCopy : availableBookCopies.values()){
            if(bookCopy.getId().equals(bookCopyId)){
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
                    BookCopy bookCopy = rack.addBook(book, bookCopyId);
                    availableBookCopies.put(bookCopyId, bookCopy);
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

        for(BookCopy bookCopy : availableBookCopies.values()){
            if(bookCopy.getBook().getBookId().equals(bookId)){
                bookCopy.borrowedBy = user;
                bookCopy.dueDate = dueDate;
                borrowedBookCopies.put(bookCopy.getId(), bookCopy);
                user.addBorrowedBookCopy(bookCopy);
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

        BookCopy bookCopy = availableBookCopies.get(bookCopyId);

        if(bookCopy != null){
            Rack rack = racks.get(bookCopy.getRackId());
            rack.removeBookCopy(bookCopyId);

            availableBookCopies.remove(bookCopyId);
            borrowedBookCopies.put(bookCopyId, bookCopy);

            bookCopy.setBorrowedBy(user);
            bookCopy.setDueDate(dueDate);
            user.addBorrowedBookCopy(bookCopy);
        } else {
            System.out.println("Invalid Book Copy Id");
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
        User user = bookCopy.getBorrowedBy();
        user.removeBorrowedBookCopy(bookCopy);
    }

    public void printBorrowed(String userId){
        User user = users.get(userId);
        if (user != null) {
            // Just get the list directly from the user!
            List<BookCopy> userBooks = user.getBookCopiesBorrowed();
            if (userBooks.isEmpty()) {
                System.out.println("User has not borrowed any books.");
            } else {
                for(BookCopy bookCopy : userBooks){
                    System.out.println("Book Copy: " + bookCopy.getId() + ", Due: " + bookCopy.getDueDate());
                }
            }
        } else {
            System.out.println("User not found.");
        }
        System.out.println();
    }

    public void searchBookById(String bookId){
        for(BookCopy bookCopy : availableBookCopies.values().stream().filter(bookCopy -> bookCopy.getBook().getBookId().equals(bookId)).toList()){
            Book book = bookCopy.getBook();
            System.out.println(bookCopy.getId() + " " +
                    book.getBookId() + " " +
                    book.getTitle() + " " +
                    book.getAuthors().toString() + " " +
                    book.getPublishers().toString() + " " +
                    bookCopy.getRackId() + " "
            );
        }

        for(BookCopy bookCopy : borrowedBookCopies.values().stream().filter(bookCopy -> bookCopy.getBook().getBookId().equals(bookId)).toList()){
            Book book = bookCopy.getBook();
            System.out.println(bookCopy.getId() + " " +
                    book.getBookId() + " " +
                    book.getTitle() + " " +
                    book.getAuthors().toString() + " " +
                    book.getPublishers().toString() + " " +
                    "-1 " +
                    bookCopy.getBorrowedBy() + " " +
                    bookCopy.getDueDate()
            );
        }
    }

    public static void main(String[] args){
        Library library = new Library(10);
        List<Author> authors = List.of(
                new Author("author1", "author1"),
                new Author("author2", "author2"),
                new Author("author3", "author3")
        );
        List<Publisher> publishers = List.of(
                new Publisher("publisher1", "publisher1"),
                new Publisher("publisher2", "publisher2"),
                new Publisher("publisher3", "publisher3")
        );
        library.addBook(
                new Book("1", "book1", List.of(authors.get(0)), List.of(publishers.get(0))),
                List.of("book_copy1", "book_copy2", "book_copy3")
        );
        library.addBook(
                new Book("2", "book2", List.of(authors.get(1)), List.of(publishers.get(1))),
                List.of("book_copy4","book_copy5","book_copy6","book_copy7","book_copy8","book_copy9","book_copy10")
        );
        library.addBook(
                new Book("3", "book3", List.of(authors.get(2)), List.of(publishers.get(2))),
                List.of("book_copy11","book_copy12","book_copy13")
        );

        library.searchBookById("1");
    }
}
