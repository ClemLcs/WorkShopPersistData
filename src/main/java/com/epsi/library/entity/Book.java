package com.epsi.library.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity @Getter @Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_book", nullable = false)
    private Long id;

    String title;

    Date publication_date;

    Integer page_number;

    @ManyToMany
    @JoinTable(name = "book_authors",
            joinColumns = @JoinColumn(name = "fk_book"),
            inverseJoinColumns = @JoinColumn(name = "fk_author"))
    private Collection<Author> authors = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "fk_category")
    private Category category;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "fk_borrow")
    private Borrow borrow;

}
