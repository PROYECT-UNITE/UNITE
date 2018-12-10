package com.eci.arsw.project.unite.beans.impl;

import com.eci.arsw.project.unite.model.Event;
import org.springframework.data.annotation.Id;

import java.util.List;

public class EventsByUser {

    @Id
    private String username;

    private List<Integer> events;

    public EventsByUser() {
    }

    public EventsByUser(String owner, List<Integer> events) {
        this.username = owner;
        this.events = events;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Integer> getEvents() {
        return events;
    }

    public void setEvents(List<Integer> events) {
        this.events = events;
    }

}
