package edu.kit.tm.cm.smartcampus.building.infrastructure.service;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions.NotFoundException;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class RoomService {
  private final RoomRepository roomRepository;

  @Autowired
  public RoomService(RoomRepository roomRepository) {
    this.roomRepository = roomRepository;
  }

  public Collection<Room> listRooms() {
    Collection<Room> rooms = new ArrayList<>();
    for (Room room : roomRepository.findAll()) rooms.add(room);
    return rooms;
  }

  public Room getRoom(String rin) throws NotFoundException {
    if (roomRepository.findById(rin).isPresent()) {
      return roomRepository.findById(rin).get();
    }
    throw new NotFoundException();
  }

  public Room createRoom(Room room) {
    return this.roomRepository.save(room);
  }

  public void deleteRoom(String rin) {
    roomRepository.deleteById(rin);
  }

  public Room updateRoom(String rin, Room room) {
    room.setIdentificationNumber(rin);
    return this.roomRepository.save(room);
  }
}
