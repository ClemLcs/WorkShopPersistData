package com.epsi.library.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.Date;

@Entity
@Getter
@Setter
public class Member {

    String firstname;

    String lastname;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    String password;

    Date registration_date;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_member", nullable = false)
    private Long id;

    public Member(String email, String firstname, String lastname, String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder =
                new BCryptPasswordEncoder(10, new SecureRandom());

        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = bCryptPasswordEncoder.encode(password);
        this.registration_date = new Date();
    }

    public Member() {

    }
}
