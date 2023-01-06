package com.bookshop;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class BookRepository {

    private static final String FILE_PATH = "D:\\Progrumming\\little-bookshop\\src\\main\\resources\\output.json";
    private final List<Book> books;

    public BookRepository() throws IOException {
        this.books = this.loadData();
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public List<Book> getBooksByAuthor(String author) {
        Map<String, List<Book>> booksAndAuthors = this.groupData();
        if (booksAndAuthors.containsKey(author)) {
            return booksAndAuthors.get(author);
        } else {
            return Collections.emptyList();
        }
    }

    public void saveData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(Paths.get(FILE_PATH).toFile(), books);
    }

    private Map<String, List<Book>> groupData() {
        Map<String, List<Book>> booksAndAuthors = new HashMap<>();
        for (Book book : this.books) {
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

    private List<Book> loadData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File src = Paths.get(FILE_PATH).toFile();
        CollectionType valueType = mapper.getTypeFactory().constructCollectionType(List.class, Book.class);
        return mapper.readValue(src, valueType);
    }


}
