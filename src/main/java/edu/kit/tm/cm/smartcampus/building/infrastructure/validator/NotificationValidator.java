package edu.kit.tm.cm.smartcampus.building.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.building.GlobalBuildingStringCollection;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NotificationValidator extends Validator<Notification> {

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
    return GlobalBuildingStringCollection.NIN_PATTERN;
  }

  @Override
  public void validateCreate(Notification object) {
    validateNotNull(Map.of(GlobalBuildingStringCollection.NOTIFICATION, object));

    validateNotNull(
        Map.of(
            GlobalBuildingStringCollection.NOTIFICATION_TITLE_NAME,
            object.getTitle(),
            GlobalBuildingStringCollection.NOTIFICATION_DESCRIPTION_NAME,
            object.getDescription(),
            GlobalBuildingStringCollection.IDENTIFICATION_NUMBER_NAME,
            object.getIdentificationNumber(),
            GlobalBuildingStringCollection.PARENT_IDENTIFICATION_NUMBER_NAME,
            object.getParentIdentificationNumber()));

    validateBase(object);
  }

  @Override
  public void validateUpdate(Notification object) {
    validateNotNull(Map.of(GlobalBuildingStringCollection.NOTIFICATION, object));

    validateNotNull(
        Map.of(
            GlobalBuildingStringCollection.NOTIFICATION_TITLE_NAME,
            object.getTitle(),
            GlobalBuildingStringCollection.NOTIFICATION_DESCRIPTION_NAME,
            object.getDescription(),
            GlobalBuildingStringCollection.IDENTIFICATION_NUMBER_NAME,
            object.getIdentificationNumber(),
            GlobalBuildingStringCollection.PARENT_IDENTIFICATION_NUMBER_NAME,
            object.getParentIdentificationNumber(),
            GlobalBuildingStringCollection.CREATION_TIME_NAME,
            object.getCreationTime()));

    validateBase(object);

    validateExists(
        object.getIdentificationNumber(),
        GlobalBuildingStringCollection.IDENTIFICATION_NUMBER_NAME);
  }

  private void validateBase(Notification object) {
    validateNotEmpty(
        Map.of(
            GlobalBuildingStringCollection.NOTIFICATION_TITLE_NAME, object.getTitle(),
            GlobalBuildingStringCollection.NOTIFICATION_DESCRIPTION_NAME,
            object.getDescription()));

    validateMatchesRegex(
        Map.of(
            GlobalBuildingStringCollection.IDENTIFICATION_NUMBER_NAME,
            Pair.of(
                object.getIdentificationNumber(), GlobalBuildingStringCollection.NIN_PATTERN),
            GlobalBuildingStringCollection.PARENT_IDENTIFICATION_NUMBER_NAME,
            Pair.of(
                object.getParentIdentificationNumber(),
                GlobalBuildingStringCollection.BIN_RIN_CIN_PATTERN)));

    validateExists(
        object.getParentIdentificationNumber(),
        GlobalBuildingStringCollection.PARENT_IDENTIFICATION_NUMBER_NAME);
  }
}
