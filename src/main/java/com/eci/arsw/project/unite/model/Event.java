package com.eci.arsw.project.unite.model;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author sergio
 */
public class Event {

    private int id;
    private String name;
    private String type;
    private int budget;
    private User owner;
    private List<User> assistants;
    private List<User> confirmedAssistants;
    private List<Date> possibleDates;
    private Map<String, String> assistantsState;
    private Chat chat;
    private Poll poll;
    private Gather gather;
    private Location location;
    private Date date;
    
    public Event(User owner, String name, String type, int budget){
        this.owner = owner;
        this.name = name;
        this.type = type;
        this.budget = budget;
        assistants = new CopyOnWriteArrayList<>();
        confirmedAssistants = new CopyOnWriteArrayList<>();
        possibleDates = new CopyOnWriteArrayList<>();
        assistantsState = new ConcurrentHashMap<>();
        chat = new Chat();
        gather = new Gather();
        
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void addMember(User member) {

    }

    public void removeMember(User memeber) {

    }

    public void confirmAttendanceOfAMember(String username) {

    }

    public void addMemberByMail(String mail) {

    }

    public void removeMemberByMail(String mail) {

    }

    public void changeStateOfUser(String username, String state) {

    }

    public void setId(int id) {
        this.id = id;
    }  

}
