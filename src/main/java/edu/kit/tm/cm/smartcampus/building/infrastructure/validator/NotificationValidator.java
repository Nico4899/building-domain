package edu.kit.tm.cm.smartcampus.building.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.building.GlobalStringCollection;
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
    return GlobalStringCollection.NIN_PATTERN;
  }

  @Override
  public void validateCreate(Notification object) {
    validateBase(object);
  }

  @Override
  public void validateUpdate(Notification object) {
    validateBase(object);
    validateExists(object.getNin(), "identification_number");
  }

  private void validateBase(Notification object) {
    validateNotNull(
        Map.of(
            "notification ", object,
            "notification title", object.getNotificationTitle(),
            "notification description", object.getNotificationDescription(),
            "notification identification number", object.getNin(),
            "notification parent identification number", object.getParentIn(),
            "notification creation time", object.getCreationTime()));

    validateNotEmpty(
        Map.of(
            "notification title", object.getNotificationTitle(),
            "notification description", object.getNotificationDescription()));

    validateMatchesRegex(
        Map.of(
            "notification identification number", Pair.of(object.getNin(), "TODO nin regex"),
            "notification parent identification number",
                Pair.of(object.getParentIn(), "TODO bin/rin/cin regex")));

    validateExists(object.getParentIn(), "parent_identification_number");
  }
}
