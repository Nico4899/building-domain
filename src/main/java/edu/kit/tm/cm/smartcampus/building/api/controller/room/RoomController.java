package edu.kit.tm.cm.smartcampus.building.api.controller.room;

import edu.kit.tm.cm.smartcampus.building.api.configuration.error.ServerExceptionInterceptor;
import edu.kit.tm.cm.smartcampus.building.api.controller.room.dto.ServerCreateRoomRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.room.dto.ServerUpdateRoomRequest;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.Service;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class represents the room controller for this domain service. It holds a Spring {@link Bean}
 * of {@link Service} managing all logical operations and running domain constraint validators. It
 * sends REST-Server responses in JSON format via the Spring internal {@link RestController}
 * annotation. In case of errors the {@link ServerExceptionInterceptor} returns given information as
 * REST error response.
 */
@RestController
public class RoomController implements RoomOperations {

  private final Service service;

  /**
   * Instantiates a new room controller for this domain service, it implements all
   * {@link RoomOperations}.
   *
   * @param service the service which controls all domain logic (constructor injected)
   */
  @Autowired
  public RoomController(Service service) {
    this.service = service;
  }

  @Override
  public Room createRoom(ServerCreateRoomRequest serverCreateRoomRequest) {
    return service.createRoom(serverCreateRoomRequest);
  }

  @Override
  public Room getRoom(String rin) {
    return service.getRoom(rin);
  }

  @Override
  public Room updateRoom(ServerUpdateRoomRequest serverUpdateRoomRequest) {
    return service.updateRoom(serverUpdateRoomRequest);
  }

  @Override
  public void removeRoom(String rin) {
    service.removeRoom(rin);
  }

  @Override
  public Collection<Room> listRooms(String in) {
    return service.listRooms(in);
  }

}
