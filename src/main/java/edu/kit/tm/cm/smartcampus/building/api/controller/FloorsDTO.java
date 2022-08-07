package edu.kit.tm.cm.smartcampus.building.api.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a data transfer object for the floors class.
 */
@Getter
@Setter
@AllArgsConstructor
public class FloorsDTO {

  private int highestFloor;
  private int lowestFloor;
}
