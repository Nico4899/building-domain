package edu.kit.tm.cm.smartcampus.building.infrastructure.service;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions.NotFoundException;
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

  public Building getBuilding(String bin) throws NotFoundException {
    if (buildingRepository.findById(bin).isPresent()) {
      return buildingRepository.findById(bin).get();
    }
    throw new NotFoundException();
  }

  public Building createBuilding(Building building) {
    return this.buildingRepository.save(building);
  }

  public void deleteBuilding(String bin) {
    buildingRepository.deleteById(bin);
  }

  public Building updateBuilding(String bin, Building building) {
    building.setIdentificationNumber(bin);
    return this.buildingRepository.save(building);
  }
}
