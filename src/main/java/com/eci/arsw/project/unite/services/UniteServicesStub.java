package com.eci.arsw.project.unite.services;

import com.eci.arsw.project.unite.model.Event;
import java.util.List;

/**
 *
 * @author sergio
 */
public class UniteServicesStub implements UniteServices{

    @Override
    public List<Event> getEvents() throws UniteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Event getEvent(String username, String eventName) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Event> getEventsByUser(String username) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void createAccount(String username, String mail, String name, String password) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void createEvent(String owner) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
