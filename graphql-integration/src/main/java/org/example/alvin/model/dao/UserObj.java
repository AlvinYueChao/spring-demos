package org.example.alvin.model.dao;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Builder
public class UserObj {
  @MongoId
  private ObjectId id;
  private String email;
  private String password;
}
