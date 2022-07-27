package edu.kit.tm.cm.smartcampus.building.api.requests;

import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Floors;
import edu.kit.tm.cm.smartcampus.building.logic.model.GeographicalLocation;
import lombok.*;

/**
 * This class represents a request for a Building.
 */
@Data
@Setter
@Getter
@AllArgsConstructor
public class BuildingRequest {

  private String name;

  private String number;

  private Building.CampusLocation campusLocation;

  private GeographicalLocation geographicalLocation;

  private Floors floors;


}
