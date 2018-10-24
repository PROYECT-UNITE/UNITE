package com.eci.arsw.project.unite.services;

import com.eci.arsw.project.unite.beans.UnitePersitence;
import com.eci.arsw.project.unite.model.Event;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sergio
 */
@Service
public class UniteServicesStub implements UniteServices{

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
    public void createAccount(String username, String mail, String name, String password) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet.");
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
    public void joinToEvent(int id, String username) throws UniteException {
        persistence.joinToEvent(id,username);
    }
    
}
