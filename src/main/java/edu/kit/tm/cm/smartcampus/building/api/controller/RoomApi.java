package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@RequestMapping
public interface RoomApi {

  @GetMapping("/buildings/{bin}/rooms")
  Collection<Room> listRooms(@PathVariable String bin);

  @PostMapping("/rooms")
  Room createRoom(@RequestBody Room room);

  @PutMapping("/rooms")
  Room updateRoom(@RequestBody Room room);

  @GetMapping("/rooms/{rin}")
  Room getRoom(@PathVariable String rin);


  @DeleteMapping("/rooms/{rin}")
  void deleteRoom(@PathVariable String rin);
}
