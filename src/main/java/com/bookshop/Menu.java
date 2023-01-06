package com.bookshop;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    BookAuthors booksAndAuthors;
    List<Book> books;
    Map<String, List<Book>> bookAndAuthorsMapping;
    Scanner userInput;

    public Menu() throws IOException {
        this.userInput = new Scanner(System.in);
        this.booksAndAuthors = new BookAuthors();
        this.books = this.booksAndAuthors.deserializeJsonData();
        this.bookAndAuthorsMapping = this.booksAndAuthors.groupData(books);
    }

    public void mainMenu() throws IOException {
        System.out.println("Choose an option: \n 1.Search \n 2.Buy \n 3.Add book \n 4.Exit");
        int optionNumber = userInput.nextInt();
        switch (optionNumber) {
            case 1: {
                Scanner searchedAuthor = new Scanner(System.in);
                System.out.println("Enter author's name:");
                String authorSearch = searchedAuthor.nextLine();
                booksAndAuthors.searchAndDisplay(authorSearch, bookAndAuthorsMapping);
            }
            break;
            case 2: {
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
            break;
            case 3: {
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
                Book existingBook = booksAndAuthors.checkDuplicateBook(author, title, books);
                if (existingBook == null) {
                    books.add(book);
                } else {
                    existingBook.setStock(existingBook.getStock() + stock);
                }
                this.bookAndAuthorsMapping = this.booksAndAuthors.groupData(books);
                System.out.println("1.Return to main menu? \n \t or \n 2.exit?");
                secondaryMenu();
            }

            break;
            case 4: {

                booksAndAuthors.createOutputFile(books);
                System.exit(0);
            }
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
