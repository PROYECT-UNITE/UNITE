package com.eci.arsw.project.Unite.beans.impl;

import com.eci.arsw.project.Unite.model.Event;
import com.eci.arsw.project.Unite.services.UniteException;
import java.util.List;

/**
 *
 * @author sergio
 */
public interface UnitePersitence {

    void createAccount(String username, String mail, String name, String password) throws UniteException;

    void createEvent(String owner) throws UniteException;

    List<Event> getEvents() throws UniteException;

    Event getEvent(String username, String eventName) throws UniteException;

    List<Event> getEventsByUser(String username) throws UniteException;
}
