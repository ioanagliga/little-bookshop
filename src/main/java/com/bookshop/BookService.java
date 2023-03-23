package com.bookshop;
import java.io.IOException;
import java.util.List;

public class BookService {
    private final BookRepository bookRepository;

    public BookService() {
        this.bookRepository = new BookRepository();
    }

    public void searchAndDisplay(String author) {
        int index = 0;
        List<Book> booksByAuthor = bookRepository.getBooksByAuthor(author);
        if (!booksByAuthor.isEmpty()) {
            System.out.println("Author found. Books by them:");
            for (Book book : booksByAuthor) {
                System.out.println(index + ".\t" + "\"" + book.getTitle() + "\"");
                index++;
            }
        } else {
            System.out.println("Author  NOT found.");
            System.exit(0);
        }
    }

    public void searchTitle(int index, String author) {
        List<Book> booksByAuthor = bookRepository.getBooksByAuthor(author);
        if (!booksByAuthor.isEmpty()) {
            for (Book book : booksByAuthor) {
                if (booksByAuthor.indexOf(book) == index) {
                    System.out.println("Chosen book: " + "\"" + book.getTitle() + "\"" + "\nThere are " + book.getStock() + " books with this title in stock.");
                }
            }
        } else {
            System.out.println("Not a valid option :(");
        }
    }

    public void purchaseBook(int stock, int index, String author)  {
        List<Book> booksByAuthor = bookRepository.getBooksByAuthor(author);
        if (booksByAuthor.isEmpty()) {
            return;
        }

        for (Book book : booksByAuthor) {
            if (booksByAuthor.indexOf(book) == index) {
                if (book.getStock() >= stock) {
                    int stockLeft = book.getStock() - stock;
                    System.out.println("You purchased " + stock + " books.");
                    System.out.println("There are " + stockLeft + " books left.");
                    book.setStock(stockLeft);
             //       bookRepository.saveData();
                } else {
                    System.out.println("Not enough stock!");
                }
            }
        }
    }

    public void addNewBookToStore(Book newBook)  {
        List<Book> books = bookRepository.getAllBooks();
        boolean bookFound = false;
        for (Book book : books) {
            if (book.getAuthor().equals(newBook.getAuthor()) && book.getTitle().equals(newBook.getTitle())) {
                bookFound = true;
                book.setStock(book.getStock() + newBook.getStock());
            }
        }
        if (!bookFound) {
            books.add(newBook);
        }
     //  bookRepository.saveData();
    }

}
