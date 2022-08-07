package edu.kit.tm.cm.smartcampus.building.api.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a data transfer object for a geographical location.
 *
 * @author Jonathan Kramer, Johannes von Geisau
 */
@Getter
@Setter
@AllArgsConstructor
public class GeographicalLocationDTO {

  private double latitude;
  private double longitude;
}
