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
        return buildingService.listBuildings();
    }

    @Override
    public Building createBuilding(Building building) {
        return buildingService.createBuilding(building);
    }

    @Override
    public Building getBuilding(String bin) {
        return null;
        //return buildingService.getBuilding(bin); TODO
    }

    @Override
    public Building updateBuilding(String bin, Building building) {
        return buildingService.updateBuilding(bin, building);
    }

    @Override
    public void deleteBuilding(String bin) {
        buildingService.deleteBuilding(bin);
    }
}
