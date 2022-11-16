package com.bookshop;

import java.io.IOException;

public class MainBookShop {

    public static void main(String[] args) throws IOException {
        BookAuthors bookAuthors = new BookAuthors();
        bookAuthors.deserializeJsonData();
        Menu displayOptions= new Menu();
        displayOptions.mainMenu();


    }

}