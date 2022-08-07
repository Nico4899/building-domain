package edu.kit.tm.cm.smartcampus.building.api.controller.component.dto;

import edu.kit.tm.cm.smartcampus.building.api.controller.GeographicalLocationDTO;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a server update request for a component.
 *
 * @author Jonathan Kramer, Johannes von Geisau
 */
@Setter
@Getter
@AllArgsConstructor
public class ServerUpdateComponentRequest {

  private String parentIdentificationNumber;
  private Component.Type type;
  private String description;
  private GeographicalLocationDTO geographicalLocation;
  private String identificationNumber;
}
