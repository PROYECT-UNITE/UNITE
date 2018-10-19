package com.eci.arsw.project.unite.beans.impl;

import com.eci.arsw.project.unite.model.Event;
import com.eci.arsw.project.unite.model.User;
import com.eci.arsw.project.unite.services.UniteException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Service;

/**
 *
 * @author sergio
 */
@Service
public class InMemoryPersistence implements UnitePersitence {

    private int eventCounter;
    private Map<Integer, Event> events;

    public InMemoryPersistence() {
        eventCounter = 0;
        events = new ConcurrentHashMap<>();
    }

    @Override
    public void createAccount(String username, String mail, String name, String password) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void createEvent(Event event) throws UniteException {
        if(events.containsKey(eventCounter)){
            throw new UniteException("Error creating a new event.");
        }else{
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
        if(events.containsKey(id)){
            return events.get(id);
        }else{
            throw new UniteException(String.format("Event with id: %d not found.",id));
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
    public void changeEventName(int id, String name) throws UniteException {
        this.getEvent(id).changeName(name);
    }

}
