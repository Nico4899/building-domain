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
    Collection<Component> getBuildingComponents(@PathVariable String bin);

    @PostMapping("/buildings/{bin}/components")
    Building createBuildingComponent(@PathVariable String bin, @RequestBody ComponentRequest componentRequest);

    @GetMapping("/buildings/{bin}/rooms/{rin}/components")
    Collection<Component> getRoomComponents(@PathVariable String bin, @PathVariable String rin);

    @PostMapping("/buildings/{bin}/rooms/{rin}/components")
    Building createRoomComponent(@PathVariable String bin, @PathVariable String rin, @RequestBody ComponentRequest componentRequest);

    @PutMapping("/buildings/{bin}/components/{cin}")
    Component editBuildingComponent(@PathVariable String bin, @PathVariable String cin, @RequestBody ComponentRequest componentRequest);

    @DeleteMapping("/buildings/{bin}/components/{cin}")
    void deleteBuildingComponent(@PathVariable String bin, @PathVariable String cin);

    @PutMapping("/buildings/{bin}/rooms/{rin}/components/{cin}")
    Component editRoomComponent(@PathVariable String bin, @PathVariable String rin, @PathVariable String cin, @RequestBody ComponentRequest componentRequest);

    @DeleteMapping("/buildings/{bin}/rooms/{rin}/components/{cin}")
    void deleteRoomComponent(@PathVariable String bin, @PathVariable String rin, @PathVariable String cin);

}
