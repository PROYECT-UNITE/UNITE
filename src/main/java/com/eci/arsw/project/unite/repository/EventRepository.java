package com.eci.arsw.project.unite.repository;

import com.eci.arsw.project.unite.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, Integer> {
}
