package com.eci.arsw.project.unite.beans;

import com.eci.arsw.project.unite.model.Event;
import com.eci.arsw.project.unite.model.User;
import com.eci.arsw.project.unite.services.UniteException;
import java.util.List;
import java.util.Set;

/**
 *
 * @author sergio
 */
public interface UnitePersitence {

    void createAccount(String username, String mail, String name, String password) throws UniteException;
    
    public void createAccount(User user) throws UniteException;

    void createEvent(Event event) throws UniteException;

    List<Event> getEvents() throws UniteException;

    Event getEvent(int id) throws UniteException;

    List<Event> getEventsByUser(String username) throws UniteException;

    User getUser(String username) throws UniteException;

    public void changeEventName(int id, String name) throws UniteException;

    public void updateUser(String username, User user) throws UniteException;

    public boolean checkUserAndPwd(String username, String pwd) throws UniteException;

    public Set<String> getAllUsers();
    
    public void joinToEvent(int id, String username) throws UniteException;

}
