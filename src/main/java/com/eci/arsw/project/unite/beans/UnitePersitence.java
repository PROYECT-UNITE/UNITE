package com.eci.arsw.project.unite.beans;

import com.eci.arsw.project.unite.model.*;
import com.eci.arsw.project.unite.services.UniteException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author sergio
 */
public interface UnitePersitence {

    void createAccount(User user) throws UniteException;

    int createEvent(Event event) throws UniteException;

    List<Event> getEvents() throws UniteException;

    Event getEvent(int id) throws UniteException;

    List<Event> getEventsByUser(String username) throws UniteException;

    User getUser(String username) throws UniteException;

    User getUserByMail(String mail) throws UniteException;

    void changeEventName(int id, String name) throws UniteException;

    void updateUser(String username, User user) throws UniteException;

    boolean checkUserAndPwd(String username, String pwd) throws UniteException;

    Collection<String> getAllUsers();

    void saveMessage(int eventId, Message message) throws UniteException;

    List<Message> getMessagesByEvent(int eventId) throws UniteException;

    void saveLink(int eventId, Message message) throws UniteException;

    List<Message> getLinkByEvent(int eventId) throws UniteException;

    List<Event> getEventsInvitedByUser(String username) throws UniteException;

    Map<String,String> getAssistanceToEvent(int eventId) throws UniteException;

    void changeStateOfAssitance(int eventId, String username, String state) throws UniteException;

    void changePassword(String username, String newPassword) throws UniteException;

    void saveEventLocation(int eventId, String longitude, String latitude) throws UniteException;

    void inviteToEvent(int eventId, String username) throws UniteException;

    ItemSet getGatherOfEvent(int eventId) throws UniteException;

    void addItem(int eventId, Item item) throws UniteException;

    void removeItem(int eventId, Item item) throws UniteException;

    Poll getPollOfEvent(int eventId) throws UniteException;

    void takeChargeItem(int eventId, Item item) throws UniteException;

    void addTopicToEvent(int eventId, Topic topic) throws UniteException;

    void removeTopicToEvent(int eventId, Topic topic) throws UniteException;

    Topic voteForTopicInEvent(int eventId, String username, Topic topic) throws UniteException;

    void addItemChecklist(int eventId, Item item) throws UniteException;

    void removeItemChecklist(int eventId, Item item) throws UniteException;

    void takeChargeItemChecklist(int eventId, Item item) throws UniteException;

    void changeDescription(int eventId, String newDescription) throws UniteException;

    void deleteEvent(int eventId) throws UniteException;
}
