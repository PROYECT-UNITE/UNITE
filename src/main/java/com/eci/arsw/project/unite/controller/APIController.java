package com.eci.arsw.project.unite.controller;

import com.eci.arsw.project.unite.model.Event;
import com.eci.arsw.project.unite.model.Message;
import com.eci.arsw.project.unite.model.User;
import com.eci.arsw.project.unite.services.UniteException;
import com.eci.arsw.project.unite.services.UniteServices;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @Autowired
    SimpMessagingTemplate msgt;
    
    @GetMapping
    public ResponseEntity<?> getEventsHandler() {
        try {
            return new ResponseEntity<>(service.getEvents(), HttpStatus.ACCEPTED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/event/{id}")
    public ResponseEntity<?> getEventHandler(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(service.getEvent(id), HttpStatus.ACCEPTED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/events/{username}")
    public ResponseEntity<?> getEventsByUserHandler(@PathVariable("username") String username) {
        try {
            return new ResponseEntity<>(service.getEventsByUser(username), HttpStatus.ACCEPTED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/event/invited/{username}")
    public ResponseEntity<?> getEventsInvitedByUserHandler(@PathVariable("username") String username) {
        try {
            return new ResponseEntity<>(service.getEventsInvitedByUser(username), HttpStatus.ACCEPTED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/newEvent")
    public ResponseEntity<?> postCreateEventHandler(@RequestBody Event event) {
        try {
            service.createEvent(event);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
    
    @PutMapping("/{id}/rename/{name}")
    public ResponseEntity<?> putChangeEventNameHandler(@PathVariable("id") int id, @PathVariable("name") String name) {
        try {
            service.changeEventName(id, name);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
    
    @PutMapping("/{id}/mail/{mail}")
    public ResponseEntity<?> putJoinToEventByMailHandler(@PathVariable("id") int id, @PathVariable("mail") String mail) {
        try {
            service.joinToEventByMail(id, mail);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
    
    @PutMapping("/{id}/user/{username}")
    public ResponseEntity<?> putJoinToEventByUsernameHandler(@PathVariable("id") int id, @PathVariable("username") String username) {
        try {
            service.joinToEventByUsername(id, username);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
   
    @PostMapping("/newAccount")
    public ResponseEntity<?> postCreateAccount(@RequestParam String username,@RequestParam String pwd, @RequestParam String mail, @RequestParam String name) {
        try {
            System.out.println(username +" Pd: "+pwd);
            service.createAccount(new User(username, pwd, mail, name));
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
    @PutMapping("/{username}")
    public ResponseEntity<?> putUpdateUserHandler(@PathVariable("username") String username, @RequestBody User user) {
        try {
            service.updateUser(username, user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
    
    @PostMapping("/access")
    public ResponseEntity<?> getAccess(@RequestParam String username, @RequestParam String pwd) {
        try {
            return new ResponseEntity<>(service.grantAccess(username,pwd), HttpStatus.ACCEPTED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/AllUsers")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/events/{eventId}/chat")
    public ResponseEntity<?> getMessagesByEventHandler(@PathVariable("eventId") int eventId) {
        try {
            return new ResponseEntity<>(service.getMessagesByEvent(eventId), HttpStatus.ACCEPTED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/events/{eventId}/link")
    public ResponseEntity<?> getLinksByEventHandler(@PathVariable("eventId") int eventId) {
        try {
            return new ResponseEntity<>(service.getLinkByEvent(eventId), HttpStatus.ACCEPTED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/events/{eventId}/assistance")
    public ResponseEntity<?> getAssistanceToEventHandler(@PathVariable("eventId") int eventId) {
        try {
            return new ResponseEntity<>(service.getAssistanceToEvent(eventId), HttpStatus.ACCEPTED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @MessageMapping("/newmessage.{eventId}")
    public void handlePointEvent(Message message, @DestinationVariable int eventId) throws Exception {
        System.out.println("New message recived from server!: " +message +" at id: "+eventId);
        msgt.convertAndSend("/topic/newmessage." + eventId, message);
        service.saveMessage(eventId, message);
    }
    
    @MessageMapping("/newlink.{eventId}")
    public void handleLinkEvent(Message message, @DestinationVariable int eventId) throws Exception {
        System.out.println("New link recived from server!: " +message +" at id: "+eventId);
        msgt.convertAndSend("/topic/newlink." + eventId, message);
        service.saveLink(eventId, message);
    }
    
    @MessageMapping("/assistance.{eventId}.{username}")
    public void handleAssistanceEvent(String state, @DestinationVariable int eventId, @DestinationVariable String username) throws Exception {
        System.out.println("New state recived from server!: " +state +" at id: "+eventId+ " username: "+username);
        msgt.convertAndSend("/topic/assistance." + eventId+"."+username, state);
        service.changeStateOfAssitance(eventId, username, state);
    }
    
}
