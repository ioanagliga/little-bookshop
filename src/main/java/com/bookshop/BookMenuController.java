package com.bookshop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookMenuController {


    private final BookService bookService;

    public BookMenuController(BookRepository bookRepository, BookService bookService) {

        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks(@RequestParam(required = false) String author) {
        if (author != null && !author.isEmpty()) {
            return bookService.searchBook(author);
        } else {
            return bookService.findAll();
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Book getBookById(@PathVariable Integer id) {
        return bookService.getBookById(id);

    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<HttpStatus> postController(@RequestBody Book book) {

        bookService.addNewBookToStore(book.getAuthor(), book.getTitle(), book.getStock());
        return ResponseEntity.ok(HttpStatus.CREATED);
    }




}
