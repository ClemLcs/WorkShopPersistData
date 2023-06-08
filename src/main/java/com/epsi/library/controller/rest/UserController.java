package com.epsi.library.controller.rest;

import com.epsi.library.entity.User;
import com.epsi.library.repository.RoleRepository;
import com.epsi.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/rest/auth")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Allows to create a User.
     *
     * @param userRequest
     * @return 201 / 400 / 500 HTTP code
     */
    @PostMapping("/register")
    public ResponseEntity<User> create(@RequestBody User userRequest) {
        try {
            User _user = userRepository
                    .save(new User(userRequest.getEmail(), userRequest.getFirstname(), userRequest.getLastname(), userRequest.getPassword(), roleRepository.getOrCreateDefaultRole("member")));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Allows to get all users
     *
     * @return 204 / 200 / 500 HTTP code
     */
    @GetMapping("")
    public ResponseEntity<List<User>> getAll() {
        try {
            List<User> users = new ArrayList<User>();
            userRepository.findAll().forEach(users::add);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
