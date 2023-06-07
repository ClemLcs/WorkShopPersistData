package com.epsi.library.controller;

import com.epsi.library.entity.Borrow;
import com.epsi.library.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/borrow")
public class BorrowController {

    @Autowired
    BorrowRepository borrowRepository;

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
}
