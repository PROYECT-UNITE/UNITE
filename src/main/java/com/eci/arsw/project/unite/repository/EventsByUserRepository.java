package com.eci.arsw.project.unite.repository;

import com.eci.arsw.project.unite.beans.impl.EventsByUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventsByUserRepository extends MongoRepository<EventsByUser, String> {

}
