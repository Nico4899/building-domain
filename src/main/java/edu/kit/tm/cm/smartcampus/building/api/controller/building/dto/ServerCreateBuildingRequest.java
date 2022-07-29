package edu.kit.tm.cm.smartcampus.building.api.controller.building.dto;

import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Floors;
import edu.kit.tm.cm.smartcampus.building.logic.model.GeographicalLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** This class represents a request for a Building. */
@Setter
@Getter
@AllArgsConstructor
public class ServerCreateBuildingRequest {
  private String name;
  private String number;
  private Building.CampusLocation campusLocation;
  private GeographicalLocation geographicalLocation;
  private Floors floors;
}
