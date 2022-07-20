package edu.kit.tm.cm.smartcampus.building.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import edu.kit.tm.cm.smartcampus.building.GlobalStringCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RoomValidator extends Validator<Room> {

  @Autowired
  protected RoomValidator(
      BuildingRepository buildingRepository,
      RoomRepository roomRepository,
      ComponentRepository componentRepository,
      NotificationRepository notificationRepository) {
    super(buildingRepository, roomRepository, componentRepository, notificationRepository);
  }

  @Override
  protected String getValidateRegex() {
    return GlobalStringCollection.RIN_PATTERN;
  }

  @Override
  public void validateCreate(Room object) {
    validateBase(object);
  }

  @Override
  public void validateUpdate(Room object) {
    validateBase(object);
    validateExists(object.getRin(), "identification_number");
  }

  private void validateBase(Room object) {
    validateNotNull(
      Map.of(
        "room ", object,
        "room_name", object.getRoomName(),
        "room_number", object.getRoomNumber(),
        "room_identification number", object.getRin(),
        "room_parent identification number", object.getParentIn(),
        "room_floor", object.getFloor(),
        "room_type", object.getRoomType(),
        "room_geographical_location", object.getGeographicalLocation()));

    validateNotEmpty(
      Map.of(
        "room_name", object.getRoomName(),
        "room_number", object.getRoomNumber()));

    validateMatchesRegex(
      Map.of(
        GlobalStringCollection.IDENTIFICATION_NUMBER_NAME, Pair.of(object.getRin(), GlobalStringCollection.RIN_PATTERN),
        GlobalStringCollection.PARENT_IDENTIFICATION_NUMBER_NAME, Pair.of(object.getParentIn(), GlobalStringCollection.BIN_PATTERN)));

    validateGeographicalLocation(
      Map.of(GlobalStringCollection.GEOGRAPHICAL_LOCATION_NAME, object.getGeographicalLocation()));

    validateExists(object.getParentIn(), "parent_identification_number");
  }
}
