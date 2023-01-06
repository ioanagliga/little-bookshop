package com.bookshop;
import java.io.IOException;
import java.util.Scanner;

public class BookMenuController {

    private final Scanner userInput;
    private final BookService bookService;

    public BookMenuController() throws IOException {
        this.userInput = new Scanner(System.in);
        this.bookService = new BookService();
    }

    public void mainMenu() throws IOException {
        System.out.println("Choose an option: \n 1.Search \n 2.Buy \n 3.Add book \n 4.Exit");
        int optionNumber = userInput.nextInt();
        switch (optionNumber) {
            case 1: {
                showSearchMenu();
            }
            break;
            case 2: {
                showPurchaseMenu();
            }
            break;
            case 3: {
                showAddBookMenu();
            }
            break;
            case 4: {

                System.exit(0);
            }
            break;
            default:
                System.out.println("Invalid selection.");
                break;
        }
    }

    private void showAddBookMenu() throws IOException {
        Scanner addNewBookOption = new Scanner(System.in);
        System.out.println("Author's name:");
        String author = addNewBookOption.nextLine();
        System.out.println("Name of the book:");
        String title = addNewBookOption.nextLine();
        System.out.println("Quantity of books:");
        int stock = addNewBookOption.nextInt();
        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);
        book.setStock(stock);
        bookService.addNewBookToStore(book);
        System.out.println("1.Return to main menu? \n \t or \n 2.exit?");
        showSecondaryMenu();
    }

    private void showPurchaseMenu() throws IOException {
        Scanner buyInputOption = new Scanner(System.in);
        System.out.println("Enter the author's name:");
        String author = buyInputOption.nextLine();
        bookService.searchAndDisplay(author);
        System.out.println("Choose your book: ");
        int index = buyInputOption.nextInt();
        bookService.searchTitle(index, author);
        System.out.println("How many books do you want to buy? Enter quantity:");
        int stock = buyInputOption.nextInt();
        bookService.purchaseBook(stock, index, author);
        System.out.println("1.Buy some more? \n \t or \n 2.exit?");
        showSecondaryMenu();
    }

    private void showSearchMenu() throws IOException {
        Scanner searchedAuthor = new Scanner(System.in);
        System.out.println("Enter author's name:");
        String authorSearch = searchedAuthor.nextLine();
        bookService.searchAndDisplay(authorSearch);
        System.out.println("1.Return to main menu? \n \t or \n 2.exit?");
        showSecondaryMenu();
    }

    public void showSecondaryMenu() throws IOException {
        int nextOption = userInput.nextInt();
        switch (nextOption) {
            case 1:
                mainMenu();
                break;
            case 2:
                System.exit(0);
                break;
        }
    }
}
