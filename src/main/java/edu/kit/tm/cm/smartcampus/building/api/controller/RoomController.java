package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.infrastructure.service.RoomService;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class RoomController implements RoomApi {

  private final RoomService roomService;

  @Autowired
  public RoomController(RoomService roomService) {
    this.roomService = roomService;
  }

  @Override
  public Collection<Room> listRooms(String bin) {
    return roomService.listRooms();
  }

  @Override
  public Room createRoom(Room room) {
    return roomService.createRoom(room);
  }

  @Override
  public Room updateRoom(Room room) {
    return roomService.updateRoom(room);
  }

  @Override
  public Room getRoom(String rin) {
    return null;
    //return roomService.getRoom(rin); TODO exceptions
  }

  @Override
  public void deleteRoom(String rin) {
    roomService.deleteRoom(rin);
  }
}
