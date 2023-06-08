package com.epsi.library.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Member {
    @Column(nullable = false)
    String firstname;
    @Column(nullable = false)
    String lastname;
    @Column(nullable = false)
    String email;
    Date registration_date;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_member", nullable = false)
    private Long id;

    public Member(String email, String firstname, String lastname) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.registration_date = new Date();
    }

    public Member() {

    }
}
