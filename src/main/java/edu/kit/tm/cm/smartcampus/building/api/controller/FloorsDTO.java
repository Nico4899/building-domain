package edu.kit.tm.cm.smartcampus.building.api.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FloorsDTO {
  private int highestFloor;
  private int lowestFloor;
}
