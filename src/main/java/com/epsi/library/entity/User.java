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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_member", nullable = false)
    private Long id;

    String firstname;

    String lastname;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    String password;

    Date registration_date;

    @ManyToOne
    @JoinColumn(name = "fk_role", nullable = false)
    private Role role;


    public User(String email, String firstname, String lastname, String password, Role role) {
        BCryptPasswordEncoder bCryptPasswordEncoder =
                new BCryptPasswordEncoder(10, new SecureRandom());

        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = bCryptPasswordEncoder.encode(password);
        this.registration_date = new Date();
        this.role = role;
    }

    public User() {

    }
}
