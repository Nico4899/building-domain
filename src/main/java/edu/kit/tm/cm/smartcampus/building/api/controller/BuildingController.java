package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.infrastructure.service.BuildingService;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class BuildingController implements BuildingAPI {

  private final BuildingService buildingService;

  @Autowired
  public BuildingController(BuildingService buildingService) {
    this.buildingService = buildingService;
  }

  @Override
  public Collection<Building> listBuildings() {
    return null;
  }

  @Override
  public Building createBuilding(Building building) {
    return null;
  }

  @Override
  public Building getBuilding(String bin) {
    return null;
  }

  @Override
  public Building updateBuilding(String bin, Building building) {
    return null;
  }

  @Override
  public void deleteBuilding(String bin) {}
}
