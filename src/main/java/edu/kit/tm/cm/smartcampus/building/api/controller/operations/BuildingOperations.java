package edu.kit.tm.cm.smartcampus.building.api.controller.operations;

import edu.kit.tm.cm.smartcampus.building.api.controller.dto.BuildingRequest;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import java.util.Collection;
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
@RequestMapping("/buildings")
public interface BuildingOperations {

  /**
   * List all buildings on "/buildings" url.
   *
   * @return a collection of all {@link Building} this domain service manages
   */
  @GetMapping("")
  Collection<Building> listBuildings();

  /**
   * Create a new {@link Building} on "/buildings" url in this domain service.
   *
   * @param buildingRequest the request for the building to be created in this service
   * @return the created building
   */
  @PostMapping("")
  Building createBuilding(@RequestBody BuildingRequest buildingRequest);

  /**
   * Get a specific {@link Building} on "/buildings/{bin}" url from tis domain service.
   *
   * @param bin identification number of the requested building in this domain service
   * @return the requested building
   */
  @GetMapping("/{bin}")
  Building getBuilding(@PathVariable String bin);

  /**
   * Update a specific {@link Building} on "/buildings" url in this domain service.
   *
   * @param building the building to be updated in this service
   * @return the updated building with updated attributes
   */
  @PutMapping("")
  Building updateBuilding(@RequestBody Building building);

  /**
   * Remove a {@link Building} on "/buildings/{bin}" from this domain service.
   *
   * @param bin identification number of the building to be removed
   */
  @DeleteMapping("/{bin}")
  void removeBuilding(@PathVariable String bin);


  /**
   * List all rooms that belong to a certain building on "/buildings/{bin}/rooms" url.
   *
   * @param bin identification number of the building
   * @return a collection of all {@link Room} this domain service manages that belong to the
   *     building
   */
  @GetMapping("/{bin}/rooms")
  Collection<Room> listBuildingRooms(@PathVariable String bin);

  /**
   * List all components that belong to a certain building on "/buildings/{bin}/components" url.
   *
   * @param bin identification number of the building
   * @return a collection of all {@link Component} this domain service manages that belong to the
   *     building
   */
  @GetMapping("/{bin}/components")
  Collection<Component> listBuildingComponents(@PathVariable String bin);

  /**
   * List all notifications that belong to a certain building on "/buildings/{bin}/notifications"
   * url.
   *
   * @param bin identification number of the building
   * @return a collection of all {@link Notification} this domain service manages that belong to the
   *     building
   */
  @GetMapping("/{bin}/notifications")
  Collection<Notification> listBuildingNotifications(@PathVariable String bin);
}
