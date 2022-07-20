package edu.kit.tm.cm.smartcampus.building.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.GlobalStringCollection;
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
    return GlobalStringCollection.CIN_PATTERN;
  }

  @Override
  public void validateCreate(Component object) {
    validateBase(object);
  }

  @Override
  public void validateUpdate(Component object) {
    validateBase(object);
    validateExists(object.getCin(), "identification_number");
  }

  private void validateBase(Component object) {
    validateNotNull(
        Map.of(
            "component ", object,
            "component description", object.getComponentDescription(),
            "component identification number", object.getCin(),
            "component parent identification number", object.getParentIn(),
            "component type", object.getComponentType(),
            "component geographical location", object.getGeographicalLocation()));

    validateNotEmpty(Map.of("component description", object.getComponentDescription()));

    validateMatchesRegex(
        Map.of(
            "component identification number", Pair.of(object.getCin(), "TODO cin regex"),
            "component parent identification number",
                Pair.of(object.getParentIn(), "TODO bin/rin regex")));

    validateGeographicalLocation(
        Map.of("component geographical location", object.getGeographicalLocation()));
    validateExists(object.getParentIn(), "parent_identification_number");

  }
}
