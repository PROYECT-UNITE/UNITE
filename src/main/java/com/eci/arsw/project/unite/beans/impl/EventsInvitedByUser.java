package com.eci.arsw.project.unite.beans.impl;

import com.eci.arsw.project.unite.model.Event;
import org.springframework.data.annotation.Id;

import java.util.List;

public class EventsInvitedByUser {

    @Id
    private String username;

    private List<Event> events;

    public EventsInvitedByUser() {
    }

    EventsInvitedByUser(String owner, List<Event> events) {
        this.username = owner;
        this.events = events;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

}
