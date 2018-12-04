package com.eci.arsw.project.unite.model;

import java.util.Objects;
import java.util.Set;

public class Topic {

    private String name;
    private String description;
    private Set<String> voters;

    public Topic() {
    }

    public int votes() {
        return voters.size();
    }

    public void vote(String username) {
        voters.add(username);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getVoters() {
        return voters;
    }

    public void setVoters(Set<String> voters) {
        this.voters = voters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return Objects.equals(name, topic.name) &&
                Objects.equals(description, topic.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
