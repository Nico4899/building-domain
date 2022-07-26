package edu.kit.tm.cm.smartcampus.building.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.building.api.requests.BuildingRequest;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.RoomRepository;
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
public class BuildingValidator extends Validator<Building, BuildingRequest> {

  @Autowired
  protected BuildingValidator(BuildingRepository buildingRepository,
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
  public void validateCreate(BuildingRequest buildingRequest) {
    validateBase(buildingRequest);
  }

  @Override
  public void validateUpdate(Building object) {
    validateBase(object);
    validateExists(object.getIdentificationNumber(), IDENTIFICATION_NUMBER_NAME);
  }

  private void validateBase(Building object) { //TODO kann raus?
    validateNotNull(Map.of(BUILDING, object));

    validateNotNull(Map.of(BUILDING_NAME, object.getName(), BUILDING_NUMBER, object.getNumber(),
            IDENTIFICATION_NUMBER_NAME, object.getIdentificationNumber(), CAMPUS_LOCATION_NAME,
            object.getCampusLocation(), GEOGRAPHICAL_LOCATION_NAME, object.getGeographicalLocation()));

    validateNotEmpty(Map.of(BUILDING_NAME, object.getName()));

    validateMatchesRegex(Map.of(BUILDING_NUMBER, Pair.of(object.getNumber(), BIN_PATTERN),
            IDENTIFICATION_NUMBER_NAME, Pair.of(object.getIdentificationNumber(), BIN_PATTERN)));

    validateCoordinates(Map.of(COORDINATES_NAME, Pair.of(object.getGeographicalLocation().getLatitude(),
            object.getGeographicalLocation().getLongitude())));

    validateFloorValues(Map.of(FLOORS_NAME, Pair.of(object.getFloors().getLowestFloor(),
            object.getFloors().getHighestFloor())));
  }

  private void validateBase(BuildingRequest buildingRequest) {
    validateNotNull(Map.of(BUILDING_REQUEST, buildingRequest));

    validateNotNull(Map.of(
        BUILDING_NAME, buildingRequest.getName(),
        BUILDING_NUMBER, buildingRequest.getNumber(),
        CAMPUS_LOCATION_NAME, buildingRequest.getCampusLocation(),
        GEOGRAPHICAL_LOCATION_NAME, buildingRequest.getGeographicalLocation(),
        FLOORS_NAME, buildingRequest.getFloors())
    );

    validateNotEmpty(Map.of(BUILDING_NAME, buildingRequest.getName()));

    validateMatchesRegex(Map.of(BUILDING_NUMBER, Pair.of(buildingRequest.getNumber(), BIN_PATTERN)));

    validateCoordinates(Map.of(COORDINATES_NAME, Pair.of(buildingRequest.getGeographicalLocation().getLatitude(),
        buildingRequest.getGeographicalLocation().getLongitude())));

    validateFloorValues(Map.of(FLOORS_NAME, Pair.of(buildingRequest.getFloors().getLowestFloor(),
        buildingRequest.getFloors().getHighestFloor())));
  }

}
