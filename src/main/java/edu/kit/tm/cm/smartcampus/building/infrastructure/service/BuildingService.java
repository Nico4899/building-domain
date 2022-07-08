package edu.kit.tm.cm.smartcampus.building.infrastructure.service;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class BuildingService {

  private final BuildingRepository buildingRepository;

  @Autowired
  public BuildingService(BuildingRepository buildingRepository) {
    this.buildingRepository = buildingRepository;
  }

  public Collection<Building> listBuildings() {
    Collection<Building> buildings = new ArrayList<>();
    for (Building building : buildingRepository.findAll()) buildings.add(building);
    return buildings;
  }

  public Building getBuilding(String identificationNumber) throws Exception {
    if (buildingRepository.findById(identificationNumber).isPresent()) {
      return buildingRepository.findById(identificationNumber).get();
    }
    throw new Exception(); // TODO not found exception?
  }

  public Building createBuilding(Building building) {
    return this.buildingRepository.save(building);
  }

  public void deleteBuilding(String identificationNumber) {
    buildingRepository.deleteById(identificationNumber);
  }

  public Building updateBuilding(String identificationNumber, Building building) {
    building.setId(identificationNumber);
    return this.buildingRepository.save(building);
  }
}
