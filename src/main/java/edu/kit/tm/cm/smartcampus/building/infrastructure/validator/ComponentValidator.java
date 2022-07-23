package edu.kit.tm.cm.smartcampus.building.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.building.GlobalBuildingStringCollection;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;

import java.util.Map;

@org.springframework.stereotype.Component
public class ComponentValidator extends Validator<Component> {

  @Autowired
  protected ComponentValidator(
      BuildingRepository buildingRepository,
      RoomRepository roomRepository,
      ComponentRepository componentRepository,
      NotificationRepository notificationRepository) {
    super(buildingRepository, roomRepository, componentRepository, notificationRepository);
  }

  @Override
  protected String getValidateRegex() {
    return GlobalBuildingStringCollection.CIN_PATTERN;
  }

  @Override
  public void validateCreate(Component object) {
    validateBase(object);
  }

  @Override
  public void validateUpdate(Component object) {
    validateBase(object);
    validateExists(
        object.getIdentificationNumber(),
        GlobalBuildingStringCollection.IDENTIFICATION_NUMBER_NAME);
  }

  private void validateBase(Component object) {

    validateNotNull(Map.of(GlobalBuildingStringCollection.COMPONENT, object));

    validateNotNull(
        Map.of(
            GlobalBuildingStringCollection.COMPONENT_DESCRIPTION_NAME,
            object.getDescription(),
            GlobalBuildingStringCollection.IDENTIFICATION_NUMBER_NAME,
            object.getIdentificationNumber(),
            GlobalBuildingStringCollection.PARENT_IDENTIFICATION_NUMBER_NAME,
            object.getParentIdentificationNumber(),
            GlobalBuildingStringCollection.COMPONENT_TYPE_NAME,
            object.getType()));

    validateNotEmpty(
        Map.of(GlobalBuildingStringCollection.COMPONENT_DESCRIPTION_NAME, object.getDescription()));

    validateMatchesRegex(
        Map.of(
            GlobalBuildingStringCollection.IDENTIFICATION_NUMBER_NAME,
                Pair.of(
                    object.getIdentificationNumber(), GlobalBuildingStringCollection.CIN_PATTERN),
            GlobalBuildingStringCollection.PARENT_IDENTIFICATION_NUMBER_NAME,
                Pair.of(
                    object.getParentIdentificationNumber(),
                    GlobalBuildingStringCollection.BIN_RIN_PATTERN)));

    validateCoordinates(
        Map.of(
            GlobalBuildingStringCollection.COORDINATES_NAME,
            Pair.of(object.getLatitude(), object.getLongitude())));
    validateExists(
        object.getParentIdentificationNumber(),
        GlobalBuildingStringCollection.PARENT_IDENTIFICATION_NUMBER_NAME);
  }
}
