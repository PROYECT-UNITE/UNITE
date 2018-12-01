package com.eci.arsw.project.unite.beans.impl;

import com.eci.arsw.project.unite.beans.UnitePersitence;
import com.eci.arsw.project.unite.model.*;
import com.eci.arsw.project.unite.repository.EventRepository;
import com.eci.arsw.project.unite.repository.EventsByUserRepository;
import com.eci.arsw.project.unite.repository.EventsInvitedByUserRepository;
import com.eci.arsw.project.unite.repository.UsersRepository;
import com.eci.arsw.project.unite.services.UniteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author SergioRt
 */

@Service
public class MongodbPersistance implements UnitePersitence {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EventsByUserRepository eventsByUserRepository;

    @Autowired
    private EventsInvitedByUserRepository eventsInvitedByUserRepository;

    private Integer eventCounter;

    @Override
    public void createAccount(String username, String mail, String name, String password) throws UniteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createAccount(User user) throws UniteException {
        boolean exists = usersRepository.existsById(user.getUsername());

        if (!exists) {
            usersRepository.save(user);
        } else {
            throw new UniteException("Username is already taken");
        }
    }

    @Override
    public void createEvent(Event event) throws UniteException {
        if (eventCounter == null) {
            eventCounter = getCounter();
        }
        if (eventRepository.findById(eventCounter).isPresent()) {
            throw new UniteException("Error creating a new event.");
        }
        event.setId(eventCounter++);
        eventRepository.save(event);
        String owner = event.getOwner();
        Optional<EventsByUser> events = eventsByUserRepository.findById(owner);
        if (events.isPresent()) {
            EventsByUser eventsByUser = events.get();
            eventsByUser.getEvents().add(event);
            eventsByUserRepository.save(eventsByUser);

        } else {
            List<Event> eventList;
            eventList = new CopyOnWriteArrayList<>();
            eventList.add(event);
            eventsByUserRepository.save(new EventsByUser(owner, eventList));
        }

    }

    @Override
    public List<Event> getEvents() throws UniteException {
        return eventRepository.findAll();
    }

