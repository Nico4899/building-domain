package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.api.payload.BuildingRequest;
import edu.kit.tm.cm.smartcampus.building.api.payload.BuildingResponse;
import edu.kit.tm.cm.smartcampus.building.api.payload.BuildingsResponse;
import org.springframework.web.bind.annotation.*;

@RequestMapping
public interface BuildingAPI {

  @GetMapping("/buildings")
  BuildingsResponse listBuildings();

  @PostMapping("/buildings")
  BuildingResponse createBuilding(@RequestBody BuildingRequest buildingRequest);

  @GetMapping("/buildings/{bin}")
  BuildingResponse getBuilding(@PathVariable String bin);

  @PutMapping("/buildings/{bin}")
  BuildingResponse editBuilding(
      @PathVariable String bin,
      @RequestBody BuildingRequest buildingRequest); // TODO rückgabetyp evtl nur auf bin? @Jonathan

  @DeleteMapping("/buildings/{bin}")
  void deleteBuilding(@PathVariable String bin);
}
