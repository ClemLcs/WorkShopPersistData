package com.epsi.library.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Borrow {
    Date loan_date;

    Date expected_end_date;

    @Column(nullable = true)
    Date borrow_end;

    @ManyToOne
    @JoinColumn(name = "fk_member", nullable = true)
    private User member;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_borrow", nullable = false)
    private Long id;

    public Borrow(Date loanDate, Date expectedEndDate, Date borrowEnd) {
        this.loan_date = loanDate;
        this.expected_end_date = expectedEndDate;
        this.borrow_end = borrowEnd;
    }

    public Borrow() {

    }
}

