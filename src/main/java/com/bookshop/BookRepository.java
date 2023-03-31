package com.bookshop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private List<Book> books;
    static String query = "SELECT * FROM books_schema.books";
    private static final String dbPath = "jdbc:mysql://localhost:3306/books_schema?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String user = "root";
    private static final String password = "admin";

    public BookRepository() {
        this.books = this.loadData();

    }

    protected List<Book> loadData() {
        try (Connection connection = DriverManager.getConnection(dbPath, user, password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            this.books = new ArrayList<>();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("ID"));
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
        String searchTable = "SELECT Author, Title, Stock FROM books WHERE author LIKE ?";
        try (Connection connection = DriverManager.getConnection(dbPath, user, password)) {
            PreparedStatement prepareStatement = connection.prepareStatement(searchTable);
            prepareStatement.setString(1, author);
            ResultSet resultSet = prepareStatement.executeQuery();
            this.books = new ArrayList<>();
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

    protected List<Book> addBook(String author, String title, int stock) {
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
        return books;
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
