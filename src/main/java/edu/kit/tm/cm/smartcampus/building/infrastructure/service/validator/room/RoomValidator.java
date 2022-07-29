package edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.room;

import edu.kit.tm.cm.smartcampus.building.api.controller.room.dto.ServerCreateRoomRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.room.dto.ServerUpdateRoomRequest;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.building.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.component.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.notification.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.room.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.Validator;
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
public class RoomValidator extends Validator<ServerUpdateRoomRequest, ServerCreateRoomRequest> {

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
  public void validateCreate(ServerCreateRoomRequest requestObject) {
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
  public void validateUpdate(ServerUpdateRoomRequest object) {
    validateNotNull(Map.of(ROOM, object));

    validateNotNull(Map.of(ROOM_NAME, object.getName(), ROOM_NUMBER, object.getNumber(),
        IDENTIFICATION_NUMBER_NAME, object.getIdentificationNumber(),
        PARENT_NAME, object.getParentIdentificationNumber(), FLOOR_NAME,
        object.getFloor(), ROOM_TYPE_NAME, object.getType()));

    validateNotEmpty(Map.of(ROOM_NAME, object.getName(), ROOM_NUMBER, object.getNumber()));

    validateMatchesRegex(
        Map.of(IDENTIFICATION_NUMBER_NAME, Pair.of(object.getIdentificationNumber(), RIN_PATTERN)));

    validateGeographicalLocations(
        Map.of(GEOGRAPHICAL_LOCATION_NAME, object.getGeographicalLocation()));

    validateExists(object.getIdentificationNumber(), PARENT_IDENTIFICATION_NUMBER_NAME);


    validateRoomFloor(object.getFloor(), object.getParentIdentificationNumber());

    validateExists(object.getIdentificationNumber(), IDENTIFICATION_NUMBER_NAME);
  }


}
