package com.epsi.library.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Borrow {
    Date borrowDate;

    Date returnDate;

    @Column(nullable = true)
    Date borrow_end;

    @ManyToOne
    @JoinColumn(name = "fk_member", nullable = true)
    private User member;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_borrow", nullable = false)
    private Long bookId;

    public Borrow(Date borrowDate, Date returnDate, Date borrowEnd) {
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.borrow_end = borrowEnd;
    }

    public Borrow() {

    }
}

