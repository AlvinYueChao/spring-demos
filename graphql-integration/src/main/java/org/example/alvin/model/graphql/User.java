package org.example.alvin.model.graphql;

import lombok.Builder;
import lombok.Data;
import org.example.alvin.model.dao.UserObj;

import java.util.List;

@Data
@Builder
public class User {
  private String id;
  private String email;
  private String password;
  private List<Event> createdEvents;

  public static User fromUserObj(UserObj userObj) {
    return User.builder()
      .id(userObj.getId().toString())
      .email(userObj.getEmail())
      .password(userObj.getPassword())
      .build();
  }
}
