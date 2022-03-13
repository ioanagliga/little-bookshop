package com.bookrenting;


import java.util.List;
import java.util.Map;

public class MainBookrental {


    public static void main(String[] args) {
        BookAuthors writers = new BookAuthors();
        Map<String,List<Book>> books = writers.getAuthorsAndBooks();
        writers.findAndDisplayBooks(books);


    }
}