package edu.kit.tm.cm.smartcampus.building.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.building.api.requests.ComponentRequest;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;

import java.util.Map;

/**
 * This class is a child implementation of the {@link Validator}, it focuses on validating {@link
 * Component} requests. It calls parent methods to validate certain attributes.
 */
@org.springframework.stereotype.Component
public class ComponentValidator extends Validator<Component, ComponentRequest> {

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
    return CIN_PATTERN;
  }

  @Override
  public void validateCreate(ComponentRequest componentRequest) {
    validateBase(componentRequest);
  }

  @Override
  public void validateUpdate(Component object) {
    validateBase(object);
    validateExists(object.getIdentificationNumber(), IDENTIFICATION_NUMBER_NAME);
  }

  private void validateBase(Component object) {

    validateNotNull(Map.of(COMPONENT, object));

    validateNotNull(
        Map.of(
            COMPONENT_DESCRIPTION_NAME,
            object.getDescription(),
            IDENTIFICATION_NUMBER_NAME,
            object.getIdentificationNumber(),
            PARENT_IDENTIFICATION_NUMBER_NAME,
            object.getParentIdentificationNumber(),
            COMPONENT_TYPE_NAME,
            object.getType()));

    validateNotEmpty(Map.of(COMPONENT_DESCRIPTION_NAME, object.getDescription()));

    validateMatchesRegex(
        Map.of(
            IDENTIFICATION_NUMBER_NAME, Pair.of(object.getIdentificationNumber(), CIN_PATTERN),
            PARENT_IDENTIFICATION_NUMBER_NAME,
                Pair.of(object.getParentIdentificationNumber(), BIN_RIN_PATTERN)));

    validateGeographicalLocations(Map.of(GEOGRAPHICAL_LOCATION_NAME, object.getGeographicalLocation()));

    validateExists(object.getParentIdentificationNumber(), PARENT_IDENTIFICATION_NUMBER_NAME);
  }

  private void validateBase(ComponentRequest componentRequest) {

    validateNotNull(Map.of(COMPONENT_REQUEST, componentRequest));

    validateNotNull(
        Map.of(
            COMPONENT_DESCRIPTION_NAME, componentRequest.getDescription(),
            PARENT_IDENTIFICATION_NUMBER_NAME, componentRequest.getParentIdentificationNumber(),
            COMPONENT_TYPE_NAME, componentRequest.getType(),
            GEOGRAPHICAL_LOCATION_NAME, componentRequest.getGeographicalLocation()));

    validateNotEmpty(Map.of(COMPONENT_DESCRIPTION_NAME, componentRequest.getDescription()));

    validateMatchesRegex(
        Map.of(
            PARENT_IDENTIFICATION_NUMBER_NAME,
            Pair.of(componentRequest.getParentIdentificationNumber(), BIN_RIN_PATTERN)));

    validateGeographicalLocations(Map.of(GEOGRAPHICAL_LOCATION_NAME, componentRequest.getGeographicalLocation()));

    validateExists(componentRequest.getParentIdentificationNumber(), PARENT_IDENTIFICATION_NUMBER_NAME);
  }
}
