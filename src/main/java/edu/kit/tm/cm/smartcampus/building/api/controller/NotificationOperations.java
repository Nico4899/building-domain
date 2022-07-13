package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/notifications")
public interface NotificationOperations {

  @GetMapping("/{nin}")
  Notification getNotification(@PathVariable String nin);

  @PutMapping("")
  Notification updateNotification(@RequestBody Notification notification);

  @DeleteMapping("/{nin}")
  void removeNotification(@PathVariable String nin);

  @PostMapping("")
  Notification createNotification(@RequestBody Notification notification);
}
