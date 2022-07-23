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
    validateExists(object.getIdentificationNumber(), GlobalBuildingStringCollection.IDENTIFICATION_NUMBER_NAME);
  }

  private void validateBase(Room object) {

    validateNotNull(Map.of(GlobalBuildingStringCollection.ROOM, object));

    validateNotNull(
        Map.of(
            GlobalBuildingStringCollection.ROOM_NAME, object.getName(),
            GlobalBuildingStringCollection.ROOM_NUMBER, object.getNumber(),
            GlobalBuildingStringCollection.IDENTIFICATION_NUMBER_NAME, object.getIdentificationNumber(),
            GlobalBuildingStringCollection.PARENT_IDENTIFICATION_NUMBER_NAME, object.getParentIdentificationNumber(),
            GlobalBuildingStringCollection.FLOOR_NAME, object.getFloor(),
            GlobalBuildingStringCollection.ROOM_TYPE_NAME, object.getType()));

    validateNotEmpty(
        Map.of(
            GlobalBuildingStringCollection.ROOM_NAME, object.getName(),
            GlobalBuildingStringCollection.ROOM_NUMBER, object.getNumber()));

    validateMatchesRegex(
        Map.of(
            GlobalBuildingStringCollection.IDENTIFICATION_NUMBER_NAME,
            Pair.of(object.getIdentificationNumber(), GlobalBuildingStringCollection.RIN_PATTERN),
            GlobalBuildingStringCollection.PARENT_IDENTIFICATION_NUMBER_NAME,
            Pair.of(object.getParentIdentificationNumber(), GlobalBuildingStringCollection.BIN_PATTERN)));

    validateCoordinates(
        Map.of(
            GlobalBuildingStringCollection.COORDINATES_NAME, Pair.of(object.getLatitude(), object.getLongitude())));

    validateExists(object.getParentIdentificationNumber(),
        GlobalBuildingStringCollection.PARENT_IDENTIFICATION_NUMBER_NAME);

    //validateFloor(object);

  }

  /*private void validateFloor(final Room object) { //TODO wie macht man dat ganze schön? -> wahrscheinlich direkt in den Validator (mache ich demnächst)
    BuildingRepository buildingRepository;
    Floors floors = buildingRepository.findById(object.getIdentificationNumber()).get().getBuildingFloors();
    if (object.getFloor() < floors.getLowestFloor()) {
      //throw exception
    }
    if (object.getFloor() > floors.getHighestFloor()) {
      //throw exception
    }
  }*/


}
