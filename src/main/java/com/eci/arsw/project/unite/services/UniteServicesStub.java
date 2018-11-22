package com.eci.arsw.project.unite.services;

import com.eci.arsw.project.unite.beans.UnitePersitence;
import com.eci.arsw.project.unite.model.Event;
import com.eci.arsw.project.unite.model.Message;
import com.eci.arsw.project.unite.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sergio
 */
@Service
public class UniteServicesStub implements UniteServices {

    @Autowired
    UnitePersitence persistence;

    @Override
    public List<Event> getEvents() throws UniteException {
        return persistence.getEvents();
    }

    @Override
    public Event getEvent(int id) throws UniteException {
        return persistence.getEvent(id);
    }

    @Override
    public List<Event> getEventsByUser(String username) throws UniteException {
        return persistence.getEventsByUser(username);
    }

    @Override
    public void createAccount(User user) throws UniteException {
        persistence.createAccount(user);
    }

    @Override
    public void createEvent(Event event) throws UniteException {
        //persistence.getUser(event.getOwner());
        persistence.createEvent(event);
    }

    @Override
    public void changeEventName(int id, String name) throws UniteException {
        persistence.changeEventName(id, name);
    }
    
    @Override

    public void updateUser(String username, User user) throws UniteException{
        persistence.updateUser(username, user);
    }

    @Override
    public boolean grantAccess(String username, String pwd) throws UniteException {
        return persistence.checkUserAndPwd(username,pwd);
    }

    @Override
    public Collection<String> getAllUsers() {
        return persistence.getAllUsers();
    }

    @Override
    public void createAccount(String username, String mail, String name, String password) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void joinToEventByUsername(int id, String username) throws UniteException {
        persistence.joinToEventByUsername(id, username);
    }

    @Override
    public void joinToEventByMail(int id, String mail) throws UniteException {
        persistence.joinToEventByMail(id, mail);
    }

    @Override
    public List<Message> getMessagesByEvent(int eventId) throws UniteException {
        return persistence.getMessagesByEvent(eventId);
    }

    @Override
    public void saveMessage(int eventId, Message message) throws UniteException {
        persistence.saveMessage(eventId, message);
    }

    @Override
    public List<Message> getLinkByEvent(int eventId) throws UniteException {
        return persistence.getLinkByEvent(eventId);
    }

    @Override
    public void saveLink(int eventId, Message message) throws UniteException {
        persistence.saveLink(eventId, message);
    }

    @Override
    public List<Event> getEventsInvitedByUser(String username) throws UniteException {
        return persistence.getEventsInvitedByUser(username);
    }
    
    @Override
    public List<User> getAssistanceToEvent(int eventId) throws UniteException {
        return persistence.getAssistanceToEvent(eventId);
    }

    @Override
    public void changeStateOfAssitance(int eventId, String username, String state) throws UniteException {
        persistence.changeStateOfAssitance(eventId,username,state);
    }

    @Override
    public void updatePassword(String username, String newPassword) throws UniteException {
        persistence.changePassword(username, newPassword);
    }

    @Override
    public void saveEventLocation(int eventId, String longitude, String latitude) throws UniteException {
        persistence.saveEventLocation(eventId,longitude,latitude);
    }

}
