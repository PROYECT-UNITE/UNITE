package com.eci.arsw.project.unite.services;

import com.eci.arsw.project.unite.model.Event;
import java.util.List;

/**
 *
 * @author sergio
 */
public interface UniteServices {

    List<Event> getEvents() throws UniteException;
    
    Event getEvent(int id) throws UniteException;

    List<Event> getEventsByUser(String username) throws UniteException;
    
    void createAccount(String username,String mail, String name,String password)throws UniteException;
    
    void createEvent(Event event)throws UniteException;
    
    void changeEventName(int id, String name) throws UniteException;
    
}
