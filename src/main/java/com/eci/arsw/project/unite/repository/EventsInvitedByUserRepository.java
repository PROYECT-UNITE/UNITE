package com.eci.arsw.project.unite.repository;

import com.eci.arsw.project.unite.beans.impl.EventsInvitedByUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EventsInvitedByUserRepository extends MongoRepository<EventsInvitedByUser, String> {
    @Query("{'events': ?0}")
    List<EventsInvitedByUser> findByEvents(int eventId);
}
