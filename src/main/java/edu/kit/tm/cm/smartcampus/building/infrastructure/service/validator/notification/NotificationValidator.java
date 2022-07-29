package edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.notification;

import edu.kit.tm.cm.smartcampus.building.api.controller.notification.dto.ServerCreateNotificationRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.notification.dto.ServerUpdateNotificationRequest;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.building.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.component.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.notification.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.room.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.Validator;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

/**
 * This class is a child implementation of the {@link Validator}, it focuses on validating
 * {@link Notification} requests. It calls parent methods to validate certain attributes.
 */
@Component
public class NotificationValidator extends Validator<ServerUpdateNotificationRequest, ServerCreateNotificationRequest> {

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
  public void validateCreate(ServerCreateNotificationRequest requestObject) {
    validateNotNull(Map.of(NOTIFICATION_REQUEST, requestObject));

    validateNotNull(
        Map.of(
            NOTIFICATION_TITLE_NAME,
            requestObject.getTitle(),
            NOTIFICATION_DESCRIPTION_NAME,
            requestObject.getDescription(),
            PARENT_IDENTIFICATION_NUMBER_NAME,
            requestObject.getParentIdentificationNumber()));

    validateNotEmpty(
        Map.of(
            NOTIFICATION_TITLE_NAME,
            requestObject.getTitle(),
            NOTIFICATION_DESCRIPTION_NAME,
            requestObject.getDescription()));

    validateMatchesRegex(
        Map.of(
            PARENT_IDENTIFICATION_NUMBER_NAME,
            Pair.of(requestObject.getParentIdentificationNumber(), BIN_RIN_CIN_PATTERN)));

    validateExists(requestObject.getParentIdentificationNumber(),
        PARENT_IDENTIFICATION_NUMBER_NAME);
  }

  @Override
  public void validateUpdate(ServerUpdateNotificationRequest object) {
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
            object.getParentIdentificationNumber()));

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

    validateExists(object.getIdentificationNumber(), IDENTIFICATION_NUMBER_NAME);
  }

}
