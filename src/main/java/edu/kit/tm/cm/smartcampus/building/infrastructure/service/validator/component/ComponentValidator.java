package edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.component;

import edu.kit.tm.cm.smartcampus.building.api.controller.component.dto.ServerCreateComponentRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.component.dto.ServerUpdateComponentRequest;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.building.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.component.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.notification.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.room.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.Validator;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class is a child implementation of the {@link Validator}, it focuses on validating
 * {@link Component} requests. It calls parent methods to validate certain attributes.
 *
 * @author Jonathan Kramer, Johannes von Geisau
 */
@org.springframework.stereotype.Component
public class ComponentValidator extends
    Validator<ServerUpdateComponentRequest, ServerCreateComponentRequest> {

  @Autowired
  protected ComponentValidator(
      BuildingRepository buildingRepository,
      RoomRepository roomRepository,
      ComponentRepository componentRepository,
      NotificationRepository notificationRepository) {
    super(buildingRepository, roomRepository, componentRepository, notificationRepository);
  }

  @Override
  protected String getValidateIdentificationNumberRegex() {
    return CIN_PATTERN;
  }

  @Override
  protected String getValidateParentIdentificationNumberRegex() {
    return BIN_RIN_PATTERN;
  }

  @Override
  public void validateCreate(ServerCreateComponentRequest serverCreateComponentRequest) {
    validateNotNull(List.of(Pair.of(COMPONENT_REQUEST, serverCreateComponentRequest)));

    validateNotNull(List.of(
        Pair.of(COMPONENT_DESCRIPTION_NAME, serverCreateComponentRequest.getDescription()),
        Pair.of(PARENT_IDENTIFICATION_NUMBER_NAME,
            serverCreateComponentRequest.getParentIdentificationNumber()),
        Pair.of(COMPONENT_TYPE_NAME, serverCreateComponentRequest.getType()),
        Pair.of(GEOGRAPHICAL_LOCATION_NAME,
            serverCreateComponentRequest.getGeographicalLocation())));

    validateNotEmpty(
        Map.of(COMPONENT_DESCRIPTION_NAME, serverCreateComponentRequest.getDescription()));

    validateMatchesRegex(
        Map.of(
            PARENT_IDENTIFICATION_NUMBER_NAME,
            Pair.of(serverCreateComponentRequest.getParentIdentificationNumber(),
                BIN_RIN_PATTERN)));

    validateGeographicalLocations(
        Map.of(GEOGRAPHICAL_LOCATION_NAME, serverCreateComponentRequest.getGeographicalLocation()));

    validateExists(serverCreateComponentRequest.getParentIdentificationNumber(),
        PARENT_IDENTIFICATION_NUMBER_NAME);
  }

  @Override
  public void validateUpdate(ServerUpdateComponentRequest object) {
    validateNotNull(List.of(Pair.of(COMPONENT, object)));

    validateNotNull(List.of(
        Pair.of(COMPONENT, object),
        Pair.of(COMPONENT_DESCRIPTION_NAME, object.getDescription()),
        Pair.of(IDENTIFICATION_NUMBER_NAME, object.getIdentificationNumber()),
        Pair.of(PARENT_IDENTIFICATION_NUMBER_NAME, object.getParentIdentificationNumber()),
        Pair.of(GEOGRAPHICAL_LOCATION_NAME, object.getGeographicalLocation()),
        Pair.of(COMPONENT_TYPE_NAME, object.getType())));

    validateNotEmpty(Map.of(COMPONENT_DESCRIPTION_NAME, object.getDescription()));

    validateMatchesRegex(
        Map.of(
            IDENTIFICATION_NUMBER_NAME, Pair.of(object.getIdentificationNumber(), CIN_PATTERN),
            PARENT_IDENTIFICATION_NUMBER_NAME,
            Pair.of(object.getParentIdentificationNumber(), BIN_RIN_PATTERN)));

    validateGeographicalLocations(
        Map.of(GEOGRAPHICAL_LOCATION_NAME, object.getGeographicalLocation()));

    validateExists(object.getParentIdentificationNumber(), PARENT_IDENTIFICATION_NUMBER_NAME);

    validateExists(object.getIdentificationNumber(), IDENTIFICATION_NUMBER_NAME);
  }

}
