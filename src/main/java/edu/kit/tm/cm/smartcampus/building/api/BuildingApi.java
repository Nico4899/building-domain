package edu.kit.tm.cm.smartcampus.building.api;

import edu.kit.tm.cm.smartcampus.building.api.payload.BuildingRequest;
import edu.kit.tm.cm.smartcampus.building.api.payload.BuildingResponse;
import edu.kit.tm.cm.smartcampus.building.api.payload.BuildingsResponse;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

public interface BuildingApi {

    @GetMapping("/buildings")
    BuildingsResponse listBuildings();

    @PostMapping("/buildings")
    BuildingResponse createBuilding(@RequestBody BuildingRequest buildingRequest);

    @GetMapping("/buildings/{bin}")
    BuildingResponse getBuilding(@PathVariable String bin);

    @PutMapping("/buildings/{bin}")
    BuildingResponse editBuilding(@PathVariable String bin, @RequestBody BuildingRequest buildingRequest);

    @DeleteMapping("/buildings/{bin}")
    void deleteBuilding(@PathVariable String bin);

}
