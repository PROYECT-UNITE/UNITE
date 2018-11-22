package com.eci.arsw.project.unite.services;

import com.eci.arsw.project.unite.model.Event;
import com.eci.arsw.project.unite.model.Message;
import com.eci.arsw.project.unite.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 *
 * @author sergio
 */
public interface UniteServices {

    List<Event> getEvents() throws UniteException;

    Event getEvent(int id) throws UniteException;

    List<Event> getEventsByUser(String username) throws UniteException;

    List<Event> getEventsInvitedByUser(String username) throws UniteException; 
    
    void createEvent(Event event) throws UniteException;

    void changeEventName(int id, String name) throws UniteException;

    void createAccount(User user) throws UniteException;

    void updateUser(String username, User user) throws UniteException;

    boolean grantAccess(String username, String pwd) throws UniteException;

    Collection<String> getAllUsers();

    void createAccount(String username, String mail, String name, String password) throws UniteException;

    void joinToEventByUsername(int id, String username) throws UniteException;

    void joinToEventByMail(int id, String mail) throws UniteException;

    List<Message> getMessagesByEvent(int eventId) throws UniteException;

    void saveMessage(int eventId, Message message) throws UniteException;
    
    List<Message> getLinkByEvent(int eventId) throws UniteException;

    void saveLink(int eventId, Message message) throws UniteException;
    
    List<User> getAssistanceToEvent(int eventId) throws UniteException;

    void changeStateOfAssitance(int eventId, String username, String state) throws UniteException;

    void updatePassword(String username, String newPassword) throws UniteException;

    void saveEventLocation(int eventId, String longitude, String latitude) throws UniteException;

}
