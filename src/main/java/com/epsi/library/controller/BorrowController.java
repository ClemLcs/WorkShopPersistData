package com.epsi.library.controller;

import com.epsi.library.entity.Borrow;
import com.epsi.library.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/borrow")
public class BorrowController {

    @Autowired
    BorrowRepository borrowRepository;

    /**
     * Allows to get all borrows
     *
     * @return ResponseEntity 204 / 200 / 500
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
     * @return ResponseEntity 201 / 500
     */
    @PostMapping("")
    public ResponseEntity<Borrow> create(@RequestBody Borrow borrowRequest) {
        try {
            Borrow _borrow = borrowRepository
                    .save(new Borrow(borrowRequest.getLoan_date(), borrowRequest.getExpected_end_date(), borrowRequest.getBorrow_end()));
            return new ResponseEntity<>(_borrow, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
