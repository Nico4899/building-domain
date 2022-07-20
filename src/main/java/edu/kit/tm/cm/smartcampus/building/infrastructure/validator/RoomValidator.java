package edu.kit.tm.cm.smartcampus.building.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import edu.kit.tm.cm.smartcampus.building.utils;
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
    return utils.RIN_PATTERN;
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
        "room_identification number", Pair.of(object.getRin(), "TODO rin regex"),
        "room parent identification number", Pair.of(object.getParentIn(), "TODO bin regex")));

    validateGeographicalLocation(
      Map.of("room geographical location", object.getGeographicalLocation()));

    validateExists(object.getParentIn(), "parent_identification_number");
  }
}
