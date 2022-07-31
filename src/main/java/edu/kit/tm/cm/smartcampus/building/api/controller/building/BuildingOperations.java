package edu.kit.tm.cm.smartcampus.building.api.controller.building;

import edu.kit.tm.cm.smartcampus.building.api.controller.building.dto.ServerCreateBuildingRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.building.dto.ServerUpdateBuildingRequest;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This interface provides all rest server operations connected "/buildings" requests.
 */
@RequestMapping
public interface BuildingOperations {

  /**
   * List all buildings on "/buildings" url.
   *
   * @return a collection of all {@link Building} this domain service manages
   */
  @GetMapping("/buildings")
  Collection<Building> listBuildings();

  /**
   * Create a new {@link Building} on "/buildings" url in this domain service.
   *
   * @param serverCreateBuildingRequest the request for the building to be created in this service
   * @return the created building
   */
  @PostMapping("/buildings")
  Building createBuilding(@RequestBody ServerCreateBuildingRequest serverCreateBuildingRequest);

  /**
   * Get a specific {@link Building} on "/buildings/{bin}" url from tis domain service.
   *
   * @param bin identification number of the requested building in this domain service
   * @return the requested building
   */
  @GetMapping("/buildings/{bin}")
  Building getBuilding(@PathVariable String bin);

  /**
   * Update a specific {@link Building} on "/buildings" url in this domain service.
   *
   * @param serverUpdateBuildingRequest the building to be updated in this service
   * @return the updated building with updated attributes
   */
  @PutMapping("/buildings")
  Building updateBuilding(@RequestBody ServerUpdateBuildingRequest serverUpdateBuildingRequest);

  /**
   * Remove a {@link Building} on "/buildings/{bin}" from this domain service.
   *
   * @param bin identification number of the building to be removed
   */
  @Modifying
  @Transactional
  @DeleteMapping("/buildings/{bin}")
  void removeBuilding(@PathVariable String bin);
}
