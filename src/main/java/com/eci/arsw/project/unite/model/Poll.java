package com.eci.arsw.project.unite.model;

import java.util.Set;

/**
 * @author sergio
 */
public class Poll {

    private String name;
    private Set<Topic> topics;
    private Set<String> voters;

    public Poll() {
    }

    public void addTopic(Topic topic){
        topics.add(topic);
    }

    public void removeTopic(Topic topic){
        topics.remove(topic);
    }

    public Topic vote(String username, Topic topic) {
        if (voters.add(username)) {
            topic.vote(username);
            topics.remove(topic);
            topics.add(topic);
        }
        return topic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    public Set<String> getVoters() {
        return voters;
    }

    public void setVoters(Set<String> voters) {
        this.voters = voters;
    }
}
