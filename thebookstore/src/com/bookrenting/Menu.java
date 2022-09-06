package com.bookrenting;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    BookAuthors booksAndAuthors = new BookAuthors();
    List<Book> books = booksAndAuthors.initialiseData("D:\\Progrumming\\little-bookshop\\thebookstore\\database.txt");
    Map<String, List<Book>> bookAndAuthorsMapping = booksAndAuthors.groupData(books);
    Scanner userInput = new Scanner(System.in);

    public Menu() throws IOException {
    }

    public void mainMenu() {
        System.out.println("Choose an option: \n 1.Search \n 2.Buy \n 3.Exit");
        int optionNumber = userInput.nextInt();
        switch (optionNumber) {
            case 1 -> {
                Scanner searchedAuthor = new Scanner(System.in);
                System.out.println("Enter author's name:");
                String authorSearch = searchedAuthor.nextLine();
                booksAndAuthors.searchAndDisplay(authorSearch, bookAndAuthorsMapping);
            }
            case 2 -> {
                Scanner buyInputOption = new Scanner(System.in);
                System.out.println("Enter the author's name:");
                String author = buyInputOption.nextLine();
                booksAndAuthors.searchAndDisplay(author, bookAndAuthorsMapping);
                System.out.println("Choose your book: ");
                int index = buyInputOption.nextInt();
                booksAndAuthors.searchTitle(index, author, bookAndAuthorsMapping);
                System.out.println("How many books do you want to buy? Enter quantity:");
                int stock = buyInputOption.nextInt();
                booksAndAuthors.checkQuantity(stock, index, author, bookAndAuthorsMapping);
                System.out.println("1.Buy some more? \n \t or \n 2.exit?");
                secondaryMenu();
            }
            case 3 -> System.exit(0);
            default -> System.out.println("Invalid selection.");
        }
    }

    public void secondaryMenu() {
        int nextOption = userInput.nextInt();
        switch (nextOption) {
            case 1 -> mainMenu();
            case 2 -> System.exit(0);
        }
    }
}
