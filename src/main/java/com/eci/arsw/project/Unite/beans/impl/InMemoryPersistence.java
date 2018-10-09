package com.eci.arsw.project.Unite.beans.impl;

import com.eci.arsw.project.Unite.model.Event;
import com.eci.arsw.project.Unite.services.UniteException;
import java.util.List;

/**
 *
 * @author sergio
 */
public class InMemoryPersistence implements UnitePersitence{

    @Override
    public void createAccount(String username, String mail, String name, String password) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void createEvent(String owner) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

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
    
}
