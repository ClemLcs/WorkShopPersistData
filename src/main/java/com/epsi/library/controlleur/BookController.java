package com.epsi.library.controlleur;

import com.epsi.library.entity.Book;
import com.epsi.library.repository.BookRepository;
import com.epsi.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/book")
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    /**
     * Allows to get all books save in DB
     *
     * @return ResponseEntity 200 / 500
     */
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> readAll() {
        try {
            List<Book> books = new ArrayList<Book>();
            bookRepository.findAll().forEach(books::add);

            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Allows to get information about a book
     *
     * @param id Book identifier
     * @return ResponseEntity 200 / 400 / 500
     */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookPerId(@PathVariable("id") long id) {
        try {
            Optional<Book> bookData = bookRepository.findById(id);

            if (bookData.isPresent()) {
                return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}