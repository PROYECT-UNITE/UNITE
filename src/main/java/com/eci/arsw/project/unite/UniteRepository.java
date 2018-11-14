/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eci.arsw.project.unite;

/**
 *
 * @author 2135494
 */
import com.eci.arsw.project.unite.model.Event;
import com.eci.arsw.project.unite.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UniteRepository extends MongoRepository<Event, String> {

    public Event findById(int id);
    
//    public User existeByUsername(String username);

}
