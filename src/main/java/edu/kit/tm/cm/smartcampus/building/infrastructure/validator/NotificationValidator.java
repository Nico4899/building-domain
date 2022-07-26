package edu.kit.tm.cm.smartcampus.building.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.building.api.requests.NotificationRequest;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * This class is a child implementation of the {@link Validator}, it focuses on validating {@link
 * Notification} requests. It calls parent methods to validate certain attributes.
 */
@Component
public class NotificationValidator extends Validator<Notification, NotificationRequest> {

  @Autowired
  protected NotificationValidator(
      BuildingRepository buildingRepository,
      RoomRepository roomRepository,
      ComponentRepository componentRepository,
      NotificationRepository notificationRepository) {
    super(buildingRepository, roomRepository, componentRepository, notificationRepository);
  }

  @Override
  protected String getValidateRegex() {
    return NIN_PATTERN;
  }

  @Override
  public void validateCreate(NotificationRequest requestObject) {
    validateNotNull(Map.of(NOTIFICATION, requestObject));

    validateNotNull(
        Map.of(
            NOTIFICATION_TITLE_NAME,
            requestObject.getTitle(),
            NOTIFICATION_DESCRIPTION_NAME,
            requestObject.getDescription(),
            IDENTIFICATION_NUMBER_NAME,
            requestObject.getIdentificationNumber(),
            PARENT_IDENTIFICATION_NUMBER_NAME,
            requestObject.getParentIdentificationNumber()));

    validateBase(requestObject);
  }

  @Override
  public void validateUpdate(Notification object) {
    validateNotNull(Map.of(NOTIFICATION, object));

    validateNotNull(
        Map.of(
            NOTIFICATION_TITLE_NAME,
            object.getTitle(),
            NOTIFICATION_DESCRIPTION_NAME,
            object.getDescription(),
            IDENTIFICATION_NUMBER_NAME,
            object.getIdentificationNumber(),
            PARENT_IDENTIFICATION_NUMBER_NAME,
            object.getParentIdentificationNumber(),
            CREATION_TIME_NAME,
            object.getCreationTime()));

    validateBase(object);

    validateExists(object.getIdentificationNumber(), IDENTIFICATION_NUMBER_NAME);
  }

  private void validateBase(Notification object) {
    validateNotEmpty(
        Map.of(
            NOTIFICATION_TITLE_NAME,
            object.getTitle(),
            NOTIFICATION_DESCRIPTION_NAME,
            object.getDescription()));

    validateMatchesRegex(
        Map.of(
            IDENTIFICATION_NUMBER_NAME,
            Pair.of(object.getIdentificationNumber(), NIN_PATTERN),
            PARENT_IDENTIFICATION_NUMBER_NAME,
            Pair.of(object.getParentIdentificationNumber(), BIN_RIN_CIN_PATTERN)));

    validateExists(object.getParentIdentificationNumber(), PARENT_IDENTIFICATION_NUMBER_NAME);
  }
}
