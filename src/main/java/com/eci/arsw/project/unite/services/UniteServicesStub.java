package com.eci.arsw.project.unite.services;

import com.eci.arsw.project.unite.beans.UnitePersitence;
import com.eci.arsw.project.unite.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author sergio
 */
@Service
public class UniteServicesStub implements UniteServices {


    @Autowired
    UnitePersitence persistence;

    public UniteServicesStub() {
    }

    public UniteServicesStub(UnitePersitence persistence) {
        this.persistence = persistence;
    }

    @Override
    public List<Event> getEvents() throws UniteException {
        return persistence.getEvents();
    }

    @Override
    public Event getEvent(int id) throws UniteException {
        return persistence.getEvent(id);
    }

    @Override
    public List<Event> getEventsByUser(String username) throws UniteException {
        return persistence.getEventsByUser(username);
    }

    @Override
    public void createAccount(User user) throws UniteException {
        persistence.createAccount(user);
    }

    @Override
    public int createEvent(Event event) throws UniteException {
        return persistence.createEvent(event);

    }

    @Override
    public void changeEventName(int id, String name) throws UniteException {
        persistence.changeEventName(id, name);
    }

    @Override

    public void updateUser(String username, User user) throws UniteException {
        persistence.updateUser(username, user);
    }

    @Override
    public boolean grantAccess(String username, String pwd) throws UniteException {
        return persistence.checkUserAndPwd(username, pwd);
    }

    @Override
    public Collection<String> getAllUsers() {
        return persistence.getAllUsers();
    }

    @Override
    public void joinToEventByUsername(int id, String username) throws UniteException {
        persistence.joinToEventByUsername(id, username);
    }

    @Override
    public void joinToEventByMail(int id, String mail) throws UniteException {
        persistence.joinToEventByMail(id, mail);
    }

    @Override
    public List<Message> getMessagesByEvent(int eventId) throws UniteException {
        return persistence.getMessagesByEvent(eventId);
    }

    @Override
    public void saveMessage(int eventId, Message message) throws UniteException {
        persistence.saveMessage(eventId, message);
    }

    @Override
    public List<Message> getLinkByEvent(int eventId) throws UniteException {
        return persistence.getLinkByEvent(eventId);
    }

    @Override
    public void saveLink(int eventId, Message message) throws UniteException {
        persistence.saveLink(eventId, message);
    }

    @Override
    public List<Event> getEventsInvitedByUser(String username) throws UniteException {
        return persistence.getEventsInvitedByUser(username);
    }
    
    @Override
    public List<User> getAssistanceToEvent(int eventId) throws UniteException {
        return persistence.getAssistanceToEvent(eventId);
    }

    @Override
    public void changeStateOfAssitance(int eventId, String username, String state) throws UniteException {
        persistence.changeStateOfAssitance(eventId,username,state);
    }



    @Override
    public void updatePassword(String username, String newPassword) throws UniteException {
        persistence.changePassword(username, newPassword);
    }

    @Override
    public void saveEventLocation(int eventId, String longitude, String latitude) throws UniteException {
        persistence.saveEventLocation(eventId, longitude, latitude);
    }

    @Override
    public void inviteToEvent(int eventId, String username) throws UniteException {
        persistence.inviteToEvent(eventId, username);
    }

    @Override
    public ItemSet getGatherOfEvent(int eventId) throws UniteException {
        return persistence.getGatherOfEvent(eventId);
    }

    @Override
    public void addItem(int eventId, Item item) throws UniteException {
        persistence.addItem(eventId, item);
    }

    @Override
    public void removeItem(int eventId, Item item) throws UniteException {
        persistence.removeItem(eventId, item);
    }

    @Override
    public Poll getPollOfEvent(int eventId) throws UniteException {
        return persistence.getPollOfEvent(eventId);
    }

    @Override
    public void takeChargeItem(int eventId, Item item) throws UniteException {
        persistence.takeChargeItem(eventId, item);
    }

    @Override
    public void addTopicToEvent(int eventId, Topic topic) throws UniteException {
        persistence.addTopicToEvent(eventId, topic);
    }

    @Override
    public void removeTopicToEvent(int eventId, Topic topic) throws UniteException {
        persistence.removeTopicToEvent(eventId, topic);
    }

    @Override
    public Topic voteForTopicInEvent(int eventId, String username, Topic topic) throws UniteException {
        return persistence.voteForTopicInEvent(eventId, username, topic);
    }

    @Override
    public void addItemChecklist(int eventId, Item item) throws UniteException {
        persistence.addItemChecklist(eventId, item);
    }

    @Override
    public void removeItemChecklist(int eventId, Item item) throws UniteException {
        persistence.removeItemChecklist(eventId, item);
    }

    @Override
    public void takeChargeItemChecklist(int eventId, Item item) throws UniteException {
        persistence.takeChargeItemChecklist(eventId, item);
    }

    @Override
    public void changeDescription(int eventId, String newDescription) throws UniteException {
        persistence.changeDescription(eventId, newDescription);
    }

}
