package com.bookshop;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookAuthors {


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
        int index = 0;
        if (booksAndAuthors.containsKey(author)) {
            List<Book> booksByAuthor = booksAndAuthors.get(author);
            System.out.println("Author found. Books by them:");
            for (Book book : booksByAuthor) {
                booksByAuthor.get(index);
                System.out.println(index + ".\t" + "\"" + book.getTitle() + "\"");
                index++;

            }

        } else {
            System.out.println("Author  NOT found.");
            System.exit(0);
        }
    }

    public void searchTitle(int index, String author, Map<String, List<Book>> booksAndAuthors) {

        if (booksAndAuthors.containsKey(author)) {
            List<Book> booksByAuthor = booksAndAuthors.get(author);
            for (Book book : booksByAuthor) {

                if (booksByAuthor.indexOf(book) == index) {

                    System.out.println("Chosen book: " + "\"" + book.getTitle() + "\"" + "\nThere are " + book.getStock() + " books with this title in stock.");

                }
            }

        } else System.out.println("Not a valid option :(");
    }

    public void checkQuantity(int stock, int index, String author, Map<String, List<Book>> booksAndAuthors) {
        if (booksAndAuthors.containsKey(author)) {
            List<Book> booksByAuthor = booksAndAuthors.get(author);
            for (Book book : booksByAuthor) {

                if (booksByAuthor.indexOf(book) == index) {

                    if (book.getStock() >= stock) {
                        int stockLeft = book.getStock() - stock;
                        System.out.println("You purchased " + stock + " books.");
                        System.out.println("There are " + stockLeft + " books left.");
                        book.setStock(stockLeft);

                    } else {
                        System.out.println("Not enough stock!");
                    }
                }
            }
        }
    }

    public void createOutputFile(List<Book> bookData) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(Paths.get("D:\\Progrumming\\little-bookshop\\src\\main\\resources\\output.json").toFile(), bookData);

    }

    public List<Book> deserializeJsonData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File src = Paths.get("D:\\Progrumming\\little-bookshop\\src\\main\\resources\\output.json").toFile();
        CollectionType valueType = mapper.getTypeFactory().constructCollectionType(List.class, Book.class);
        List<Book> books = mapper.readValue(src, valueType);
        return books;
    }

}