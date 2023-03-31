package com.bookshop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class BookShopConfig {
    @Bean
    public BookMenuController controller() {
        return new BookMenuController(userInput(), bookService());
    }

    @Bean
    public Scanner userInput() {
        return new Scanner(System.in);
    }

    @Bean
    public BookService bookService() {
        return new BookService(bookRepository());
    }

    @Bean
    public BookRepository bookRepository() {
        return new BookRepository();
    }

}
