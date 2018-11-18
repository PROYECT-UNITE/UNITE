package com.eci.arsw.project.unite.repository;

import com.eci.arsw.project.unite.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EventRepository extends MongoRepository<Event, Integer> {

//    @Query(Criteria.where("_id").is())
//    void chancheNameEvent(String name);

}
