package edu.kit.tm.cm.smartcampus.building.logic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * This class represents a geographical location, by longitude and latitude coordinates.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "geographical_location")
public class GeographicalLocation {

  @Id
  private Long id;
  private double latitude;
  private double longitude;
}
