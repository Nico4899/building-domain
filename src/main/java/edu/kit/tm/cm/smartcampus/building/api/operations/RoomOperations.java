package edu.kit.tm.cm.smartcampus.building.api.operations;

import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

//TODO javadocs
@RequestMapping("/rooms")
public interface RoomOperations {

  @PostMapping("")
  Room createRoom(@RequestBody Room room);

  @GetMapping("/{rin}")
  Room getRoom(@PathVariable String rin);

  @PutMapping("")
  Room updateRoom(@RequestBody Room room);

  @DeleteMapping("/{rin}")
  void removeRoom(@PathVariable String rin);

  @GetMapping("/{rin}/components")
  Collection<Component> listRoomComponents(@PathVariable String rin);

  @GetMapping("/{rin}/notifications")
  Collection<Notification> listRoomNotifications(@PathVariable String rin);
}
