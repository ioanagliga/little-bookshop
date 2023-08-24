package com.bookshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookMenuController {

    private final BookRepository bookRepository;
    @Autowired
    private final BookService bookService;

    public BookMenuController(BookRepository bookRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks(@RequestParam(required = false) String author) {
        if (author != null && !author.isEmpty()) {
            return bookRepository.searchBook(author);
        } else {
            return bookRepository.findAll();
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
