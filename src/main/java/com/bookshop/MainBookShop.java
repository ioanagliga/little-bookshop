package com.bookshop;

import java.io.IOException;
import java.sql.SQLException;

public class MainBookShop {

    public static void main(String[] args) throws IOException, SQLException {
        BookMenuController controller = new BookMenuController();
        controller.mainMenu();


    }

}