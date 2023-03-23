package com.bookshop;

public class DBTestService {
    private final DBTestRepository dbTestRepository;


    public DBTestService() {
        this.dbTestRepository = new DBTestRepository();
    }

    public void searchAndDisplay(String author) {
        if (!dbTestRepository.searchBook(author).isEmpty()) {
            System.out.println("Author found. Books by them:");
            System.out.println(dbTestRepository.searchBook(author));

        } else {
            System.out.println("Author  NOT found.");
            System.exit(0);
        }
    }

    public void addNewBookToStore(String author, String title, int stock) {

        if (dbTestRepository.findMatchingBook(author, title)) {
            System.out.println("book already in db.Updating stock.");
            dbTestRepository.updateStockIfBookExist(author, title, stock);
        } else {
            dbTestRepository.addBook(author, title, stock);
            System.out.println("Added new book.");
        }
    }

    public boolean checkBookExist(String author, String title) {
        return dbTestRepository.findMatchingBook(author, title);
    }

    public void purchaseBook(String author, String title, int numberOfPurchasedBooks) {
        if (dbTestRepository.getStock(author, title) >= numberOfPurchasedBooks) {
            dbTestRepository.purchaseBook(author, title, numberOfPurchasedBooks);
        } else {
            System.out.println("Not enough stock :(");
        }
    }

}
