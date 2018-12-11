package com.eci.arsw.project.unite.controller;

import com.eci.arsw.project.unite.model.*;
import com.eci.arsw.project.unite.services.UniteException;
import com.eci.arsw.project.unite.services.UniteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
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
            int eventId = service.createEvent(event);
            return new ResponseEntity<>(eventId, HttpStatus.CREATED);
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

    @PutMapping("/{eventId}/description")
    public ResponseEntity<?> putChangeDesciptionHandler(@PathVariable("eventId") int eventId, @RequestBody String newDescription) {
        try {
            service.changeDescription(eventId, newDescription);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
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

    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> deleteEventHandler(@PathVariable("eventId") int eventId) {
        try {
            service.deleteEvent(eventId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{eventId}/poll")
    public ResponseEntity<?> getPollOfEventHandler(@PathVariable("eventId") int eventId) {
        try {
            return new ResponseEntity<>(service.getPollOfEvent(eventId), HttpStatus.ACCEPTED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{eventId}/checklist")
    public ResponseEntity<?> getChecklistOfEventHandler(@PathVariable("eventId") int eventId) {
        try {
            return new ResponseEntity<>(service.getChecklistOfEvent(eventId), HttpStatus.ACCEPTED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{eventId}/location")
    public ResponseEntity<?> postEventLocation(@PathVariable("eventId") int eventId, @RequestParam String longitude, @RequestParam String latitude) {
        try {
            service.saveEventLocation(eventId, longitude, latitude);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{eventId}/invite")
    public ResponseEntity<?> postInviteToEvent(@PathVariable("eventId") int eventId, @RequestBody List<String> usernames) {
        try {

            service.inviteToEvent(eventId, usernames);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @MessageMapping("/newmessage.{eventId}")
    public void handlePointEvent(Message message, @DestinationVariable int eventId) throws UniteException {
        System.out.println("New message recived from server!: " + message + " at id: " + eventId);
        msgt.convertAndSend("/topic/newmessage." + eventId, message);
        service.saveMessage(eventId, message);
    }

    /**
     * Resive un nuevo link y lo publica en /topic/newlink.{eventId}
     *
     * @param message mensaje que se publica y resive
     * @param eventId id del evento al que pertenece el mensaje
     * @throws UniteException
     */
    @MessageMapping("/newlink.{eventId}")
    public void handleLinkEvent(Message message, @DestinationVariable int eventId) throws UniteException {
        System.out.println("New link recived from server!: " + message + " at id: " + eventId);
        msgt.convertAndSend("/topic/newlink." + eventId, message);
        service.saveLink(eventId, message);
    }

    /**
     * Resive un cambio en la asistencia de un evento y lo publica en /topic/assistance.{eventId}
     *
     * @param user    usuario con solo el username y el estado correspondiente JSON{"username":"","state":""}
     * @param eventId id del evento
     * @throws UniteException
     */
    @MessageMapping("/assistance.{eventId}")
    public void handleAssistanceEvent(User user, @DestinationVariable int eventId) throws UniteException {
        System.out.println("New user state recived from server!: " + user + " at id: " + eventId);
        msgt.convertAndSend("/topic/assistance." + eventId, user);
        service.changeStateOfAssitance(eventId, user.getUsername(), user.getState());
    }

    /**
     * Resive un nuevo item a la vaca de un evento y lo publica en /topic/additem.{eventId}
     *
     * @param item    item publicado
     * @param eventId id del evento
     * @throws UniteException
     */
    @MessageMapping("/additem.{eventId}")
    public void handleAddItem(Item item, @DestinationVariable int eventId) throws UniteException {
        System.out.println("New item recived from server!: " + item + " at id: " + eventId);
        msgt.convertAndSend("/topic/additem." + eventId, item);
        service.addItem(eventId, item);
    }

    /**
     * Resive un item a la vaca de un evento para eliminar y lo publica en /topic/removeitem.{eventId}
     *
     * @param item    item publicado
     * @param eventId id del evento
     * @throws UniteException
     */
    @MessageMapping("/removeitem.{eventId}")
    public void handleRemoveItem(Item item, @DestinationVariable int eventId) throws UniteException {
        System.out.println("New item to remove recived from server!: " + item + " at id: " + eventId);
        msgt.convertAndSend("/topic/removeitem." + eventId, item);
        service.removeItem(eventId, item);
    }

    /**
     * Resive un item a la vaca de un evento con el oncharge=Username y lo publica en /topic/takechargeitem.{eventId}
     *
     * @param item    item publicado
     * @param eventId id del evento
     * @throws UniteException
     */
    @MessageMapping("/takechargeitem.{eventId}")
    public void handleTakeChargeItem(Item item, @DestinationVariable int eventId) throws UniteException {
        System.out.println("New item to take charge recived from server!: " + item + " at id: " + eventId);
        msgt.convertAndSend("/topic/takechargeitem." + eventId, item);
        service.takeChargeItem(eventId, item);
    }

    /**
     * Resive un topico de la encuesta en un evento lo agrega y lo publica en /topic/addtopic.{eventId}
     *
     * @param topic   topic publicado
     * @param eventId id del evento
     * @throws UniteException
     */
    @MessageMapping("/addtopic.{eventId}")
    public void handleAddTopicToEvent(Topic topic, @DestinationVariable int eventId) throws UniteException {
        System.out.println("New topic to add recived from server!: " + topic + " at id: " + eventId);
        msgt.convertAndSend("/topic/addtopic." + eventId, topic);
        service.addTopicToEvent(eventId, topic);
    }

    /**
     * Resive un topico de la encuesta en un evento lo elimina y lo publica en /topic/removetopic.{eventId}
     *
     * @param topic   topic publicado
     * @param eventId id del evento
     * @throws UniteException
     */
    @MessageMapping("/removetopic.{eventId}")
    public void handleRemoveTopic(Topic topic, @DestinationVariable int eventId) throws UniteException {
        System.out.println("New topic to remove recived from server!: " + topic + " at id: " + eventId);
        msgt.convertAndSend("/topic/addtopic." + eventId, topic);
        service.removeTopicToEvent(eventId, topic);
    }

    /**
     * Resive un topico de la encuesta en un evento vota en el con el usuario dado y lo publica en /topic/votetopic.{eventId},
     * el topic tiene una lista de los que votaron por el, eso se modifica y debe servir para actualizar el cliente JS suscrito a /topic
     *
     * @param username username del usuario que vota por el topic
     * @param topic    topic publicado
     * @param eventId  id del evento
     * @throws UniteException
     */
    @MessageMapping("/votetopic.{eventId}.{username}")
    public void handleVoteForTopic(Topic topic, @DestinationVariable int eventId, @DestinationVariable String username) throws UniteException {
        System.out.println("New topic to vote recived from server!: " + topic + " at id: " + eventId);
        Topic votedTopic = service.voteForTopicInEvent(eventId, username, topic);
        msgt.convertAndSend("/topic/votetopic." + eventId, votedTopic);
    }

    /**
     * Resive un nuevo item para el checklist de un evento y lo publica en /topic/additemchecklist.{eventId}
     *
     * @param item    item publicado
     * @param eventId id del evento
     * @throws UniteException
     */
    @MessageMapping("/additemchecklist.{eventId}")
    public void handleAddItemChecklist(Item item, @DestinationVariable int eventId) throws UniteException {
        System.out.println("New item recived from server!: " + item + " at id: " + eventId);
        msgt.convertAndSend("/topic/additemchecklist." + eventId, item);
        service.addItemChecklist(eventId, item);
    }

    /**
     * Resive un item para el checklist de un evento para eliminar y lo publica en /topic/removeitemchecklist.{eventId}
     *
     * @param item    item publicado
     * @param eventId id del evento
     * @throws UniteException
     */
    @MessageMapping("/removeitemchecklist.{eventId}")
    public void handleRemoveItemChecklist(Item item, @DestinationVariable int eventId) throws UniteException {
        System.out.println("New item to remove recived from server!: " + item + " at id: " + eventId);
        msgt.convertAndSend("/topic/removeitemchecklist." + eventId, item);
        service.removeItemChecklist(eventId, item);
    }

    /**
     * Resive un item para el checklist de un evento con el oncharge=Username y state=Taken y lo publica en /topic/takechargeitemchecklist.{eventId}
     *
     * @param item    item publicado
     * @param eventId id del evento
     * @throws UniteException
     */
    @MessageMapping("/takechargeitemchecklist.{eventId}")
    public void handleTakeChargeItemChecklist(Item item, @DestinationVariable int eventId) throws UniteException {
        System.out.println("New item to take charge recived from server!: " + item + " at id: " + eventId);
        msgt.convertAndSend("/topic/takechargeitemchecklist." + eventId, item);
        service.takeChargeItemChecklist(eventId, item);
    }

    @MessageMapping("/theWallAt.{eventId}")
    public void putUpdateWall(String wall ,@DestinationVariable("eventId") int eventId) {
        try {
            service.updateWall(eventId,wall);
            msgt.convertAndSend("/topic/theWallAt." + eventId, wall);
        } catch (UniteException ex) {
            Logger.getLogger(UniteException.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
