package com.cbyk.blogg.repo;

import com.cbyk.blogg.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository  extends MongoRepository<User, Long> {

    User findByUsername(String username);
}