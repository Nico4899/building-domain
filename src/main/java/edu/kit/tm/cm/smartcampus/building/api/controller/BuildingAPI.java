package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping
public interface BuildingAPI {

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
}
