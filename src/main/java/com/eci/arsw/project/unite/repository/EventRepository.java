package com.eci.arsw.project.unite.repository;

/**
 *
 * @author 2135494
 */
import com.eci.arsw.project.unite.model.Event;
import com.eci.arsw.project.unite.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {

    public Event findById(int id);

}
