package edu.kit.tm.cm.smartcampus.building.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.building.GlobalBuildingStringCollection;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BuildingValidator extends Validator<Building> {

  @Autowired
  protected BuildingValidator(
      BuildingRepository objectRepository,
      RoomRepository roomRepository,
      ComponentRepository componentRepository,
      NotificationRepository notificationRepository) {
    super(objectRepository, roomRepository, componentRepository, notificationRepository);
  }

  @Override
  protected String getValidateRegex() {
    return GlobalBuildingStringCollection.BIN_PATTERN;
  }

  @Override
  public void validateCreate(Building object) {
    validateBase(object);
  }

  @Override
  public void validateUpdate(Building object) {
    validateBase(object);
    validateExists(
        object.getIdentificationNumber(),
        GlobalBuildingStringCollection.IDENTIFICATION_NUMBER_NAME);
  }

  private void validateBase(Building object) {
    validateNotNull(Map.of(GlobalBuildingStringCollection.BUILDING, object));

    validateNotNull(
        Map.of(
            GlobalBuildingStringCollection.BUILDING_NAME,
            object.getBuildingName(),
            GlobalBuildingStringCollection.BUILDING_NUMBER,
            object.getBuildingNumber(),
            GlobalBuildingStringCollection.IDENTIFICATION_NUMBER_NAME,
            object.getIdentificationNumber(),
            GlobalBuildingStringCollection.FLOORS_NAME,
            object.getBuildingFloors(),
            GlobalBuildingStringCollection.CAMPUS_LOCATION_NAME,
            object.getCampusLocation(),
            GlobalBuildingStringCollection.GEOGRAPHICAL_LOCATION_NAME,
            object.getGeographicalLocation()));

    validateNotEmpty(
        Map.of(GlobalBuildingStringCollection.BUILDING_NAME, object.getBuildingName()));

    validateMatchesRegex(
        Map.of(
            GlobalBuildingStringCollection.BUILDING_NUMBER,
                Pair.of(object.getBuildingNumber(), GlobalBuildingStringCollection.BIN_PATTERN),
            GlobalBuildingStringCollection.IDENTIFICATION_NUMBER_NAME,
                Pair.of(
                    object.getIdentificationNumber(), GlobalBuildingStringCollection.BIN_PATTERN)));

    validateGeographicalLocation(
        Map.of(
            GlobalBuildingStringCollection.GEOGRAPHICAL_LOCATION_NAME,
            object.getGeographicalLocation()));
  }
}
