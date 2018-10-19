package com.eci.arsw.project.unite.beans.impl;

import com.eci.arsw.project.unite.model.Event;
import com.eci.arsw.project.unite.model.User;
import com.eci.arsw.project.unite.services.UniteException;
import java.util.List;

/**
 *
 * @author sergio
 */
public interface UnitePersitence {

    void createAccount(String username, String mail, String name, String password) throws UniteException;

    void createEvent(Event event) throws UniteException;

    List<Event> getEvents() throws UniteException;

    Event getEvent(int id) throws UniteException;

    List<Event> getEventsByUser(String username) throws UniteException;

    User getUser(String username) throws UniteException;

    public void changeEventName(int id, String name) throws UniteException;

}
