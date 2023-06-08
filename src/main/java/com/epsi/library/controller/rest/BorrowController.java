package com.epsi.library.controller.rest;

import com.epsi.library.entity.Borrow;
import com.epsi.library.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/borrow")
public class BorrowController {

    @Autowired
    BorrowRepository borrowRepository;

    /**
     * Allows to get all borrows
     *
     * @return 204 / 200 / 500 HTTP code
     */
    @GetMapping("")
    public ResponseEntity<List<Borrow>> getAll() {
        try {
            List<Borrow> borrows = new ArrayList<Borrow>();
            borrowRepository.findAll().forEach(borrows::add);

            if (borrows.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(borrows, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Allows to create a Borrow.
     *
     * @param borrowRequest
     * @return 201 / 500 HTTP code
     */
    @PostMapping("")
    public ResponseEntity<Borrow> create(@RequestBody Borrow borrowRequest) {
        try {
            Borrow _borrow = borrowRepository
                    .save(new Borrow(borrowRequest.getBorrowDate(), borrowRequest.getReturnDate(), borrowRequest.getBorrow_end()));
            return new ResponseEntity<>(_borrow, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Allows to update a borrow
     *
     * @param borrowRequest
     * @return 200 / 404 / 500 HTTP code
     */
    @PutMapping("")
    public ResponseEntity<Borrow> update(@RequestBody Borrow borrowRequest) {
        try {
            if (borrowRequest.getBookId() != null) {
                Optional<Borrow> _borrow = borrowRepository.findById(borrowRequest.getBookId());
                if (_borrow.isPresent()) {
                    Borrow _borrowData = _borrow.get();
                    _borrowData.setBorrowDate(borrowRequest.getBorrowDate());
                    _borrowData.setReturnDate(borrowRequest.getReturnDate());
                    _borrowData.setBorrow_end(borrowRequest.getBorrow_end());

                    return new ResponseEntity<>(borrowRepository.save(_borrowData), HttpStatus.OK);

                }
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Allows to delete a borrow
     *
     * @param id Borrow Id
     * @return 201 / 404 / 500 HTTP Code
     */
    @DeleteMapping("")
    public ResponseEntity<Borrow> delete(@RequestParam Long id) {
        try {
            Optional<Borrow> _borrow = borrowRepository.findById(id);
            if (_borrow.isPresent()) {
                borrowRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
