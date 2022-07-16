package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/components")
public interface ComponentApi {

  @PostMapping("")
  Component createComponent(@RequestBody Component component);

  @GetMapping("/{cin}")
  Component getComponent(@PathVariable String cin);

  @PutMapping("")
  Component updateComponent(@RequestBody Component component);

  @DeleteMapping("/{cin}")
  void removeComponent(@PathVariable String cin);

  @GetMapping("/{cin}/notifications")
  Collection<Notification> listComponentNotifications(@PathVariable String cin);
}
