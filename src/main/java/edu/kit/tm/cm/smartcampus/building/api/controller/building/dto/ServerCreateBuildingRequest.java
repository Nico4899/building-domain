package edu.kit.tm.cm.smartcampus.building.api.controller.building.dto;

import edu.kit.tm.cm.smartcampus.building.api.controller.FloorsDTO;
import edu.kit.tm.cm.smartcampus.building.api.controller.GeographicalLocationDTO;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a request for a Building.
 *
 * @author Jonathan Kramer, Johannes von Geisau
 */
@Setter
@Getter
@AllArgsConstructor
public class ServerCreateBuildingRequest {
  private String name;
  private String number;
  private String address;
  private Building.CampusLocation campusLocation;
  private GeographicalLocationDTO geographicalLocation;
  private FloorsDTO floors;
}
