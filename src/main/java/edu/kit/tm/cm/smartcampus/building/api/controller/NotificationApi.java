package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping
public interface NotificationApi {

  @GetMapping("/notifications/{nin}")
  Notification getNotification(@PathVariable String nin);

  @PutMapping("/notifications/{nin}")
  Notification updateNotification(@PathVariable String nin, @RequestBody Notification notification);

  @DeleteMapping("/notifications/{nin}")
  void deleteNotification(@PathVariable String nin);

  // Buildings
  @GetMapping("/buildings/{bin}/notifications")
  Collection<Building> listBuildingNotifications(@PathVariable String bin);

  @PostMapping("buildings/{bin}/notifications")
  Notification createBuildingNotification(
      @PathVariable String bin, @RequestBody Notification notification);

  // Rooms
  @GetMapping("/rooms/{rin}/notifications")
  Collection<Notification> listRoomNotifications(@PathVariable String rin);

  @PostMapping("rooms/{rin}/notifications")
  Notification createRoomNotification(
      @PathVariable String rin, @RequestBody Notification notification);

  // Components
  @GetMapping("/components/{cin}/notifications")
  Collection<Notification> listComponentNotifications(@PathVariable String cin);

  @PostMapping("components/{cin}/notifications")
  Notification createComponentNotification(
      @PathVariable String cin, @RequestBody Notification notification);
}
