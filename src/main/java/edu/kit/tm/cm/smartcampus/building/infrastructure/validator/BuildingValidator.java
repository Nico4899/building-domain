package edu.kit.tm.cm.smartcampus.building.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.utils;
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
    return utils.BIN_PATTERN;
  }

  @Override
  public void validateCreate(Building object) {
    validateBase(object);
  }

  @Override
  public void validateUpdate(Building object) {
    validateBase(object);
    validateExists(object.getBin(), "identification_number");
  }

  private void validateBase(Building object) {
    validateNotNull(
        Map.of(
            "building", object,
            "building name", object.getBuildingName(),
            "building number", object.getBuildingNumber(),
            "building identification number", object.getBin(),
            "building floors", object.getBuildingFloors(),
            "building campus location", object.getCampusLocation(),
            "building geographical location", object.getGeographicalLocation()));

    validateNotEmpty(Map.of("building name", object.getBuildingName()));

    validateMatchesRegex(
        Map.of(
            "building number", Pair.of(object.getBuildingNumber(), "TODO building number regex"),
            "building identification number", Pair.of(object.getBin(), "TODO bin regex")));

    validateGeographicalLocation(
        Map.of("building geographical location", object.getGeographicalLocation()));
  }
}
