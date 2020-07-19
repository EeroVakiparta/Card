package com.arue.card.controller;

import com.arue.card.model.Card;
import com.arue.card.model.User;
import com.arue.card.repositories.CardRepository;
import com.arue.card.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("admin")
@RestController
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String adminHome() {
        return ("<h1> Admin Page </h1>");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody final User user) {
        return userRepository.saveAndFlush(user);
    }


}
