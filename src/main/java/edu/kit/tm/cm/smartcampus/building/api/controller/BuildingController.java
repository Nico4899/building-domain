package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.api.BuildingApi;
import edu.kit.tm.cm.smartcampus.building.api.payload.BuildingRequest;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;

import java.util.Collection;


public class BuildingController implements BuildingApi {
    @Override
    public Collection<Building> listBuildings() {
        return null;
    }

    @Override
    public Building createBuilding(final BuildingRequest buildingRequest) {
        return null;
    }

    @Override
    public Building getBuilding(final String bin) {
        return null;
    }

    @Override
    public Building editBuilding(final String bin, final BuildingRequest buildingRequest) {
        return null;
    }

    @Override
    public void deleteBuilding(final String bin) {

    }


}
