package com.epsi.library.controller;

import com.epsi.library.entity.Category;
import com.epsi.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Allows to create a category.
     *
     * @param categoryRequest
     * @return 201 / 500 HTTP code
     */
    @PostMapping("")
    public ResponseEntity<Category> create(@RequestBody Category categoryRequest) {
        try {
            Category _category = categoryRepository
                    .save(new Category(categoryRequest.getName()));
            return new ResponseEntity<>(_category, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
