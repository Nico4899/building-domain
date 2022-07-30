package edu.kit.tm.cm.smartcampus.building.api.responses;

import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Floors;
import edu.kit.tm.cm.smartcampus.building.logic.model.GeographicalLocation;

public class BuildingResponse {

  private String identificationNumber;

  private Building.CampusLocation campusLocation;

  private String name;

  private String number;

  private GeographicalLocation geographicalLocation;

  private Floors floors;

}
