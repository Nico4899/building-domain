package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.api.BuildingApi;
import edu.kit.tm.cm.smartcampus.building.api.payload.BuildingRequest;
import edu.kit.tm.cm.smartcampus.building.api.payload.BuildingResponse;
import edu.kit.tm.cm.smartcampus.building.api.payload.BuildingsResponse;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.operations.BuildingOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class BuildingController implements BuildingApi {

    private final BuildingOperations buildingOperations;

    @Autowired
    public BuildingController(BuildingOperations buildingOperations) {
        this.buildingOperations = buildingOperations;
    }

    @Override
    public BuildingsResponse listBuildings() {
        return null; //TODO implement methodes with exceptions
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
