package com.eci.arsw.project.unite.repository;

import com.eci.arsw.project.unite.beans.impl.EventsByUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface EventsByUserRepository extends MongoRepository<EventsByUser, String> {

    @Query("{'events._id': ?0}")
    EventsByUser findByEventsContainsId(int id);
}
