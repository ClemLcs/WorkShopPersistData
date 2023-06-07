package com.epsi.library.controlleur;

import com.epsi.library.entity.Book;
import com.epsi.library.repository.BookRepository;
import com.epsi.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
     * @return ResponseEntity
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
}