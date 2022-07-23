package edu.kit.tm.cm.smartcampus.building.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Map;

// TODO javadocs
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
    return RIN_PATTERN;
  }

  @Override
  public void validateCreate(Room object) {
    validateBase(object);
  }

  @Override
  public void validateUpdate(Room object) {
    validateBase(object);
    validateExists(object.getIdentificationNumber(), IDENTIFICATION_NUMBER_NAME);
  }

  private void validateBase(Room object) {

    validateNotNull(Map.of(ROOM, object));

    validateNotNull(
        Map.of(
            ROOM_NAME, object.getName(),
            ROOM_NUMBER, object.getNumber(),
            IDENTIFICATION_NUMBER_NAME, object.getIdentificationNumber(),
            PARENT_IDENTIFICATION_NUMBER_NAME, object.getParentIdentificationNumber(),
            FLOOR_NAME, object.getFloor(),
            ROOM_TYPE_NAME, object.getType()));

    validateNotEmpty(
        Map.of(
            ROOM_NAME, object.getName(),
            ROOM_NUMBER, object.getNumber()));

    validateMatchesRegex(
        Map.of(
            IDENTIFICATION_NUMBER_NAME,
            Pair.of(object.getIdentificationNumber(), RIN_PATTERN),
            PARENT_IDENTIFICATION_NUMBER_NAME,
            Pair.of(object.getParentIdentificationNumber(), BIN_PATTERN)));

    validateCoordinates(
        Map.of(COORDINATES_NAME, Pair.of(object.getLatitude(), object.getLongitude())));

    validateExists(object.getParentIdentificationNumber(), PARENT_IDENTIFICATION_NUMBER_NAME);

    validateValidRoomFloor(object.getFloor(), object.getParentIdentificationNumber());
  }
}
