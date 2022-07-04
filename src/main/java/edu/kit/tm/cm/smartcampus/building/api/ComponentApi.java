package edu.kit.tm.cm.smartcampus.building.api;

import edu.kit.tm.cm.smartcampus.building.api.payload.*;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

public interface ComponentApi {

    @GetMapping("/buildings/{bin}/components")
    ComponentsResponse getBuildingComponents(@PathVariable String bin);

    @PostMapping("/buildings/{bin}/components")
    ComponentResponse createBuildingComponent(@PathVariable String bin, @RequestBody ComponentRequest componentRequest);

    @GetMapping("/rooms/{rin}/components")
    ComponentsResponse getRoomComponents(@PathVariable String rin);

    @PostMapping("/rooms/{rin}/components")
    ComponentResponse createRoomComponent(@PathVariable String rin, @RequestBody ComponentRequest componentRequest);

    @GetMapping("/components/{cin}")
    ComponentResponse getComponent(@PathVariable String cin);

    @PutMapping("/components/{cin}")
    ComponentResponse editComponent(@PathVariable String cin, @RequestBody ComponentRequest componentRequest);

    @DeleteMapping("/components/{cin}")
    void deleteComponent(@PathVariable String cin);


}
