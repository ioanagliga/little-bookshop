package com.bookshop;

import java.sql.*;

public class DBAccess {
    static String query = "SELECT * FROM books_schema.books";
    private static final String dbPath = "jdbc:mysql://localhost:3306/books_schema?useSSL=false";
    private static final String user = "root";
    private static final String password = "admin";

    public static void getConnection() {
        try (Connection connection = DriverManager.getConnection(dbPath, user, password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("ID"));
                System.out.println("Author: " + resultSet.getString("Author"));
                System.out.println("Title: " + resultSet.getString("Title"));
                System.out.println("Stock: " + resultSet.getInt("Stock"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
