package org.example.alvin.model.graphql;

import lombok.Builder;
import lombok.Data;
import org.example.alvin.model.dao.EventObj;

@Data
@Builder
public class Event {
  private String id;
  private String title;
  private String description;
  private Double price;
  private String date;
  private String creatorId;
  private User creator;

  public static Event fromEventObj(EventObj eventObj) {
    return Event.builder()
      .id(eventObj.getId().toString())
      .title(eventObj.getTitle())
      .description(eventObj.getDescription())
      .price(eventObj.getPrice().doubleValue())
//      .date(eventDoc.getDate().format(DateTimeFormatter.ISO_DATE_TIME))
      .date(eventObj.getDate())
      .creatorId(eventObj.getCreatorId().toString())
      .build();
  }
}
