package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.api.BuildingApi;
import edu.kit.tm.cm.smartcampus.building.api.payload.BuildingRequest;
import edu.kit.tm.cm.smartcampus.building.api.payload.BuildingResponse;
import edu.kit.tm.cm.smartcampus.building.api.payload.BuildingsResponse;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;

import java.util.Collection;


public class BuildingController implements BuildingApi {

    @Override
    public BuildingsResponse listBuildings() {
        return null;
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
