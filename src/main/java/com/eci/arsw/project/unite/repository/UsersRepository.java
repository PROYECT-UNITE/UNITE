package com.eci.arsw.project.unite.repository;

import com.eci.arsw.project.unite.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<User, String> {
    User findByMail(String mail);
}
