package org.example.alvin.repository;

import org.example.alvin.model.dao.EventObj;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<EventObj, String> {

  List<EventObj> findByCreatorId(String creatorId);
}
