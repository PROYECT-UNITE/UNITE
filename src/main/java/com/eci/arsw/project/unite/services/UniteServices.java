package com.eci.arsw.project.unite.services;

import com.eci.arsw.project.unite.model.*;

import java.util.Collection;
import java.util.List;

/**
 * @author sergio
 */
public interface UniteServices {

    List<Event> getEvents() throws UniteException;

    Event getEvent(int id) throws UniteException;

    List<Event> getEventsByUser(String username) throws UniteException;

    List<Event> getEventsInvitedByUser(String username) throws UniteException;

    int createEvent(Event event) throws UniteException;

    void changeEventName(int id, String name) throws UniteException;

    void createAccount(User user) throws UniteException;

    void updateUser(String username, User user) throws UniteException;

    boolean grantAccess(String username, String pwd) throws UniteException;

    Collection<String> getAllUsers();

    void joinToEventByUsername(int id, String username) throws UniteException;

    void joinToEventByMail(int id, String mail) throws UniteException;

    List<Message> getMessagesByEvent(int eventId) throws UniteException;

    void saveMessage(int eventId, Message message) throws UniteException;

    List<Message> getLinkByEvent(int eventId) throws UniteException;

    void saveLink(int eventId, Message message) throws UniteException;

    List<User> getAssistanceToEvent(int eventId) throws UniteException;

    void changeStateOfAssitance(int eventId, String username, String state) throws UniteException;

    void updatePassword(String username, String newPassword) throws UniteException;

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
}
