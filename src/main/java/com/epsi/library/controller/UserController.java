package com.epsi.library.controller;

import com.epsi.library.entity.User;
import com.epsi.library.repository.RoleRepository;
import com.epsi.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
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
}
