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
    validateExists(object.getCin(), GlobalBuildingStringCollection.IDENTIFICATION_NUMBER_NAME);
  }

  private void validateBase(Component object) {

    validateNotNull(Map.of(GlobalBuildingStringCollection.COMPONENT, object));

    validateNotNull(
        Map.of(
            GlobalBuildingStringCollection.COMPONENT_DESCRIPTION_NAME,
            object.getComponentDescription(),
            GlobalBuildingStringCollection.IDENTIFICATION_NUMBER_NAME,
            object.getCin(),
            GlobalBuildingStringCollection.PARENT_IDENTIFICATION_NUMBER_NAME,
            object.getParentIn(),
            GlobalBuildingStringCollection.COMPONENT_TYPE_NAME,
            object.getComponentType(),
            GlobalBuildingStringCollection.GEOGRAPHICAL_LOCATION_NAME,
            object.getGeographicalLocation()));

    validateNotEmpty(
        Map.of(
            GlobalBuildingStringCollection.COMPONENT_DESCRIPTION_NAME, object.getComponentDescription()));

    validateMatchesRegex(
        Map.of(
            GlobalBuildingStringCollection.IDENTIFICATION_NUMBER_NAME,
                Pair.of(object.getCin(), GlobalBuildingStringCollection.CIN_PATTERN),
            GlobalBuildingStringCollection.PARENT_IDENTIFICATION_NUMBER_NAME,
                Pair.of(object.getParentIn(), GlobalBuildingStringCollection.BIN_RIN_PATTERN)));

    validateGeographicalLocation(
        Map.of(
            GlobalBuildingStringCollection.GEOGRAPHICAL_LOCATION_NAME, object.getGeographicalLocation()));
    validateExists(object.getParentIn(), GlobalBuildingStringCollection.PARENT_IDENTIFICATION_NUMBER_NAME);
  }
}
