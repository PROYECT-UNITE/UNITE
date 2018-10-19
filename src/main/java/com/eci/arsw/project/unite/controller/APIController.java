package com.eci.arsw.project.unite.controller;

import com.eci.arsw.project.unite.model.Event;
import com.eci.arsw.project.unite.services.UniteException;
import com.eci.arsw.project.unite.services.UniteServices;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sergio
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/unite")
public class APIController {
    
    @Autowired
    UniteServices service;
    
    @GetMapping
    public ResponseEntity<?> getEventsHandler() {
        try {
            return new ResponseEntity<>(service.getEvents(), HttpStatus.ACCEPTED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getEventHandler(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(service.getEvent(id), HttpStatus.ACCEPTED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/{username}")
    public ResponseEntity<?> getEventHandler(@PathVariable("username") String username) {
        try {
            return new ResponseEntity<>(service.getEventsByUser(username), HttpStatus.ACCEPTED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
    public ResponseEntity<?> postCreateEventHandler(@RequestBody Event event) {
        try {
            service.createEvent(event);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
    
    @PutMapping("/{id}/{name}")
    public ResponseEntity<?> putChangeEventNameHandler(@PathVariable("id") int id, @PathVariable("name") String name) {
        try {
            service.changeEventName(id, name);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
    
}
