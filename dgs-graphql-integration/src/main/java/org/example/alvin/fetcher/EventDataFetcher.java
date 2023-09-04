package org.example.alvin.fetcher;

import com.netflix.graphql.dgs.*;
import lombok.extern.slf4j.Slf4j;
import org.example.alvin.model.dao.EventObj;
import org.example.alvin.model.dao.UserObj;
import org.example.alvin.model.graphql.Event;
import org.example.alvin.model.graphql.EventInput;
import org.example.alvin.model.graphql.User;
import org.example.alvin.repository.EventRepository;
import org.example.alvin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@DgsComponent
public class EventDataFetcher {

  private final EventRepository eventRepository;
  private final UserRepository userRepository;

  @Autowired
  public EventDataFetcher(EventRepository eventRepository, UserRepository userRepository) {
    this.eventRepository = eventRepository;
    this.userRepository = userRepository;
  }

  @DgsQuery
  public List<Event> events() {
    List<EventObj> eventObjs = eventRepository.findAll();
    return eventObjs.stream().map(Event::fromEventObj).collect(Collectors.toList());
  }

  @DgsMutation
  public Event createEvent(@InputArgument EventInput eventInput) {
    EventObj eventObj = EventObj.builder()
      .title(eventInput.getTitle())
      .description(eventInput.getDescription())
      .price(BigDecimal.valueOf(eventInput.getPrice()))
//      .date(OffsetDateTime.parse(eventInput.getDate(), DateTimeFormatter.ISO_DATE_TIME))
      .date(eventInput.getDate())
      .creatorId(eventInput.getCreatorId())
      .build();
    EventObj insertedEventObj = eventRepository.insert(eventObj);
    Event createdEvent = Event.fromEventObj(insertedEventObj);
    log.info("event created successfully, eventId: {}, creatorId: {}, date: {}", createdEvent.getId(), createdEvent.getCreatorId(), createdEvent.getDate());
    return createdEvent;
  }

  @DgsData(parentType = "Event", field = "creator")
  public User creator(DgsDataFetchingEnvironment dfe) {
    Event event = dfe.getSource();
    Optional<UserObj> userObjOpt = userRepository.findById(event.getCreatorId());
    return userObjOpt.isPresent() ? User.fromUserObj(userObjOpt.get()) : User.builder().build();
  }
}
