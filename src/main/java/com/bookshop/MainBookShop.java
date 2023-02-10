package com.bookshop;

import java.io.IOException;

public class MainBookShop {

    public static void main(String[] args) throws IOException {
     //   BookMenuController controller = new BookMenuController();
      //  controller.mainMenu();
        DBAccess driverManager = new DBAccess();
        driverManager.getConnection();
    }

}