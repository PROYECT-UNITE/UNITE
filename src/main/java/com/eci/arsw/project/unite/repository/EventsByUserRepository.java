package com.eci.arsw.project.unite.repository;

import com.eci.arsw.project.unite.beans.impl.EventsByUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EventsByUserRepository extends MongoRepository<EventsByUser, String> {
    @Query("{'events': ?0}")
    List<EventsByUser> findByEvents(int eventId);
}
