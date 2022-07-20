package edu.kit.tm.cm.smartcampus.building.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.building.GlobalBuildingStringCollection;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
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
    return GlobalBuildingStringCollection.RIN_PATTERN;
  }

  @Override
  public void validateCreate(Room object) {
    validateBase(object);
  }

  @Override
  public void validateUpdate(Room object) {
    validateBase(object);
    validateExists(object.getRin(), GlobalBuildingStringCollection.IDENTIFICATION_NUMBER_NAME);
  }

  private void validateBase(Room object) {

    validateNotNull(Map.of(GlobalBuildingStringCollection.ROOM, object));

    validateNotNull(
        Map.of(
            GlobalBuildingStringCollection.ROOM_NAME, object.getRoomName(),
            GlobalBuildingStringCollection.ROOM_NUMBER, object.getRoomNumber(),
            GlobalBuildingStringCollection.IDENTIFICATION_NUMBER_NAME, object.getRin(),
            GlobalBuildingStringCollection.PARENT_IDENTIFICATION_NUMBER_NAME, object.getParentIn(),
            GlobalBuildingStringCollection.FLOOR_NAME, object.getFloor(),
            GlobalBuildingStringCollection.ROOM_TYPE_NAME, object.getRoomType(),
            GlobalBuildingStringCollection.GEOGRAPHICAL_LOCATION_NAME, object.getGeographicalLocation()));

    validateNotEmpty(
        Map.of(
            GlobalBuildingStringCollection.ROOM_NAME, object.getRoomName(),
            GlobalBuildingStringCollection.ROOM_NUMBER, object.getRoomNumber()));

    validateMatchesRegex(
        Map.of(
            GlobalBuildingStringCollection.IDENTIFICATION_NUMBER_NAME,
                Pair.of(object.getRin(), GlobalBuildingStringCollection.RIN_PATTERN),
            GlobalBuildingStringCollection.PARENT_IDENTIFICATION_NUMBER_NAME,
                Pair.of(object.getParentIn(), GlobalBuildingStringCollection.BIN_PATTERN)));

    validateGeographicalLocation(
        Map.of(
            GlobalBuildingStringCollection.GEOGRAPHICAL_LOCATION_NAME, object.getGeographicalLocation()));

    validateExists(object.getParentIn(), GlobalBuildingStringCollection.PARENT_IDENTIFICATION_NUMBER_NAME);
  }
}
