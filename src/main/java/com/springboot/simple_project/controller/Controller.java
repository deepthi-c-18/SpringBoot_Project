package com.springboot.simple_project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.simple_project.entity.User;
import com.springboot.simple_project.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class Controller {
	 @Autowired
     private UserRepository userRepository;

    // CREATE with validation
	 @PostMapping
	 public Object createUser(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            result.getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
            );

            return errors;
        }

        return userRepository.save(user);
    }

    // READ
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}