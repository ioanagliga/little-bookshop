package com.bookshop;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/books")
public class BookMenuController {

    private final BookRepository bookRepository;

    private final Scanner userInput;
    private final BookService bookService;

    public BookMenuController(BookRepository bookRepository, Scanner userInput, BookService bookService) {
        this.bookRepository = bookRepository;
        this.userInput = userInput;
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks(@RequestParam(required = false) String author) {
        if (author!= null && !author.isEmpty()) {
            return bookRepository.searchBook(author);
        }else{
            return bookRepository.findAll();
        }
    }

   @GetMapping("/{id}")
    @ResponseBody
    public Book getBookById(@PathVariable Integer id) {
            return bookService.getBookById(id);

    }

    public void mainMenu() {
        System.out.println("Choose an option: \n 1.Search \n 2.Buy \n 3.Add book \n 4.Exit ");
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

    private void showAddBookMenu() {
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
        bookService.addNewBookToStore(author, title, stock);
        System.out.println("1.Return to main menu? \n \t or \n 2.exit?");
        showSecondaryMenu();
    }

    private void showPurchaseMenu() {
        Scanner buyInputOption = new Scanner(System.in);
        System.out.println("Enter the author's name:");
        String author = buyInputOption.nextLine();
        bookService.searchAndDisplay(author);
        System.out.println("Choose your book: ");
        String title = buyInputOption.nextLine();
        if (bookService.checkBookExist(author, title)) {
            System.out.println("How many books do you want to buy? Enter quantity:");
            int numberOfPurchasedBooks = buyInputOption.nextInt();
            bookService.purchaseBook(author, title, numberOfPurchasedBooks);

        } else {
            System.out.println("Book does not exist in db");
        }

        System.out.println("1.Buy some more? \n \t or \n 2.exit?");
        showSecondaryMenu();
    }

    private void showSearchMenu() {
        Scanner searchedAuthor = new Scanner(System.in);
        System.out.println("Enter author's name:");
        String authorSearch = searchedAuthor.nextLine();
        bookService.searchAndDisplay(authorSearch);
        System.out.println("1.Return to main menu? \n \t or \n 2.exit?");
        showSecondaryMenu();
    }

    public void showSecondaryMenu() {
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
