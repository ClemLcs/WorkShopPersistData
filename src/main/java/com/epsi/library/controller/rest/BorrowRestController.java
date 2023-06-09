package com.epsi.library.controller.rest;

import com.epsi.library.entity.Borrow;
import com.epsi.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/borrow")
public class BorrowRestController {

    @Autowired
    private BorrowService borrowService;

    @GetMapping("")
    public ResponseEntity<List<Borrow>> getAllBorrows() {
        List<Borrow> borrows = borrowService.getAllBorrows();
        return new ResponseEntity<>(borrows, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Borrow> getBorrowById(@PathVariable Long id) {
        Borrow borrow = borrowService.getBorrowById(id);
        if (borrow != null) {
            return new ResponseEntity<>(borrow, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<Borrow> createBorrow(@RequestBody Borrow borrow) {
        Borrow createdBorrow = borrowService.createBorrow(borrow);
        return new ResponseEntity<>(createdBorrow, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Borrow> updateBorrow(@PathVariable Long id, @RequestBody Borrow updatedBorrow) {
        Borrow borrow = borrowService.updateBorrow(id, updatedBorrow);
        if (borrow != null) {
            return new ResponseEntity<>(borrow, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrow(@PathVariable Long id) {
        boolean deleted = borrowService.deleteBorrow(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}