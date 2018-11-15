package com.eci.arsw.project.unite.beans;

import com.eci.arsw.project.unite.model.Event;
import com.eci.arsw.project.unite.model.Message;
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
    
    User getUserByMail(String mail) throws UniteException;

    void changeEventName(int id, String name) throws UniteException;

    public void updateUser(String username, User user) throws UniteException;

    public boolean checkUserAndPwd(String username, String pwd) throws UniteException;

    public Set<String> getAllUsers();
    
    void joinToEventByMail(int id, String username) throws UniteException;

    void joinToEventByUsername(int id, String username) throws UniteException;

    public void saveMessage(int eventId, Message message) throws UniteException;

    public List<Message> getMessagesByEvent(int eventId) throws UniteException;

    public void saveLink(int eventId, Message message) throws UniteException;

    public List<Message> getLinkByEvent(int eventId) throws UniteException;


    public List<Event> getEventsInvitedByUser(String username) throws UniteException;

    public List<User> getAssistanceToEvent(int eventId) throws UniteException;

    public void changeStateOfAssitance(int eventId, String username, String state) throws UniteException;

}
