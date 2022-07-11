package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@RequestMapping
public interface BuildingAPI {

  @GetMapping("/buildings")
  Collection<Building> listBuildings();

  @PostMapping("/buildings")
  Building createBuilding(@RequestBody Building building);

  @PutMapping("/buildings")
  Building updateBuilding(@RequestBody Building building);

  @GetMapping("/buildings/{bin}")
  Building getBuilding(@PathVariable String bin);

  @DeleteMapping("/buildings/{bin}")
  void deleteBuilding(@PathVariable String bin);
}
