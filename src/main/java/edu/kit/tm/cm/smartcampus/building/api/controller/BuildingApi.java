package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
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
public interface BuildingApi {

  // building mappings
  @GetMapping("/buildings")
  Collection<Building> listBuildings();

  @PostMapping("/buildings")
  Building createBuilding(@RequestBody Building building);

  @GetMapping("/buildings/{bin}")
  Building getBuilding(@PathVariable String bin);

  @PutMapping("/buildings/{bin}")
  Building updateBuilding(@PathVariable String bin, @RequestBody Building building);

  @DeleteMapping("/buildings/{bin}")
  void deleteBuilding(@PathVariable String bin);

  // room mappings
  @GetMapping("/buildings/{bin}/rooms")
  Collection<Room> listRooms(@PathVariable String bin);

  @PostMapping("/rooms")
  Room createRoom(@RequestBody Room room);

  @GetMapping("/rooms/{rin}")
  Room getRoom(@PathVariable String rin);

  @PutMapping("/rooms/{rin}")
  Room updateRoom(@PathVariable String rin, @RequestBody Room room);

  @DeleteMapping("/rooms/{rin}")
  void deleteRoom(@PathVariable String rin);

  // component mappings
  @GetMapping("/buildings/{bin}/components")
  Collection<Component> listBuildingComponents(@PathVariable String bin);

  @PostMapping("/buildings/{bin}/components")
  Component createBuildingComponent(@PathVariable String bin, @RequestBody Component component);

  @GetMapping("/rooms/{rin}/components")
  Collection<Component> listRoomComponents(@PathVariable String rin);

  @PostMapping("/rooms/{rin}/components")
  Component createRoomComponent(@PathVariable String rin, @RequestBody Component component);

  @GetMapping("/components/{cin}")
  Component getComponent(@PathVariable String cin);

  @PutMapping("/components/{cin}")
  Component updateComponent(@PathVariable String cin, @RequestBody Component component);

  @DeleteMapping("/components/{cin}")
  void removeComponent(@PathVariable String cin);

  // notification mappings
  @GetMapping("/notifications/{nin}")
  Notification getNotification(@PathVariable String nin);

  @PutMapping("/notifications/{nin}")
  Notification updateNotification(@PathVariable String nin, @RequestBody Notification notification);

  @DeleteMapping("/notifications/{nin}")
  void deleteNotification(@PathVariable String nin);

  @GetMapping("/buildings/{bin}/notifications")
  Collection<Building> listBuildingNotifications(@PathVariable String bin);

  @GetMapping("/rooms/{rin}/notifications")
  Collection<Notification> listRoomNotifications(@PathVariable String rin);

  @GetMapping("/components/{cin}/notifications")
  Collection<Notification> listComponentNotifications(@PathVariable String cin);

  @PostMapping("/notifications")
  Notification createNotification(@RequestBody Notification notification);
}
