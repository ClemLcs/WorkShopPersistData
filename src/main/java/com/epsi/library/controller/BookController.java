package com.epsi.library.controller;

import com.epsi.library.entity.Book;
import com.epsi.library.repository.BookRepository;
import com.epsi.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    CategoryRepository categoryRepository;
    @Value("${serverPort}")
    private String serverPort;

    @GetMapping("")
    public String listBooks(Model model) {
        List<Book> books = webClientBuilder.build()
                .get()
                .uri("http://localhost:" + serverPort + "/rest/book")
                .retrieve()
                .bodyToFlux(Book.class)
                .collectList()
                .block();

        model.addAttribute("books", books);

        return "book/list";
    }
}
