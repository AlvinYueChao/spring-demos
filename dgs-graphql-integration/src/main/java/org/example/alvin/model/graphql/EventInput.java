package org.example.alvin.model.graphql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventInput {
  private String title;
  private String description;
  private Double price;
  private String date;
  private String creatorId;
}
