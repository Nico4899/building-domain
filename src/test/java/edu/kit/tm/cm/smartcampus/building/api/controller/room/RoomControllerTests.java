package edu.kit.tm.cm.smartcampus.building.api.controller.room;

import edu.kit.tm.cm.smartcampus.building.api.controller.GeographicalLocationDTO;
import edu.kit.tm.cm.smartcampus.building.api.controller.room.dto.ServerCreateRoomRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.room.dto.ServerUpdateRoomRequest;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.Service;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class RoomControllerTests {

  //TODO (@Jonathan) "copy paste vom Building controller test" und das für diese klasse und für
  // component und notification
  private static final Service SERVICE = mock(Service.class);
  private static final RoomController ROOM_CONTROLLER = new RoomController(SERVICE);
  //Attributes
  private static final String ROOM_IDENTIFICATION_NUMBER = "r-1";
  private static final String BUILDING_IDENTIFICATION_NUMBER = "b-1";
  private static final String ROOM_NAME = "Testraum";
  private static final String ROOM_NUMBER = "30";
  private static final int ROOM_FLOOR = 4;
  private static final Room.Type ROOM_TYPE = Room.Type.SEMINAR_ROOM;
  private static final GeographicalLocationDTO ROOM_GEOGRAPHICAL_LOCATION =
      new GeographicalLocationDTO(42.123, 43.111111);
  //Requests
  private static final ServerCreateRoomRequest SERVER_CREATE_ROOM_REQUEST =
      new ServerCreateRoomRequest(BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME, ROOM_NUMBER, ROOM_FLOOR, ROOM_TYPE,
          ROOM_GEOGRAPHICAL_LOCATION);
  private static final ServerUpdateRoomRequest SERVER_UPDATE_ROOM_REQUEST =
      new ServerUpdateRoomRequest(BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME, ROOM_NUMBER, ROOM_FLOOR, ROOM_TYPE,
          ROOM_GEOGRAPHICAL_LOCATION, ROOM_IDENTIFICATION_NUMBER);
  @Test
  void createRoom_ShouldCreateRoom() {
    ROOM_CONTROLLER.createRoom(SERVER_CREATE_ROOM_REQUEST);
    Mockito.verify(SERVICE).createRoom(SERVER_CREATE_ROOM_REQUEST);
  }

  @Test
  void getRoom_ShouldGetRoom() {
    ROOM_CONTROLLER.getRoom(ROOM_IDENTIFICATION_NUMBER);
    Mockito.verify(SERVICE).getRoom(ROOM_IDENTIFICATION_NUMBER);
  }

  @Test
  void listRooms_ShouldListRooms() {
    ROOM_CONTROLLER.listRooms(BUILDING_IDENTIFICATION_NUMBER);
    Mockito.verify(SERVICE).listRooms(BUILDING_IDENTIFICATION_NUMBER);
  }

  @Test
  void updateRoom_ShouldUpdateRoom() {
    ROOM_CONTROLLER.updateRoom(SERVER_UPDATE_ROOM_REQUEST);
    Mockito.verify(SERVICE).updateRoom(SERVER_UPDATE_ROOM_REQUEST);
  }

  @Test
  void removeRoom_ShouldRemoveRoom() {
    ROOM_CONTROLLER.removeRoom(ROOM_IDENTIFICATION_NUMBER);
    Mockito.verify(SERVICE).removeRoom(ROOM_IDENTIFICATION_NUMBER);
  }

}
