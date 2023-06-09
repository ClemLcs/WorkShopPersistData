package com.epsi.library.controller;

import com.epsi.library.entity.Book;
import com.epsi.library.entity.Borrow;
import com.epsi.library.entity.Category;
import com.epsi.library.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    private final WebClient.Builder webClientBuilder;

    @Value("${restServerUrl}")
    private String restServerUrl;

    @Autowired
    public BookController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @GetMapping("")
    public String listBooks(Model model) {
        List<Book> books = webClientBuilder.build()
                .get()
                .uri(restServerUrl + "/rest/book")
                .retrieve()
                .bodyToFlux(Book.class)
                .collectList()
                .block();

        model.addAttribute("books", books);

        return "book/list";
    }

    @GetMapping("/add")
    public String showAddForm(Book book, Model model) {
        List<Category> categories = webClientBuilder.build()
                .get()
                .uri(restServerUrl + "/rest/category")
                .retrieve()
                .bodyToFlux(Category.class)
                .collectList()
                .block();

        List<User> users = webClientBuilder.build()
                .get()
                .uri(restServerUrl + "/rest/auth")
                .retrieve()
                .bodyToFlux(User.class)
                .collectList()
                .block();

        model.addAttribute("categories", categories);

        return "book/add";
    }

    @PostMapping("/add")
    public String addBook(@Validated Book book) {
        webClientBuilder.build()
                .post()
                .uri(restServerUrl + "/rest/book")
                .bodyValue(book)
                .retrieve()
                .toBodilessEntity()
                .block();

        return "redirect:/book";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = webClientBuilder.build()
                .get()
                .uri(restServerUrl + "/rest/book/{id}", id)
                .retrieve()
                .bodyToMono(Book.class)
                .block();

        if (book != null) {
            model.addAttribute("book", book);
            return "book/edit";
        }

        return "redirect:/book";
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id, @Validated Book updatedBook) {
        webClientBuilder.build()
                .put()
                .uri(restServerUrl + "/rest/book/{id}", id)
                .bodyValue(updatedBook)
                .retrieve()
                .toBodilessEntity()
                .block();

        return "redirect:/book";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        webClientBuilder.build()
                .delete()
                .uri(restServerUrl + "/rest/book/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .block();

        return "redirect:/book";
    }

    @GetMapping("/borrow-count")
    public String getBorrowCountByDateRange(@RequestParam("startDate") String startDate,
                                            @RequestParam("endDate") String endDate,
                                            Model model) {
        int count = webClientBuilder.build()
                .get()
                .uri(restServerUrl + "/rest/book/borrow-count?startDate={startDate}&endDate={endDate}", startDate, endDate)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();

        model.addAttribute("count", count);

        return "borrow/count";
    }

    @GetMapping("/{id}/borrow-count")
    public String getBorrowCountByBook(@PathVariable Long id, Model model) {
        int count = webClientBuilder.build()
                .get()
                .uri(restServerUrl + "/rest/book/{id}/borrow-count", id)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();

        model.addAttribute("count", count);

        return "borrow/count";
    }

    @GetMapping("/active-borrows")
    public String getActiveBorrows(Model model) {
        List<Borrow> borrows = webClientBuilder.build()
                .get()
                .uri(restServerUrl + "/rest/book/active-borrows")
                .retrieve()
                .bodyToFlux(Borrow.class)
                .collectList()
                .block();

        model.addAttribute("borrows", borrows);

        return "borrow/active";
    }

    @GetMapping("/search")
    public String searchBooksByAuthor(@RequestParam("authorName") String authorName, Model model) {
        List<Book> books = webClientBuilder.build()
                .get()
                .uri(restServerUrl + "/rest/book/search?authorName={authorName}", authorName)
                .retrieve()
                .bodyToFlux(Book.class)
                .collectList()
                .block();

        model.addAttribute("books", books);

        return "book/list";
    }
}