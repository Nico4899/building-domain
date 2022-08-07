package edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.building;

import edu.kit.tm.cm.smartcampus.building.api.controller.building.dto.ServerCreateBuildingRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.building.dto.ServerUpdateBuildingRequest;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.building.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.component.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.notification.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.room.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.Validator;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class is a child implementation of the {@link Validator}, it focuses on validating
 * {@link Building} requests. It calls parent methods to validate certain attributes.
 *
 * @author Jonathan Kramer, Johannes von Geisau
 */
@Component
public class BuildingValidator
    extends Validator<ServerUpdateBuildingRequest, ServerCreateBuildingRequest> {

  @Autowired
  protected BuildingValidator(
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
    validateNotNull(List.of(Pair.of(BUILDING_REQUEST, serverCreateBuildingRequest)));

    validateNotNull(List.of(
        Pair.of(BUILDING_NAME, serverCreateBuildingRequest.getName()),
        Pair.of(BUILDING_NUMBER, serverCreateBuildingRequest.getNumber()),
        Pair.of(CAMPUS_LOCATION_NAME, serverCreateBuildingRequest.getCampusLocation()),
        Pair.of(GEOGRAPHICAL_LOCATION_NAME, serverCreateBuildingRequest.getGeographicalLocation()),
        Pair.of(FLOORS_NAME, serverCreateBuildingRequest.getFloors())));

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
    validateNotNull(List.of(Pair.of(BUILDING, object)));

    validateNotNull(List.of(
        Pair.of(BUILDING_NAME, object.getName()),
        Pair.of(BUILDING_NUMBER, object.getNumber()),
        Pair.of(IDENTIFICATION_NUMBER_NAME, object.getIdentificationNumber()),
        Pair.of(CAMPUS_LOCATION_NAME, object.getCampusLocation()),
        Pair.of(GEOGRAPHICAL_LOCATION_NAME, object.getGeographicalLocation()),
        Pair.of(FLOORS_NAME, object.getFloors())));

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
