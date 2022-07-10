package edu.kit.tm.cm.smartcampus.building.logic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * This class represents a geographical location, by longitude and latitude coordinates.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "geographical_location")
public class GeographicalLocation {

  @Id
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "latitude", nullable = false, updatable = false)
  private double latitude;

  @Column(name = "longitude", nullable = false, updatable = false)
  private double longitude;
}
