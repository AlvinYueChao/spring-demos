package org.example.alvin.repository;

import org.example.alvin.model.dao.UserObj;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserObj, String> {
}
