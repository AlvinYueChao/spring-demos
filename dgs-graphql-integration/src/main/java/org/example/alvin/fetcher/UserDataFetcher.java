package org.example.alvin.fetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.extern.slf4j.Slf4j;
import org.example.alvin.model.dao.UserObj;
import org.example.alvin.model.graphql.User;
import org.example.alvin.model.graphql.UserInput;
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

  @Autowired
  public UserDataFetcher(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
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
}