    @Override
    public Event getEvent(int id) throws UniteException {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            return event.get();
        } else {
            throw new UniteException(String.format("Event with id: %d not found.", id));
        }

    }

    @Override
    public List<Event> getEventsByUser(String username) throws UniteException {
        Optional<EventsByUser> eventsByUser = eventsByUserRepository.findById(username);
        if (eventsByUser.isPresent()) {
            return eventsByUser.get().getEvents();
        } else {
            throw new UniteException("No found user " + username);
        }
    }

    @Override
    public User getUser(String username) throws UniteException {
        Optional<User> user = usersRepository.findById(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UniteException("No found user " + username);
        }
    }

    @Override
    public User getUserByMail(String mail) throws UniteException {
        User user = usersRepository.findByMail(mail);
        if (user == null) {
            throw new UniteException("User with given mail does not exist");
        } else {
            return user;
        }
    }

    @Override
    public void changeEventName(int id, String name) throws UniteException {
        Event event = this.getEvent(id);
        event.setName(name);
        eventRepository.save(event);
        EventsByUser eventsByUser = eventsByUserRepository.findByEventsContainsId(id);
        for (Event e : eventsByUser.getEvents())
            if (e.getId() == id) e.setName(name);
        eventsByUserRepository.save(eventsByUser);
    }

    @Override
    public void updateUser(String username, User user) throws UniteException {
        boolean exists = usersRepository.existsById(username);
        if (exists) {
            usersRepository.save(user);
        } else {
            throw new UniteException("No found user " + username);
        }
    }

    @Override
    public boolean checkUserAndPwd(String username, String pwd) throws UniteException {
        Optional<User> user = usersRepository.findById(username);
        if (user.isPresent()) {
            return user.get().getPassword().equals(pwd);
        } else {
            throw new UniteException("User with given username does not exist");
        }
    }

    @Override
    public Collection<String> getAllUsers() {
        List<User> users = usersRepository.findAll();
        List<String> usernames = new ArrayList<>();
        for (User u : users
        ) {
            usernames.add(u.getUsername());
        }
        return usernames;
    }

    @Override
    public void joinToEventByMail(int id, String mail) throws UniteException {
        Event event = this.getEvent(id);
        event.addMember(this.getUserByMail(mail));
        eventRepository.save(event);
    }

    @Override
    public void joinToEventByUsername(int id, String username) throws UniteException {
        Event event = this.getEvent(id);
        event.addMember(this.getUser(username));
        eventRepository.save(event);
    }

    @Override
    public void saveMessage(int eventId, Message message) throws UniteException {
        Event event = getEvent(eventId);
        event.getChat().saveMessage(message);
        eventRepository.save(event);
    }

    @Override
    public List<Message> getMessagesByEvent(int eventId) throws UniteException {
        return getEvent(eventId).getChat().getRecord();
    }

    @Override
    public void saveLink(int eventId, Message message) throws UniteException {
        Event event = getEvent(eventId);
        event.getChat().saveMessage(message);
        eventRepository.save(event);
    }

    @Override
    public List<Message> getLinkByEvent(int eventId) throws UniteException {
        return getEvent(eventId).getLinkChat().getRecord();
    }

    @Override
    public List<Event> getEventsInvitedByUser(String username) throws UniteException {
        Optional<EventsInvitedByUser> EventsInvitedByUser = eventsInvitedByUserRepository.findById(username);
        if (EventsInvitedByUser.isPresent()) {
            return EventsInvitedByUser.get().getEvents();
        } else {
            throw new UniteException("The user is not invited to any event");
        }
    }

    @Override
    public List<User> getAssistanceToEvent(int eventId) throws UniteException {
        return getEvent(eventId).getAssistants();
    }

    @Override
    public void changeStateOfAssitance(int eventId, String username, String state) throws UniteException {
        Event event = getEvent(eventId);
        event.changeStateOfUser(username, state);
        eventRepository.save(event);
    }

    @Override
    public void changePassword(String username, String newPassword) throws UniteException {
        User user = getUser(username);
        user.setPassword(newPassword);
        usersRepository.save(user);
    }

    @Override
    public void saveEventLocation(int eventId, String longitude, String latitude) throws UniteException {
        Event event = getEvent(eventId);
        event.setLocation("lon: " + longitude + " lat: " + latitude);
        eventRepository.save(event);
    }

    @Override
    public void inviteToEvent(int eventId, String username) throws UniteException {
        Event event = getEvent(eventId);
        Optional<EventsInvitedByUser> events = eventsInvitedByUserRepository.findById(username);
        if (events.isPresent()) {
            EventsInvitedByUser eventsInvitedByUser = events.get();
            eventsInvitedByUser.getEvents().add(event);
            eventsInvitedByUserRepository.save(eventsInvitedByUser);

        } else {
            List<Event> eventList;
            eventList = new CopyOnWriteArrayList<>();
            eventList.add(event);
            eventsInvitedByUserRepository.save(new EventsInvitedByUser(username, eventList));
        }

    }

    @Override
    public Gather getGatherOfEvent(int eventId) throws UniteException {
        return getEvent(eventId).getGather();
    }

    @Override
    public void addItem(int eventid, Item item) throws UniteException {
        Event event = getEvent(eventid);
        event.getGather().addItem(item);
        eventRepository.save(event);
    }

    @Override
    public void removeItem(int eventId, Item item) throws UniteException {
        Event event = getEvent(eventId);
        event.getGather().removeItem(item);
        eventRepository.save(event);
    }

    private int getCounter() {
        List<Event> events = eventRepository.findAll();
        int counter = 0;
        for (Event e : events
        ) {
            counter = java.lang.Math.max(counter, e.getId());
        }
        return counter + 1;
    }


}
