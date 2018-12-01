package com.eci.arsw.project.unite.controller;

import com.eci.arsw.project.unite.model.Event;
import com.eci.arsw.project.unite.model.Item;
import com.eci.arsw.project.unite.model.Message;
import com.eci.arsw.project.unite.model.User;
import com.eci.arsw.project.unite.services.UniteException;
import com.eci.arsw.project.unite.services.UniteServices;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controlador API Rest
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

    @GetMapping("/events/invited/{username}")
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
            System.out.println("Change " + id + " " + name);
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
    public ResponseEntity<?> postCreateAccount(@RequestBody User user) {
        try {
            System.out.println(user);
            service.createAccount(user);
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

    @PutMapping("/changePassword/{username}")
    public ResponseEntity<?> putUpdatePasswordHandler(@PathVariable("username") String username, @RequestBody String newPassword) {
        try {
            newPassword = (String) new JSONObject(newPassword).get("newPassword");
            service.updatePassword(username, newPassword);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/access")
    public ResponseEntity<?> getAccess(@RequestParam String username, @RequestParam String pwd) {
        try {
            boolean ans = service.grantAccess(username, pwd);
            System.out.println("Getting acces to " + username + " " + pwd + " = " + ans);
            return new ResponseEntity<>(ans, HttpStatus.ACCEPTED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/AllUsers")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{eventId}/chat")
    public ResponseEntity<?> getMessagesByEventHandler(@PathVariable("eventId") int eventId) {
        try {
            return new ResponseEntity<>(service.getMessagesByEvent(eventId), HttpStatus.ACCEPTED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{eventId}/link")
    public ResponseEntity<?> getLinksByEventHandler(@PathVariable("eventId") int eventId) {
        try {
            return new ResponseEntity<>(service.getLinkByEvent(eventId), HttpStatus.ACCEPTED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{eventId}/assistance")
    public ResponseEntity<?> getAssistanceToEventHandler(@PathVariable("eventId") int eventId) {
        try {
            return new ResponseEntity<>(service.getAssistanceToEvent(eventId), HttpStatus.ACCEPTED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{eventId}/gather")
    public ResponseEntity<?> getGatherOfEventHandler(@PathVariable("eventId") int eventId) {
        try {
            return new ResponseEntity<>(service.getGatherOfEvent(eventId), HttpStatus.ACCEPTED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{eventID}/location")
    public ResponseEntity<?> postEventLocation(@PathVariable("eventId") int eventId, @RequestParam String longitude, @RequestParam String latitude) {
        try {
            service.saveEventLocation(eventId, longitude, latitude);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{eventID}/invite/{username}")
    public ResponseEntity<?> postInviteToEvent(@PathVariable("eventId") int eventId, @PathVariable("username") String username) {
        try {
            service.inviteToEvent(eventId, username);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @MessageMapping("/newmessage.{eventId}")
    public void handlePointEvent(Message message, @DestinationVariable int eventId) throws Exception {
        System.out.println("New message recived from server!: " + message + " at id: " + eventId);
        msgt.convertAndSend("/topic/newmessage." + eventId, message);
        service.saveMessage(eventId, message);
    }

    /**
     * Resive un nuevo link y lo publica en /topic/newlink.{eventId}
     *
     * @param message mensaje que se publica y resive
     * @param eventId id del evento al que pertenece el mensaje
     * @throws Exception
     */
    @MessageMapping("/newlink.{eventId}")
    public void handleLinkEvent(Message message, @DestinationVariable int eventId) throws Exception {
        System.out.println("New link recived from server!: " + message + " at id: " + eventId);
        msgt.convertAndSend("/topic/newlink." + eventId, message);
        service.saveLink(eventId, message);
    }

    /**
     * Resive un cambio en la asistencia de un evento y lo publica en /topic/assistance.{eventId}
     *
     * @param user    usuario con solo el username y el estado correspondiente JSON{"username":"","state":""}
     * @param eventId id del evento
     * @throws Exception
     */
    @MessageMapping("/assistance.{eventId}")
    public void handleAssistanceEvent(User user, @DestinationVariable int eventId) throws Exception {
        System.out.println("New user state recived from server!: " + user + " at id: " + eventId);
        msgt.convertAndSend("/topic/assistance." + eventId, user);
        service.changeStateOfAssitance(eventId, user.getUsername(), user.getState());
    }

    /**
     * Resive un nuevo item a la vaca de un evento y lo publica en /topic/additem.{eventId}
     *
     * @param item    item publicado
     * @param eventId id del evento
     * @throws Exception
     */
    @MessageMapping("/additem.{eventId}")
    public void handleAddItem(Item item, @DestinationVariable int eventId) throws Exception {
        System.out.println("New item recived from server!: " + item + " at id: " + eventId);
        msgt.convertAndSend("/topic/additem." + eventId, item);
        service.addItem(eventId,item);
    }

    /**
     * Resive un item a la vaca de un evento para eliminar y lo publica en /topic/newitem.{eventId}
     *
     * @param item    item publicado
     * @param eventId id del evento
     * @throws Exception
     */
    @MessageMapping("/removeitem.{eventId}")
    public void handleRemoveItem(Item item, @DestinationVariable int eventId) throws Exception {
        System.out.println("New item to remove recived from server!: " + item + " at id: " + eventId);
        msgt.convertAndSend("/topic/newitem." + eventId, item);
        service.removeItem(eventId,item);
    }

}
