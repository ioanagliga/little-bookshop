package com.bookshop;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookService {
    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void searchAndDisplay(String author) {
        if (!bookRepository.findByAuthor(author).isEmpty()) {
            System.out.println("Author found. Books by them:");
            System.out.println(bookRepository.findByAuthor(author));
        } else {
            System.out.println("Author  NOT found.");
            System.exit(0);
        }
    }

    public void addNewBookToStore(String author, String title, int stock) {
        Book existingBook = bookRepository.findByAuthorAndTitle(author, title);
        if (existingBook != null) {
            System.out.println("book already in db.Updating stock.");
            existingBook.setStock(stock + existingBook.getStock());
            bookRepository.save(existingBook);
        } else {
            Book newBook=new Book();
            newBook.setAuthor(author);
            newBook.setTitle(title);
            newBook.setStock(stock);
            bookRepository.save(newBook);
            System.out.println("Added new book.");
        }
    }

    public boolean checkBookExist(String author, String title) {
        return bookRepository.findByAuthorAndTitle(author, title) != null;
    }

    public void purchaseBook(String author, String title, int numberOfPurchasedBooks) {
        Book book = bookRepository.findByAuthorAndTitle(author, title);
        int stock = book.getStock();
        if (stock >= numberOfPurchasedBooks) {
            book.setStock(stock - numberOfPurchasedBooks);
        } else {
            System.out.println("Not enough stock :(");
        }
    }

    public Book getById(Integer id) {
        return bookRepository.findById(id).get();
    }

    public List<Book> findByName(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
