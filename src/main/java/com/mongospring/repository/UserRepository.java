package com.mongospring.repository;

import com.mongospring.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {

    User findByUsername(String username);
    List<User> findAll();
    long count();
}
