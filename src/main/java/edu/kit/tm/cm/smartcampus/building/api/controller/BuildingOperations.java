package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/buildings")
public interface BuildingOperations {

  @GetMapping("")
  Collection<Building> listBuildings();

  @PostMapping("")
  Building createBuilding(@RequestBody Building building);

  @GetMapping("/{bin}")
  Building getBuilding(@PathVariable String bin);

  @PutMapping("")
  Building updateBuilding(@RequestBody Building building);

  @DeleteMapping("/{bin}")
  void removeBuilding(@PathVariable String bin);

  @GetMapping("/{bin}/rooms")
  Collection<Room> listBuildingRooms(@PathVariable String bin);

  @GetMapping("/{bin}/components")
  Collection<Component> listBuildingComponents(@PathVariable String bin);

  @GetMapping("/{bin}/notifications")
  Collection<Notification> listBuildingNotifications(@PathVariable String bin);
}
