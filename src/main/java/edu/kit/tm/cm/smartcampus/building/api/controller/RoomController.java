package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class RoomController implements RoomApi {

  @Override
  public Collection<Room> getRooms(String bin) {
    return null;
  }

  @Override
  public Room addRoom(String bin, Room room) {
    return null;
  }

  @Override
  public Room getRoom(String rin) {
    return null;
  }

  @Override
  public Room editRoom(String rin, Room room) {
    return null;
  }

  @Override
  public void deleteRoom(String rin) {}
}
