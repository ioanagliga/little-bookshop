package com.bookshop;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    BookAuthors booksAndAuthors = new BookAuthors();
    List<Book> books = booksAndAuthors.deserializeJsonData();
    Map<String, List<Book>> bookAndAuthorsMapping = booksAndAuthors.groupData(books);


    Scanner userInput = new Scanner(System.in);

    public Menu() throws IOException {
    }

    public void mainMenu() throws IOException {
        System.out.println("Choose an option: \n 1.Search \n 2.Buy \n 3.Exit");
        int optionNumber = userInput.nextInt();
        switch (optionNumber) {
            case 1 : {
                Scanner searchedAuthor = new Scanner(System.in);
                System.out.println("Enter author's name:");
                String authorSearch = searchedAuthor.nextLine();
                booksAndAuthors.searchAndDisplay(authorSearch,  bookAndAuthorsMapping);
            }
            break;
            case 2 : {
                Scanner buyInputOption = new Scanner(System.in);
                System.out.println("Enter the author's name:");
                String author = buyInputOption.nextLine();
                booksAndAuthors.searchAndDisplay(author,  bookAndAuthorsMapping);
                System.out.println("Choose your book: ");
                int index = buyInputOption.nextInt();
                booksAndAuthors.searchTitle(index, author,  bookAndAuthorsMapping);
                System.out.println("How many books do you want to buy? Enter quantity:");
                int stock = buyInputOption.nextInt();
                booksAndAuthors.checkQuantity(stock, index, author,  bookAndAuthorsMapping);
                System.out.println("1.Buy some more? \n \t or \n 2.exit?");
                secondaryMenu();
            }
            break;
            case 3 :

             booksAndAuthors.createOutputFile(books);
                System.exit(0);

                break;
            default:
                System.out.println("Invalid selection.");
                break;
        }
    }

    public void secondaryMenu() throws IOException {
        int nextOption = userInput.nextInt();
        switch (nextOption) {
            case 1:
                mainMenu();
                break;
            case 2:
                booksAndAuthors.createOutputFile(books);
                System.exit(0);
                break;
        }
    }
}
