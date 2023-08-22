package com.bookshop;

public class BookService {
    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void searchAndDisplay(String author) {
        if (!bookRepository.searchBook(author).isEmpty()) {
            System.out.println("Author found. Books by them:");
            System.out.println(bookRepository.searchBook(author));

        } else {
            System.out.println("Author  NOT found.");
            System.exit(0);
        }
    }

    public void addNewBookToStore(String author, String title, int stock) {

        if (bookRepository.findMatchingBook(author, title)) {
            System.out.println("book already in db.Updating stock.");
            bookRepository.updateStockIfBookExist(author, title, stock);
        } else {
            bookRepository.addBook(author, title, stock);
            System.out.println("Added new book.");
        }
    }

    public boolean checkBookExist(String author, String title) {
        return bookRepository.findMatchingBook(author, title);
    }

    public void purchaseBook(String author, String title, int numberOfPurchasedBooks) {
        if (bookRepository.getStock(author, title) >= numberOfPurchasedBooks) {
            bookRepository.purchaseBook(author, title, numberOfPurchasedBooks);
        } else {
            System.out.println("Not enough stock :(");
        }
    }

    public Book getBookById(Integer id) {
        return bookRepository.getBookByID(id);
    }
}
