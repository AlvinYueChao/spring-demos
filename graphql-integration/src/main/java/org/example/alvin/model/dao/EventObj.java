package org.example.alvin.model.dao;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.example.alvin.model.graphql.Event;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;

@Data
@Builder
public class EventObj {
  @MongoId
  private ObjectId id;
  private String title;
  private String description;
  private BigDecimal price;
//  private OffsetDateTime date;
  private String date;
  private String creatorId;

  public static EventObj fromEvent(Event event) {
    return EventObj.builder()
      .title(event.getTitle())
      .description(event.getDescription())
      .price(BigDecimal.valueOf(event.getPrice()))
//      .date(OffsetDateTime.parse(event.getDate(), DateTimeFormatter.ISO_DATE_TIME))
      .date(event.getDate())
      .creatorId(event.getCreatorId())
      .build();
  }
}
