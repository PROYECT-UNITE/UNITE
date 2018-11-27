package com.eci.arsw.project.unite.repository;

import com.eci.arsw.project.unite.beans.impl.EventsInvitedByUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventsInvitedByUserRepository extends MongoRepository<EventsInvitedByUser, String> {
}
