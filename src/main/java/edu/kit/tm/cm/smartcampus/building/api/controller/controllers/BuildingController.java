package edu.kit.tm.cm.smartcampus.building.api.controller.controllers;

import edu.kit.tm.cm.smartcampus.building.api.controller.error.ServerExceptionInterceptor;
import edu.kit.tm.cm.smartcampus.building.api.controller.operations.BuildingOperations;
import edu.kit.tm.cm.smartcampus.building.api.controller.dto.BuildingRequest;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.Service;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class represents the building controller for this domain service. It holds a Spring
 * {@link Bean} of {@link Service} managing all logical operations and running domain constraint
 * validators. It sends REST-Server responses in JSON format via the Spring internal
 * {@link RestController} annotation. In case of errors the {@link ServerExceptionInterceptor}
 * returns given information as REST error response.
 */
@RestController
public class BuildingController implements BuildingOperations {

  private final Service service;

  /**
   * Instantiates a new building controller for this domain service, it implements all
   * {@link BuildingOperations}.
   *
   * @param service the service which controls all domain logic (constructor injected)
   */
  @Autowired
  public BuildingController(Service service) {
    this.service = service;
  }

  @Override
  public Collection<Building> listBuildings() {
    return service.listBuildings();
  }

  @Override
  public Building createBuilding(BuildingRequest buildingRequest) {
    return service.createBuilding(buildingRequest);
  }

  @Override
  public Building getBuilding(String bin) {
    return service.getBuilding(bin);
  }

  @Override
  public Building updateBuilding(Building building) {
    return service.updateBuilding(building);
  }

  @Override
  public void removeBuilding(String bin) {
    service.removeBuilding(bin);
  }

  @Override
  public Collection<Room> listBuildingRooms(String bin) {
    return service.listBuildingRooms(bin);
  }

  @Override
  public Collection<Component> listBuildingComponents(String bin) {
    return service.listBuildingComponents(bin);
  }

  @Override
  public Collection<Notification> listBuildingNotifications(String bin) {
    return service.listBuildingNotifications(bin);
  }

}
