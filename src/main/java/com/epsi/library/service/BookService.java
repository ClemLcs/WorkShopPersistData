package com.epsi.library.service;

import com.epsi.library.entity.Book;
import com.epsi.library.entity.Borrow;
import com.epsi.library.repository.BookRepository;
import com.epsi.library.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowRepository borrowRepository;

    /**
     * Allows to gets all the books.
     */
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    /**
     * Allows to gets all the books per id.
     *
     * @param id Book id
     * @return
     */
    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    /**
     * Allows to save a book.
     *
     * @param book
     * @return
     */
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    /**
     * Allows to update a book
     *
     * @param id          Book id
     * @param updatedBook Book Collection
     * @return
     */
    public Book updateBook(Long id, Book updatedBook) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            updatedBook.setId(id);
            return bookRepository.save(updatedBook);
        }
        return null;
    }

    /**
     * Allows to delete a book while preserving the borrowing history.
     *
     * @param id Book id.
     * @return
     */
    public boolean deleteBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Allows to recover the number of loans in a given date range.
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public int getBorrowCountByDateRange(Date startDate, Date endDate) {
        return borrowRepository.countByBorrowDateBetween(startDate, endDate);
    }

    /**
     * Allows to retrieve the number of loans for a given book.
     *
     * @param bookId Book Id     * @return
     */
    public int getBorrowCountByBook(Long bookId) {
        return borrowRepository.countByBookId(bookId);
    }

    /**
     * Allows to recovers all outstanding loans.
     *
     * @return
     */
    public List<Borrow> getActiveBorrows() {
        return borrowRepository.findByReturnDateIsNull();
    }

    /**
     * Allows to search for books by author name.
     *
     * @param authorName
     * @return
     */
    public List<Book> searchBooksByAuthor(String authorName) {
        return bookRepository.findByAuthorsNameContainingIgnoreCase(authorName);
    }
}