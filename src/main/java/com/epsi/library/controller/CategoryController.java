package com.epsi.library.controller;

import com.epsi.library.entity.Borrow;
import com.epsi.library.entity.Category;
import com.epsi.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    /**
     * Allows to update a category
     *
     * @param categoryRequest
     * @return 200 / 404 / 500 HTTP code
     */
    @PutMapping("")
    public ResponseEntity<Category> update(@RequestBody Category categoryRequest) {
        try {
            if (categoryRequest.getId() != null) {
                Optional<Category> _category = categoryRepository.findById(categoryRequest.getId());
                if (_category.isPresent()) {
                    Category _categoryData = _category.get();
                    _categoryData.setName(categoryRequest.getName());

                    return new ResponseEntity<>(categoryRepository.save(_categoryData), HttpStatus.OK);

                }
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Allows to delete a category
     *
     * @param id Category Id
     * @return 201 / 404 / 500 HTTP Code
     */
    @DeleteMapping("")
    public ResponseEntity<Borrow> delete(@RequestParam Long id) {
        try {
            Optional<Category> _category = categoryRepository.findById(id);
            if (_category.isPresent()) {
                categoryRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
