package edu.kit.tm.cm.smartcampus.building.api.requests;

import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a request for a Building
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildingRequest {

  private String name;

  private String number;

  private Building.CampusLocation campusLocation;

  private GeographicalLocation geographicalLocation;

  private Floors floors;


}
