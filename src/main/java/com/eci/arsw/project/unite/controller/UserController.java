package com.eci.arsw.project.unite.controller;

import com.eci.arsw.project.unite.model.User;
import com.eci.arsw.project.unite.repository.UsersRepository;
import com.eci.arsw.project.unite.services.UniteException;
import com.eci.arsw.project.unite.services.UniteServices;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

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
                User.passwordValid(user.getPassword());
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                service.createAccount(user);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (UniteException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
            }
        }
    }

    @PutMapping("/changePassword/{username}")
    public ResponseEntity<?> putUpdatePasswordHandler(@PathVariable("username") String username, @RequestBody String newPassword) {
        try {
            newPassword = (String) new JSONObject(newPassword).get("newPassword");
            User.passwordValid(newPassword);
            service.updatePassword(username, bCryptPasswordEncoder.encode(newPassword));
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
}