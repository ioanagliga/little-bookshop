package com.bookrenting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookAuthors {

    public List<Book> initialiseData(String filePath) throws IOException {


        List<String> database = Files.readAllLines(Paths.get(filePath));
        List<Book> bookData = new ArrayList<>();
        for (String line : database) {
            String[] lineElement = line.split(";");
            String author = lineElement[0];
            String title = lineElement[1];
            int stock = Integer.parseInt(lineElement[2]);

            Book book = generateBook(author, title, stock);
            bookData.add(book);
        }
        return bookData;
    }

    public Map<String, List<Book>> groupData(List<Book> bookData) {

        Map<String, List<Book>> booksAndAuthors = new HashMap<>();
        for (Book book : bookData) {
            String author = book.getAuthor();

            if (booksAndAuthors.containsKey(author)) {
                List<Book> booksByAuthor = booksAndAuthors.get(author);
                booksByAuthor.add(book);

            } else {
                List<Book> booksByAuthor = new ArrayList<>();
                booksByAuthor.add(book);
                booksAndAuthors.put(author, booksByAuthor);
            }
        }

        return booksAndAuthors;
    }

    public void searchAndDisplay(String author, Map<String, List<Book>> booksAndAuthors) {
        if (booksAndAuthors.containsKey(author)) {
            List<Book> booksByAuthor = booksAndAuthors.get(author);
            System.out.println("Author found. Books by them:");
            for (Book book : booksByAuthor) {
                System.out.println("Title:" + book.getTitle() + "\nStock:" + book.getStock());
            }

        } else {
            System.out.println("Author  NOT found.");
        }

    }

    private Book generateBook(String author, String title, int stock) {
        Book myBook = new Book();
        myBook.setAuthor(author);
        myBook.setTitle(title);
        myBook.setStock(stock);
        return myBook;
    }


}