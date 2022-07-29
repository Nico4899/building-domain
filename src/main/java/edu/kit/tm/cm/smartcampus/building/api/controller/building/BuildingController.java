package edu.kit.tm.cm.smartcampus.building.api.controller.building;

import edu.kit.tm.cm.smartcampus.building.api.configuration.error.ServerExceptionInterceptor;
import edu.kit.tm.cm.smartcampus.building.api.controller.building.dto.ServerCreateBuildingRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.building.dto.ServerUpdateBuildingRequest;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.Service;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.operations.utility.DataTransferUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * This class represents the building controller for this domain service. It holds a Spring {@link
 * Bean} of {@link Service} managing all logical operations and running domain constraint
 * validators. It sends REST-Server responses in JSON format via the Spring internal {@link
 * RestController} annotation. In case of errors the {@link ServerExceptionInterceptor} returns
 * given information as REST error response.
 */
@RestController
public class BuildingController implements BuildingOperations {

  private final Service service;

  /**
   * Instantiates a new building controller for this domain service, it implements all {@link
   * BuildingOperations}.
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
  public Building createBuilding(ServerCreateBuildingRequest serverCreateBuildingRequest) {
    return service.createBuilding(serverCreateBuildingRequest);
  }

  @Override
  public Building getBuilding(String bin) {
    return service.getBuilding(bin);
  }

  @Override
  public Building updateBuilding(ServerUpdateBuildingRequest serverUpdateBuildingRequest) {
    return service.updateBuilding(serverUpdateBuildingRequest);
  }

  @Override
  public void removeBuilding(String bin) {
    service.removeBuilding(bin);
  }
}
