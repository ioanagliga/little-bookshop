package com.bookshop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class BookRepository  {


    static String query = "SELECT * FROM books_schema.books";
    @Value("${datasource.url}")
    private String dbPath;
    @Value("${datasource.username}")
    private String user;
    @Value("${datasource.password}")
    private String password;

protected List<Book> findAll(){
    List<Book> books = new ArrayList<>();
    String searchTable = "SELECT * FROM books_schema.books";
    try (Connection connection = DriverManager.getConnection(dbPath, user, password)) {
        PreparedStatement prepareStatement = connection.prepareStatement(searchTable);
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            Book book = new Book();
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
    protected List<Book> searchBook(String author) {
        List<Book> books = new ArrayList<>();
        String searchTable = "SELECT Author, Title, Stock FROM books WHERE author LIKE ?";
        try (Connection connection = DriverManager.getConnection(dbPath, user, password)) {
            PreparedStatement prepareStatement = connection.prepareStatement(searchTable);
            prepareStatement.setString(1, author);
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
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

    protected void addBook(String author, String title, int stock) {

        String addInTable = "INSERT INTO books (Author, Title, Stock) VALUES (?,?,?)";
        try (Connection connection = DriverManager.getConnection(dbPath, user, password)) {
            PreparedStatement prepareStatement = connection.prepareStatement(addInTable);
            prepareStatement.setString(1, author);
            prepareStatement.setString(2, title);
            prepareStatement.setInt(3, stock);
            prepareStatement.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void updateStockIfBookExist(String author, String title, int stock) {
        String updateStock = "UPDATE books SET Stock = Stock + ? WHERE Author = ? AND Title = ? ";
        try (Connection connection = DriverManager.getConnection(dbPath, user, password)) {
            PreparedStatement prepareStatement = connection.prepareStatement(updateStock);
            prepareStatement.setInt(1, stock);
            prepareStatement.setString(2, author);
            prepareStatement.setString(3, title);

            prepareStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    boolean findMatchingBook(String author, String title) {
        String findMatch = "SELECT Author, Title FROM books WHERE Author = ? AND Title = ? ";
        try (Connection connection = DriverManager.getConnection(dbPath, user, password)) {
            PreparedStatement prepareStatement = connection.prepareStatement(findMatch);
            prepareStatement.setString(1, author);
            prepareStatement.setString(2, title);
            return prepareStatement.executeQuery().next();


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public int getStock(String author, String title) {
        String checkStockQuery = "SELECT Stock FROM books WHERE Author = ? AND Title = ? ";
        try (Connection connection = DriverManager.getConnection(dbPath, user, password)) {
            PreparedStatement prepareStatement = connection.prepareStatement(checkStockQuery);
            prepareStatement.setString(1, author);
            prepareStatement.setString(2, title);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("Stock");
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return 0;
    }

    public void purchaseBook(String author, String title, int numberOfPurchasedBooks) {
        String updateStock = "UPDATE books SET Stock = Stock - ? WHERE Author = ? AND Title = ? ";
        try (Connection connection = DriverManager.getConnection(dbPath, user, password)) {
            PreparedStatement prepareStatement = connection.prepareStatement(updateStock);
            prepareStatement.setInt(1, numberOfPurchasedBooks);
            prepareStatement.setString(2, author);
            prepareStatement.setString(3, title);
            prepareStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
