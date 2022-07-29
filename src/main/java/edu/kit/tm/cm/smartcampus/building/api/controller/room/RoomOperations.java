package edu.kit.tm.cm.smartcampus.building.api.controller.room;

import edu.kit.tm.cm.smartcampus.building.api.controller.room.dto.ServerCreateRoomRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.room.dto.ServerUpdateRoomRequest;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/** This interface provides all rest server operations connected "/rooms" requests. */
@RequestMapping
public interface RoomOperations {

  /**
   * Create a new {@link Room} on "/rooms" url in this domain service.
   *
   * @param serverCreateRoomRequest the request for the room to be created in this service
   * @return the created room
   */
  @PostMapping("/rooms")
  Room createRoom(@RequestBody ServerCreateRoomRequest serverCreateRoomRequest);

  /**
   * Get a specific {@link Room} on "/rooms/{rin}" url from tis domain service.
   *
   * @param rin identification number of the requested room in this domain service
   * @return the requested room
   */
  @GetMapping("/rooms/{rin}")
  Room getRoom(@PathVariable String rin);

  /**
   * Update a specific {@link Room} on "/rooms" url in this domain service.
   *
   * @param serverUpdateRoomRequest the room to be updated in this service
   * @return the updated room with updated attributes
   */
  @PutMapping("/rooms")
  Room updateRoom(@RequestBody ServerUpdateRoomRequest serverUpdateRoomRequest);

  /**
   * Remove a {@link Room} on "/rooms/{rin}" from this domain service.
   *
   * @param rin identification number of the room to be removed
   */
  @DeleteMapping("/rooms/{rin}")
  void removeRoom(@PathVariable String rin);

  /**
   * List all rooms that belong to a certain building on "/buildings/{bin}/rooms" url.
   *
   * @param in identification number of the parent
   * @return a collection of all {@link Room} this domain service manages that belong to the
   *     building
   */
  @GetMapping("{in}/rooms")
  Collection<Room> listRooms(@PathVariable String in);
}
