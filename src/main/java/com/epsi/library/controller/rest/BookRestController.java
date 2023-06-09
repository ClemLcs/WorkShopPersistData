package com.epsi.library.controller.rest;

import com.epsi.library.entity.Book;
import com.epsi.library.entity.Borrow;
import com.epsi.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest/book")
public class BookRestController {

    @Autowired
    private BookService bookService;

    @GetMapping("")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Book book = bookService.updateBook(id, updatedBook);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        boolean deleted = bookService.deleteBook(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/borrow-count")
    public ResponseEntity<Integer> getBorrowCountByDateRange(@RequestParam Date startDate, @RequestParam Date endDate) {
        int count = bookService.getBorrowCountByDateRange(startDate, endDate);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/{id}/borrow-count")
    public ResponseEntity<Integer> getBorrowCountByBook(@PathVariable Long id) {
        int count = bookService.getBorrowCountByBook(id);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/active-borrows")
    public ResponseEntity<List<Borrow>> getActiveBorrows() {
        List<Borrow> borrows = bookService.getActiveBorrows();
        return new ResponseEntity<>(borrows, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooksByAuthor(@RequestParam String authorName) {
        List<Book> books = bookService.searchBooksByAuthor(authorName);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}