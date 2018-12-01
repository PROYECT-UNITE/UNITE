package com.eci.arsw.project.unite.beans.impl;

import com.eci.arsw.project.unite.beans.UnitePersitence;
import com.eci.arsw.project.unite.model.*;
import com.eci.arsw.project.unite.services.UniteException;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author sergio
 */

public class InMemoryPersistence implements UnitePersitence {

    private int eventCounter;
    private Map<Integer, Event> events;
    private Map<String, User> uniteUsers;
    private Map<String, List<Event>> eventsByUser;
    private Map<String, List<Event>> eventsIvitedByUser;

    public InMemoryPersistence() {
        eventCounter = 0;
        events = new ConcurrentHashMap<>();
        eventsByUser = new ConcurrentHashMap<>();
        eventsIvitedByUser = new ConcurrentHashMap<>();
        uniteUsers = new ConcurrentHashMap<>();
        try {
            createEvent(new Event("user", "PEventoPrueba", "PARTY", 100000));
        } catch (UniteException e) {
            Logger.getLogger(InMemoryPersistence.class.getName()).log(Level.SEVERE, null, e);
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
            String owner = event.getOwner();
            if (eventsByUser.containsKey(owner)) {
                eventsByUser.get(owner).add(event);
            } else {
                List<Event> userEvents = new CopyOnWriteArrayList<>();
                userEvents.add(event);
                eventsByUser.put(owner, userEvents);
            }
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
        if (eventsByUser.containsKey(username)) {
            return eventsByUser.get(username);
        } else {
            throw new UniteException("No found user " + username);
        }

    }

    @Override
    public User getUser(String username) throws UniteException {
        if (uniteUsers.containsKey(username)) {
            return uniteUsers.get(username);
        } else {
            throw new UniteException("No found user " + username);
        }
    }

    @Override
    public User getUserByMail(String mail) throws UniteException {
        for (User u : uniteUsers.values()) {
            if (u.getMail().equals(mail)) {
                return u;
            }
        }
        throw new UniteException("User with given mail does not exist");
    }

    @Override
    public void changeEventName(int id, String name) throws UniteException {
        this.getEvent(id).setName(name);
    }

    @Override
    public void createAccount(User user) throws UniteException {
        if (uniteUsers.containsKey(user.getUsername())) {
            throw new UniteException("Username is already taken.");
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
        if (uniteUsers.get(username) == null) {
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

    @Override
    public void saveLink(int eventId, Message message) throws UniteException {
        Chat chat = getEvent(eventId).getChat();
        chat.saveMessage(message);
    }

    @Override
    public List<Message> getLinkByEvent(int eventId) throws UniteException {
        Chat links = getEvent(eventId).getLinkChat();
        return links.getRecord();
    }

    @Override
    public void changePassword(String username, String newPassword) throws UniteException {
        if (!uniteUsers.containsKey(username)) {
            throw new UniteException("User with given username does not exist");
        } else {
            uniteUsers.get(username).setPassword(newPassword);
        }
    }

    @Override
    public List<Event> getEventsInvitedByUser(String username) throws UniteException {
        if(eventsIvitedByUser.containsKey(username)){
            return eventsIvitedByUser.get(username);
        }
        throw new UniteException("The user is not invited to any event");
    }

    public List<User> getAssistanceToEvent(int eventId) throws UniteException {
        return getEvent(eventId).getAssistants();
    }

    @Override
    public void changeStateOfAssitance(int eventId, String username, String state) throws UniteException {
        getEvent(eventId).changeStateOfUser(username, state);
    }

    @Override
    public void saveEventLocation(int eventId, String longitude, String latitude) {
        events.get(eventId).setLocation("lon: "+longitude +" lat: "+latitude);
    }

    @Override
    public void inviteToEvent(int eventId, String username) throws UniteException {

    }

    @Override
    public Set<Item> getGatherOfEvent(int eventId) throws UniteException {
        return null;
    }

    @Override
    public void addItem(int eventId, Item item) throws UniteException {

    }

    @Override
    public void removeItem(int eventId, Item item) throws UniteException {

    }

}
