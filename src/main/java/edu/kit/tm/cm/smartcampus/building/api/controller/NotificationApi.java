package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@RequestMapping
public interface NotificationApi {

  @GetMapping("/rooms/{rin}/notifications")
  Collection<Notification> listRoomNotifications(@PathVariable String rin);

  @GetMapping("/buildings/{bin}/notifications")
  Collection<Building> listBuildingNotifications(@PathVariable String bin);

  @GetMapping("/components/{cin}/notifications")
  Collection<Notification> listComponentNotifications(@PathVariable String cin);

  @GetMapping("/notifications/{nin}")
  Notification getNotification(@PathVariable String nin);

  @PutMapping("/notifications")
  Notification updateNotification(@RequestBody Notification notification);

  @PostMapping("/notifications")
  Notification createNotification(@RequestBody Notification notification);

  @DeleteMapping("/notifications/{nin}")
  void deleteNotification(@PathVariable String nin);
}
