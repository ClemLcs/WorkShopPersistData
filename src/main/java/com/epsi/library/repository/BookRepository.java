package com.epsi.library.repository;

import com.epsi.library.entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByAuthorsNameContainingIgnoreCase(String authorName);
}
