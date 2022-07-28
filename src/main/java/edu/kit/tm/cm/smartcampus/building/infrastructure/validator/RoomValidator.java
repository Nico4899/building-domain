package edu.kit.tm.cm.smartcampus.building.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.building.api.requests.RoomRequest;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

/**
 * This class is a child implementation of the {@link Validator}, it focuses on validating
 * {@link Room} requests. It calls parent methods to validate certain attributes.
 */
@Component
public class RoomValidator extends Validator<Room, RoomRequest> {

  @Autowired
  protected RoomValidator(BuildingRepository buildingRepository, RoomRepository roomRepository,
      ComponentRepository componentRepository, NotificationRepository notificationRepository) {
    super(buildingRepository, roomRepository, componentRepository, notificationRepository);
  }

  @Override
  protected String getValidateRegex() {
    return RIN_PATTERN;
  }

  @Override
  public void validateCreate(RoomRequest requestObject) {
    validateNotNull(Map.of(ROOM_REQUEST, requestObject));

    validateNotNull(
        Map.of(ROOM_NAME, requestObject.getName(), ROOM_NUMBER, requestObject.getNumber(),
            PARENT_IDENTIFICATION_NUMBER_NAME, requestObject.getParentIdentificationNumber(),
            FLOOR_NAME, requestObject.getFloor(), ROOM_TYPE_NAME, requestObject.getType()));

    validateNotEmpty(
        Map.of(ROOM_NAME, requestObject.getName(), ROOM_NUMBER, requestObject.getNumber()));

    validateMatchesRegex(Map.of(PARENT_IDENTIFICATION_NUMBER_NAME,
        Pair.of(requestObject.getParentIdentificationNumber(), BIN_PATTERN)));

    validateGeographicalLocations(
        Map.of(GEOGRAPHICAL_LOCATION_NAME, requestObject.getGeographicalLocation()));

    validateExists(requestObject.getParentIdentificationNumber(),
        PARENT_IDENTIFICATION_NUMBER_NAME);

    validateRoomFloor(requestObject.getFloor(), requestObject.getParentIdentificationNumber());
  }

  @Override
  public void validateUpdate(Room object) {
    validateNotNull(Map.of(ROOM, object));

    validateNotNull(Map.of(ROOM_NAME, object.getName(), ROOM_NUMBER, object.getNumber(),
        IDENTIFICATION_NUMBER_NAME, object.getIdentificationNumber(),
        PARENT_NAME, object.getParentBuilding(), FLOOR_NAME,
        object.getFloor(), ROOM_TYPE_NAME, object.getType()));

    validateNotEmpty(Map.of(ROOM_NAME, object.getName(), ROOM_NUMBER, object.getNumber()));

    validateMatchesRegex(
        Map.of(IDENTIFICATION_NUMBER_NAME, Pair.of(object.getIdentificationNumber(), RIN_PATTERN)));

    validateGeographicalLocations(
        Map.of(GEOGRAPHICAL_LOCATION_NAME, object.getGeographicalLocation()));

    //validateExists(object.getParentBuilding(), PARENT_IDENTIFICATION_NUMBER_NAME);
    //TODo @Jonathan das braucht man ja nicht mehr weil jetzt als Attribut ein building
    // gespeichert wird und wenn das!=null is muss es ja valid sein oder? oder gehen wir hier
    // dann in den building validator o.ä.?

    validateRoomFloor(object.getFloor(), object.getParentBuilding().getIdentificationNumber());

    validateExists(object.getIdentificationNumber(), IDENTIFICATION_NUMBER_NAME);
  }


}
