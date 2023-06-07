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
    Date borrow_end;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_borrow", nullable = false)
    private Long id;

}

