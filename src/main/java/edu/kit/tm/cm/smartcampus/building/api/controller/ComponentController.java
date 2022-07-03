package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.api.ComponentApi;
import edu.kit.tm.cm.smartcampus.building.api.payload.ComponentRequest;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;

import java.util.Collection;

public class ComponentController implements ComponentApi {
    @Override
    public Collection<Component> getBuildingComponents(String bin) {
        return null;
    }

    @Override
    public Building createBuildingComponent(String bin, ComponentRequest componentRequest) {
        return null;
    }

    @Override
    public Collection<Component> getRoomComponents(String bin, String rin) {
        return null;
    }

    @Override
    public Building createRoomComponent(String bin, String rin, ComponentRequest componentRequest) {
        return null;
    }

    @Override
    public Component editBuildingComponent(String bin, String cin, ComponentRequest componentRequest) {
        return null;
    }

    @Override
    public void deleteBuildingComponent(String bin, String cin) {

    }

    @Override
    public Component editRoomComponent(String bin, String rin, String cin, ComponentRequest componentRequest) {
        return null;
    }

    @Override
    public void deleteRoomComponent(String bin, String rin, String cin) {

    }
}
