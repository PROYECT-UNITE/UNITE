package com.eci.arsw.project.unite.controller;

import com.eci.arsw.project.unite.model.User;
import com.eci.arsw.project.unite.repository.UsersRepository;
import com.eci.arsw.project.unite.services.UniteException;
import com.eci.arsw.project.unite.services.UniteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UniteServices service;

    private UsersRepository usersRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UsersRepository usersRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        if (usersRepository.findById(user.getUsername()).isPresent()) {
            return new ResponseEntity<>("User already exists", HttpStatus.FORBIDDEN);
        } else {
            try {
                user.passwordValid(user.getPassword());
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                service.createAccount(user);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (UniteException e) {
                System.out.println(e.getMessage());
                return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
            }
        }
    }
}