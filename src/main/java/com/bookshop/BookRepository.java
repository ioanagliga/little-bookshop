package com.bookshop;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

public class BookRepository {
    private static final String FILE_PATH = "D:\\Progrumming\\little-bookshop\\src\\main\\resources\\output.json";
    private List<Book> books;
    static String query = "SELECT * FROM books_schema.books";
    private static final String dbPath = "jdbc:mysql://localhost:3306/books_schema?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String user = "root";
    private static final String password = "admin";

    public BookRepository()  {
        this.books = this.loadData();

    }

    public List<Book> getAllBooks() {
        return books;
    }
    public void saveData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(Paths.get(FILE_PATH).toFile(), books);
    }
    public List<Book> getBooksByAuthor(String author) {
        Map<String, List<Book>> booksAndAuthors = this.groupData();
        if (booksAndAuthors.containsKey(author)) {
            return booksAndAuthors.get(author);
        } else {
            return Collections.emptyList();
        }
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
    private List<Book> loadData(){
        try (Connection connection = DriverManager.getConnection(dbPath, user, password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            this.books= new ArrayList<>();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId( resultSet.getInt("ID"));
                book.setAuthor(resultSet.getString("Author"));
                book.setTitle(resultSet.getString("Title"));
                book.setStock(resultSet.getInt("Stock"));
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }


}
