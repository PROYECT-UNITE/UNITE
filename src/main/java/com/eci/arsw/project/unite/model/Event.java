package com.eci.arsw.project.unite.model;

import com.eci.arsw.project.unite.services.UniteException;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author sergio
 */
public class Event {

    @Id
    private int id;

    private String name;
    private String type;
    private int budget;
    private String owner;
    private List<Date> possibleDates;
    private Map<String, String> assistantsState;
    private Chat chat;
    private Chat linkChat;
    private Poll poll;
    private ItemSet gather;
    private ItemSet checklist;
    private String location;
    private String date;
    private String description;

    public Event(String owner, String name, String type, int budget) {
        this.owner = owner;
        this.name = name;
        this.type = type;
        this.budget = budget;
        possibleDates = new CopyOnWriteArrayList<>();
        assistantsState = new ConcurrentHashMap<>();
        chat = new Chat();
        linkChat = new Chat();
        gather = new ItemSet();
    }

    public Event() {
        possibleDates = new CopyOnWriteArrayList<>();
        assistantsState = new ConcurrentHashMap<>();
        chat = new Chat();
        gather = new ItemSet();
    }

    public void addMember(User member,String state) {
        assistantsState.put(member.getUsername(), state);
    }

    public void removeMember(User memeber) {
        assistantsState.remove(memeber.getUsername());
    }

    public void addMemberByMail(String mail) {

    }

    public void removeMemberByMail(String mail) {

    }

    public void changeStateOfUser(String username, String state) throws UniteException {
        if (assistantsState.containsKey(username)) {
            assistantsState.put(username, state);
        } else {
            throw new UniteException("User not assist to this event.");
        }
    }

    public ItemSet getChecklist() {
        return checklist;
    }

    public void setChecklist(ItemSet checklist) {
        this.checklist = checklist;
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

    public Map<String, String> getAssistantsState() {
        return assistantsState;
    }

    public void setAssistantsState(Map<String, String> assistantsState) {
        this.assistantsState = assistantsState;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Chat getLinkChat() {
        return linkChat;
    }

    public void setLinkChat(Chat linkChat) {
        this.linkChat = linkChat;
    }

    public ItemSet getGather() {
        return gather;
    }

    public void setGather(ItemSet gather) {
        this.gather = gather;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", budget=" + budget +
                ", owner='" + owner + '\'' +
                ", possibleDates=" + possibleDates +
                ", assistantsState=" + assistantsState +
                ", chat=" + chat +
                ", linkChat=" + linkChat +
                ", poll=" + poll +
                ", gather=" + gather +
                ", checklist=" + checklist +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
