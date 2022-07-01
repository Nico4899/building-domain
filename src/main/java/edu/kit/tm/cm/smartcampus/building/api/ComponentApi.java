package main.java.edu.kit.tm.cm.smartcampus.building.api;

import main.java.edu.kit.tm.cm.smartcampus.building.api.payload.BuildingRequest;
import main.java.edu.kit.tm.cm.smartcampus.building.api.payload.ComponentRequest;
import main.java.edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import main.java.edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

public interface ComponentApi {

    @GetMapping("/buildings/{bin}/components")
    Collection<Component> getComponents(@PathVariable String bin);

    @PostMapping("/buildings/{bin}/components")
    Building createComponent(@PathVariable String bin, @RequestBody ComponentRequest componentRequest);

    @GetMapping("/buildings/{bin}/rooms/{rin}/components")
    Collection<Component> getComponents(@PathVariable String bin, @PathVariable String rin);

    //TODO WIP
    @GetMapping("/buildings/{bin}")
    Building getBuilding(@PathVariable String bin);

    @PutMapping("/buildings/{bin}")
    Building editBuilding(@PathVariable String bin, @RequestBody BuildingRequest buildingRequest);

    @DeleteMapping("/buildings/{bin}")
    void deleteBuilding(@PathVariable String bin);

}
