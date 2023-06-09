package com.epsi.library.service;

import com.epsi.library.entity.Borrow;
import com.epsi.library.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    public Borrow getBorrowById(Long id) {
        Optional<Borrow> borrow = borrowRepository.findById(id);
        return borrow.orElse(null);
    }

    public Borrow createBorrow(Borrow borrow) {
        return borrowRepository.save(borrow);
    }

    public Borrow updateBorrow(Long id, Borrow updatedBorrow) {
        Optional<Borrow> borrow = borrowRepository.findById(id);
        if (borrow.isPresent()) {
            updatedBorrow.setBookId(id);
            return borrowRepository.save(updatedBorrow);
        }
        return null;
    }

    public boolean deleteBorrow(Long id) {
        Optional<Borrow> borrow = borrowRepository.findById(id);
        if (borrow.isPresent()) {
            borrowRepository.deleteById(id);
            return true;
        }
        return false;
    }
}