package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.api.payload.BuildingRequest;
import edu.kit.tm.cm.smartcampus.building.api.payload.BuildingResponse;
import edu.kit.tm.cm.smartcampus.building.api.payload.BuildingsResponse;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.BuildingService;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        return this.buildingService.listBuildings();
    }

    @Override
    public BuildingResponse createBuilding(BuildingRequest buildingRequest) {
        return null;
    }

    @Override
    public BuildingResponse getBuilding(String bin) {
        return null;
    }

    @Override
    public BuildingResponse editBuilding(String bin, BuildingRequest buildingRequest) {
        return null;
    }

    @Override
    public void deleteBuilding(String bin) {

    }
}
