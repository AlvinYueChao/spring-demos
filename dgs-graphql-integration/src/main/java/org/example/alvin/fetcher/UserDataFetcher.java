package org.example.alvin.fetcher;

import com.netflix.graphql.dgs.*;
import lombok.extern.slf4j.Slf4j;
import org.example.alvin.model.dao.EventObj;
import org.example.alvin.model.dao.UserObj;
import org.example.alvin.model.graphql.Event;
import org.example.alvin.model.graphql.User;
import org.example.alvin.model.graphql.UserInput;
import org.example.alvin.repository.EventRepository;
import org.example.alvin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@DgsComponent
public class UserDataFetcher {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final EventRepository eventRepository;

  @Autowired
  public UserDataFetcher(UserRepository userRepository, PasswordEncoder passwordEncoder, EventRepository eventRepository) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.eventRepository = eventRepository;
  }

  @DgsQuery
  public List<User> users() {
    List<UserObj> allUsers = userRepository.findAll();
    return allUsers.stream().map(User::fromUserObj).collect(Collectors.toList());
  }

  @DgsMutation
  public User createUser(@InputArgument UserInput userInput) {
    UserObj userObj = UserObj.builder()
      .email(userInput.getEmail())
      .password(passwordEncoder.encode(userInput.getPassword()))
      .build();
    UserObj createdUserObj = userRepository.insert(userObj);
    User createdUser = User.fromUserObj(createdUserObj);
    log.info("user created successfully, userId: {}, email: {}", createdUser.getId(), createdUser.getEmail());
    return createdUser;
  }

  @DgsData(parentType = "User", field = "createdEvents")
  public List<Event> createdEvents(DgsDataFetchingEnvironment dfe) {
    User user = dfe.getSource();
    List<EventObj> eventObjs = eventRepository.findByCreatorId(user.getId());
    return eventObjs.stream().map(Event::fromEventObj).collect(Collectors.toList());
  }
}
