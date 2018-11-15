package com.eci.arsw.project.unite.beans.impl;

import com.eci.arsw.project.unite.beans.UnitePersitence;
import com.eci.arsw.project.unite.model.Event;
import com.eci.arsw.project.unite.model.Message;
import com.eci.arsw.project.unite.model.User;
import com.eci.arsw.project.unite.repository.EventRepository;
import com.eci.arsw.project.unite.services.UniteException;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author 2135494
 */
public class MongodbPersistance implements UnitePersitence {

    @Autowired
    private EventRepository repository;

    @Override
    public void createAccount(String username, String mail, String name, String password) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createAccount(User user) throws UniteException {
//        User userFind = repository.(user.getUsername());
//        if (userFind == null) {
//            
//        } else {
//            throw new UniteException("Username is already taken");
//        }
    }

    @Override
    public void createEvent(Event event) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Event> getEvents() throws UniteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Event getEvent(int id) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Event> getEventsByUser(String username) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUser(String username) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUserByMail(String mail) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changeEventName(int id, String name) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateUser(String username, User user) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkUserAndPwd(String username, String pwd) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<String> getAllUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void joinToEventByMail(int id, String username) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void joinToEventByUsername(int id, String username) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveMessage(int eventId, Message message) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Message> getMessagesByEvent(int eventId) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveLink(int eventId, Message message) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Message> getLinkByEvent(int eventId) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Event> getEventsInvitedByUser(String username) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<User> getAssistanceToEvent(int eventId) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void changeStateOfAssitance(int eventId, String username, String state) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
