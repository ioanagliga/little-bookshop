package com.bookrenting;

public class Book {

    private final int stock;
    private final String title;

    public Book(String title, int stock) {

        this.title = title.trim();

        this.stock = stock;
    }

    public String toString() {
        return "\nTitle: " + title + "\t Stock: " + stock;
    }
}