package com.epsi.library.repository;

import com.epsi.library.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    int countByBorrowDateBetween(Date startDate, Date endDate);

    int countByBookId(Long bookId);

    List<Borrow> findByReturnDateIsNull();
}
