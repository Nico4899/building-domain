package main.java.edu.kit.tm.cm.smartcampus.building.api;

import main.java.edu.kit.tm.cm.smartcampus.building.api.payload.BuildingRequest;
import main.java.edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

public interface BuildingApi {

    @GetMapping("/buildings")
    Collection<Building> listBuildings();

    @PostMapping("/buildings")
    Building createBuilding(@RequestBody BuildingRequest buildingRequest);

    @GetMapping("/buildings/{bin}")
    Building getBuilding(@PathVariable String bin);

    @PutMapping("/buildings/{bin}")
    Building editBuilding(@PathVariable String bin, @RequestBody BuildingRequest buildingRequest);

    @DeleteMapping("/buildings/{bin}")
    void deleteBuilding(@PathVariable String bin);

}
