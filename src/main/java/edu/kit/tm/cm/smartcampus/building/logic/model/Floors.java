package edu.kit.tm.cm.smartcampus.building.logic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * This class represents floors of a building.
 * The ground-floor hast the value 0 by convention.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "floors")
public class Floors {

  @Id
  private Long id;

  @Column(name = "highest_floor")
  private int highestFloor;

  @Column(name = "lowest_floor")
  private int lowestFloor;

}
