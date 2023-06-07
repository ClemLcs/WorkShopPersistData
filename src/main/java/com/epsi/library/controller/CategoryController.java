package com.epsi.library.controller;

import com.epsi.library.entity.Category;
import com.epsi.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    /**
     * Allows to get all categories
     *
     * @return 204 / 200 / 500 HTTP code
     */
    @GetMapping("")
    public ResponseEntity<List<Category>> getAll() {
        try {
            List<Category> categories = new ArrayList<Category>();
            categoryRepository.findAll().forEach(categories::add);

            if (categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
