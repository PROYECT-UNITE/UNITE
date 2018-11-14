package com.eci.arsw.project.unite.beans.impl;

import com.eci.arsw.project.unite.beans.UnitePersitence;
import com.eci.arsw.project.unite.model.Chat;
import com.eci.arsw.project.unite.model.Event;
import com.eci.arsw.project.unite.model.Message;
import com.eci.arsw.project.unite.model.User;
import com.eci.arsw.project.unite.services.UniteException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author sergio
 */
@Service
public class InMemoryPersistence implements UnitePersitence {

    private int eventCounter;
    private Map<Integer, Event> events;
    private Map<String, User> uniteUsers;

    public InMemoryPersistence() {
        eventCounter = 0;
        events = new ConcurrentHashMap<>();
        try {
            createEvent(new Event("user","PEventoPrueba","PARTY",100000));
        } catch (UniteException e) {
            e.printStackTrace();
        }
        uniteUsers = new ConcurrentHashMap<>();
        try {
            uniteUsers.put("JuanDDuenas", new User("JuanDDuenas", "passwordnob1", "juan.duenas@gmail.com", "Juan Dueñas"));
            uniteUsers.put("SergioR", new User("SergioR", "Sergio1", "sergior@gmail.com", "Sergio Rodriguez"));
            uniteUsers.put("NicGarcia", new User("NicGarcia", "nicolas2", "nicolas.garcia@gmail.com", "Nicolas Garcia"));
        } catch (UniteException ex) {
            Logger.getLogger(InMemoryPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void createAccount(String username, String mail, String name, String password) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void createEvent(Event event) throws UniteException {
        if (events.containsKey(eventCounter)) {
            throw new UniteException("Error creating a new event.");
        } else {
            event.setId(eventCounter);
            events.put(eventCounter++, event);
        }
    }

    @Override
    public List<Event> getEvents() throws UniteException {
        return new CopyOnWriteArrayList<>(events.values());
    }

    @Override
    public Event getEvent(int id) throws UniteException {
        if (events.containsKey(id)) {
            return events.get(id);
        } else {
            throw new UniteException(String.format("Event with id: %d not found.", id));
        }
    }

    @Override
    public List<Event> getEventsByUser(String username) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public User getUser(String username) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public User getUserByMail(String mail) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void changeEventName(int id, String name) throws UniteException {
        this.getEvent(id).changeName(name);
    }
    
    @Override
    public void createAccount(User user) throws UniteException {
        if (uniteUsers.containsKey(user.getUsername())) {
            throw new UniteException("Username is already taken");
        } else {
            uniteUsers.put(user.getUsername(), user);
        }
    }

    @Override
    public void updateUser(String username, User user) throws UniteException {
        if (!uniteUsers.containsKey(user.getUsername())) {
            throw new UniteException("User with given username does not exist");
        } else {
            uniteUsers.replace(username, user);
        }
    }

    @Override
    public boolean checkUserAndPwd(String username, String pwd) throws UniteException {
        if (uniteUsers.get(username)==null) {
            throw new UniteException("User with given username does not exist");
        } else {
            return uniteUsers.get(username).getPassword().equals(pwd);
        }
        
    }

    @Override
    public Set<String> getAllUsers() {
        return uniteUsers.keySet();
    }
    
    
    @Override
    public void joinToEventByUsername(int id, String username) throws UniteException {
        Event event = events.get(id);
        event.addMember(this.getUser(username));
    }

    @Override
    public void joinToEventByMail(int id, String mail) throws UniteException {
        Event event = events.get(id);
        event.addMember(this.getUserByMail(mail));
    }

    @Override
    public void saveMessage(int eventId, Message message) throws UniteException {
        Chat chat = getEvent(eventId).getChat();
        chat.saveMessage(message);
    }

    @Override
    public List<Message> getMessagesByEvent(int eventId) throws UniteException {
        Chat chat = getEvent(eventId).getChat();
        return chat.getRecord();
    }

}
