package com.epsi.library.controller;

import com.epsi.library.entity.Member;
import com.epsi.library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class MemberController {
    @Autowired
    MemberRepository memberRepository;

    /**
     * Allows to create a Member.
     *
     * @param memberRequest
     * @return 201 / 400 / 500 HTTP code
     */
    @PostMapping("/register")
    public ResponseEntity<Member> create(@RequestBody Member memberRequest) {
        try {
            Member _member = memberRepository
                    .save(new Member(memberRequest.getEmail(), memberRequest.getFirstname(), memberRequest.getLastname()));
            return new ResponseEntity<>(_member, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
