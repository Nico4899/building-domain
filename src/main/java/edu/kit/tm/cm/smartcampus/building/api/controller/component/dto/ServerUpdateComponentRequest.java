package edu.kit.tm.cm.smartcampus.building.api.controller.component.dto;

import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.GeographicalLocation;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ServerUpdateComponentRequest {
  private String parentIdentificationNumber;
  private Component.Type type;
  private String description;
  private GeographicalLocation geographicalLocation;
  private String identificationNumber;
}
