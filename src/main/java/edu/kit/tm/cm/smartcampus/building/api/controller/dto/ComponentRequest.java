package edu.kit.tm.cm.smartcampus.building.api.controller.dto;

import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.GeographicalLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** This class represents a request for a Component. */
@Setter
@Getter
@AllArgsConstructor
public class ComponentRequest {

  private String parentIdentificationNumber;

  private Component.Type type;

  private String description;

  private GeographicalLocation geographicalLocation;
}
