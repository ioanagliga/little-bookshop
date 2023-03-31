package com.bookshop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MainBookShop {

    public static void main(String[] args) {

        ApplicationContext context =new AnnotationConfigApplicationContext(BookShopConfig.class);
        BookMenuController controller = context.getBean(BookMenuController.class);
        controller.mainMenu();


    }

}