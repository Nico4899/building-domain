package edu.kit.tm.cm.smartcampus.building.api.controller.component.dto;

import edu.kit.tm.cm.smartcampus.building.api.controller.GeographicalLocationDTO;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a request for a Component.
 *
 * @author Jonathan Kramer, Johannes von Geisau
 */
@Setter
@Getter
@AllArgsConstructor
public class ServerCreateComponentRequest {

  private String parentIdentificationNumber;
  private Component.Type type;
  private String description;
  private GeographicalLocationDTO geographicalLocation;
}
