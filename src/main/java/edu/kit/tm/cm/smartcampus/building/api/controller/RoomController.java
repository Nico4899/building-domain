package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.api.exception.ServerExceptionInterceptor;
import edu.kit.tm.cm.smartcampus.building.api.operations.BuildingOperations;
import edu.kit.tm.cm.smartcampus.building.api.operations.ComponentOperations;
import edu.kit.tm.cm.smartcampus.building.api.operations.NotificationOperations;
import edu.kit.tm.cm.smartcampus.building.api.operations.RoomOperations;
import edu.kit.tm.cm.smartcampus.building.api.requests.RoomRequest;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.Service;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * This class represents the room controller for this domain service. It holds a Spring {@link
 * Bean} of {@link Service} managing all logical operations and running domain constraint
 * validators. It sends REST-Server responses in JSON format via the Spring internal {@link
 * RestController} annotation. In case of errors the {@link ServerExceptionInterceptor} returns
 * given information as REST error response.
 */
@RestController
public class RoomController implements RoomOperations {

  private final Service service;

  /**
   * Instantiates a new room controller for this domain service, it implements all {@link
   * RoomOperations}.
   *
   * @param service the service which controls all domain logic (constructor injected)
   */
  @Autowired
  public RoomController(Service service) {
    this.service = service;
  }

  @Override
  public Room createRoom(RoomRequest roomRequest) {
    return service.createRoom(roomRequest);
  }

  @Override
  public Room getRoom(String rin) {
    return service.getRoom(rin);
  }

  @Override
  public Room updateRoom(Room room) {
    return service.updateRoom(room);
  }

  @Override
  public void removeRoom(String rin) {
    service.removeRoom(rin);
  }

  @Override
  public Collection<Component> listRoomComponents(String rin) {
    return service.listRoomComponents(rin);
  }

  @Override
  public Collection<Notification> listRoomNotifications(String rin) {
    return service.listRoomNotifications(rin);
  }

}
