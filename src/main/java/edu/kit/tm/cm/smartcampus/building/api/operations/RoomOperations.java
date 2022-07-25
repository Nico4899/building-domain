package edu.kit.tm.cm.smartcampus.building.api.operations;

import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * This interface provides all rest server operations connected "/rooms" requests.
 */
@RequestMapping("/rooms")
public interface RoomOperations {

  /**
   * Create a new {@link Room} on "/rooms" url in this domain service.
   *
   * @param room the room to be created in this service
   * @return the created room
   */
  @PostMapping("")
  Room createRoom(@RequestBody Room room);

  /**
   * Get a specific {@link Room} on "/rooms/{rin}" url from tis domain service.
   *
   * @param rin identification number of the requested room in this domain service
   * @return the requested room
   */
  @GetMapping("/{rin}")
  Room getRoom(@PathVariable String rin);

  /**
   * Update a specific {@link Room} on "/rooms" url in this domain service.
   *
   * @param room the room to be updated in this service
   * @return the updated room with updated attributes
   */
  @PutMapping("")
  Room updateRoom(@RequestBody Room room);

  /**
   * Remove a {@link Room} on "/rooms/{rin}" from this domain service.
   *
   * @param rin identification number of the room to be removed
   */
  @DeleteMapping("/{rin}")
  void removeRoom(@PathVariable String rin);

  /**
   * List all components that belong to a certain room on "/rooms/{rin}/components" url.
   *
   * @param rin identification number of the room
   * @return a collection of all {@link Component} this domain service manages that belong to the room
   */
  @GetMapping("/{rin}/components")
  Collection<Component> listRoomComponents(@PathVariable String rin);

  /**
   * List all notifications that belong to a certain room on "/rooms/{rin}/notifications" url.
   *
   * @param rin identification number of the room
   * @return a collection of all {@link Notification} this domain service manages that belong to the room
   */
  @GetMapping("/{rin}/notifications")
  Collection<Notification> listRoomNotifications(@PathVariable String rin);
}
