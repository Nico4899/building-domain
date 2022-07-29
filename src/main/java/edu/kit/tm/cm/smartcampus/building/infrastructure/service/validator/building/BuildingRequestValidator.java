package edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.building;

import edu.kit.tm.cm.smartcampus.building.api.controller.building.dto.ServerCreateBuildingRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.building.dto.ServerUpdateBuildingRequest;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.building.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.component.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.notification.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.room.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.Validator;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * This class is a child implementation of the {@link Validator}, it focuses on validating {@link
 * Building} requests. It calls parent methods to validate certain attributes.
 */
@Component
public class BuildingRequestValidator
    extends Validator<ServerUpdateBuildingRequest, ServerCreateBuildingRequest> {

  @Autowired
  protected BuildingRequestValidator(
      BuildingRepository buildingRepository,
      RoomRepository roomRepository,
      ComponentRepository componentRepository,
      NotificationRepository notificationRepository) {
    super(buildingRepository, roomRepository, componentRepository, notificationRepository);
  }

  @Override
  protected String getValidateRegex() {
    return BIN_PATTERN;
  }

  @Override
  public void validateCreate(ServerCreateBuildingRequest serverCreateBuildingRequest) {
    validateNotNull(Map.of(BUILDING_REQUEST, serverCreateBuildingRequest));

    validateNotNull(
        Map.of(
            BUILDING_NAME, serverCreateBuildingRequest.getName(),
            BUILDING_NUMBER, serverCreateBuildingRequest.getNumber(),
            CAMPUS_LOCATION_NAME, serverCreateBuildingRequest.getCampusLocation(),
            GEOGRAPHICAL_LOCATION_NAME, serverCreateBuildingRequest.getGeographicalLocation(),
            FLOORS_NAME, serverCreateBuildingRequest.getFloors()));

    validateNotEmpty(Map.of(BUILDING_NAME, serverCreateBuildingRequest.getName()));

    validateMatchesRegex(
        Map.of(
            BUILDING_NUMBER,
            Pair.of(serverCreateBuildingRequest.getNumber(), BUILDING_NUMBER_PATTERN)));

    validateGeographicalLocations(
        Map.of(GEOGRAPHICAL_LOCATION_NAME, serverCreateBuildingRequest.getGeographicalLocation()));

    validateFloors(Map.of(FLOORS_NAME, serverCreateBuildingRequest.getFloors()));
  }

  @Override
  public void validateUpdate(ServerUpdateBuildingRequest object) {
    validateNotNull(Map.of(BUILDING, object));

    validateNotNull(
        Map.of(
            BUILDING_NAME,
            object.getName(),
            BUILDING_NUMBER,
            object.getNumber(),
            IDENTIFICATION_NUMBER_NAME,
            object.getIdentificationNumber(),
            CAMPUS_LOCATION_NAME,
            object.getCampusLocation(),
            GEOGRAPHICAL_LOCATION_NAME,
            object.getGeographicalLocation()));

    validateNotEmpty(Map.of(BUILDING_NAME, object.getName()));

    validateMatchesRegex(
        Map.of(
            BUILDING_NUMBER,
            Pair.of(object.getNumber(), BUILDING_NUMBER_PATTERN),
            IDENTIFICATION_NUMBER_NAME,
            Pair.of(object.getIdentificationNumber(), BIN_PATTERN)));

    validateGeographicalLocations(
        Map.of(GEOGRAPHICAL_LOCATION_NAME, object.getGeographicalLocation()));

    validateFloors(Map.of(FLOORS_NAME, object.getFloors()));

    validateExists(object.getIdentificationNumber(), IDENTIFICATION_NUMBER_NAME);
  }
}
