package com.eci.arsw.project.unite.model;

import com.eci.arsw.project.unite.services.UniteException;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.eci.arsw.project.unite.services.UniteException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.annotation.Id;

/**
 *
 * @author sergio
 */
public class Event {
    
    @Id
    private int id;
    
    private String name;
    private String type;
    private int budget;
    private String owner;
    private List<User> assistants;
    private List<Date> possibleDates;
    private Map<String, String> assistantsState;
    private Chat chat;
    private Chat linkChat;
    private Poll poll;
    private Gather gather;
    private String location;
    private Date date;
    private String description;

    public Event(String owner, String name, String type, int budget){
        this.owner = owner;
        this.name = name;
        this.type = type;
        this.budget = budget;
        assistants = new CopyOnWriteArrayList<>();
        possibleDates = new CopyOnWriteArrayList<>();
        assistantsState = new ConcurrentHashMap<>();
        chat = new Chat();
        linkChat = new Chat();
        gather = new Gather();
    }

    public Event() {
        assistants = new CopyOnWriteArrayList<>();
        possibleDates = new CopyOnWriteArrayList<>();
        assistantsState = new ConcurrentHashMap<>();
        chat = new Chat();
        gather = new Gather();
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void addMember(User member) {
        assistants.add(member);
        assistantsState.put(member.getUsername(), "indeterminate");
    }

    public void removeMember(User memeber) {

    }

    public void addMemberByMail(String mail) {

    }

    public void removeMemberByMail(String mail) {

    }

    public void changeStateOfUser(String username, String state) throws UniteException {
        if(assistantsState.containsKey(username)){
            assistantsState.put(username, state);
        }else{
            throw new UniteException("User not assist to this event.");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<User> getAssistants() {
        return assistants;
    }

    public List<Date> getPossibleDates() {
        return possibleDates;
    }

    public Map<String, String> getAssistantsState() {
        return assistantsState;
    }

    public Chat getChat() {
        return chat;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public Gather getGather() {
        return gather;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Chat getLinkChat() {
        return linkChat;
    }

    public void setLinkChat(Chat linkChat) {
        this.linkChat = linkChat;
    }
    
    public String getDescription() { return description; }

    public void setDescription(String description) {  this.description = description; }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", budget=" + budget +
                ", owner='" + owner + '\'' +
                ", assistants=" + assistants +
                ", possibleDates=" + possibleDates +
                ", assistantsState=" + assistantsState +
                ", chat=" + chat +
                ", poll=" + poll +
                ", gather=" + gather +
                ", location=" + location +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }

}
