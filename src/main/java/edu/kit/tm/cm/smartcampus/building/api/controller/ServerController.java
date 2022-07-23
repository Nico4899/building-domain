package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.api.operations.BuildingOperations;
import edu.kit.tm.cm.smartcampus.building.api.operations.ComponentOperations;
import edu.kit.tm.cm.smartcampus.building.api.operations.NotificationOperations;
import edu.kit.tm.cm.smartcampus.building.api.operations.RoomOperations;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.Service;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ServerController
        implements BuildingOperations, RoomOperations, ComponentOperations, NotificationOperations {

  private final Service service;

  @Autowired
  public ServerController(Service service) {
    this.service = service;
  }

  // "/buildings" urls
  @Override
  public Collection<Building> listBuildings() {
    return service.listBuildings();
  }

  @Override
  public Building createBuilding(Building building) {
    return service.createBuilding(building);
  }

  @Override
  public Building getBuilding(String bin) {
    return service.getBuilding(bin);
  }

  @Override
  public Building updateBuilding(Building building) {
    return service.updateBuilding(building);
  }

  @Override
  public void removeBuilding(String bin) {
    service.removeBuilding(bin);
  }

  @Override
  public Collection<Room> listBuildingRooms(String bin) {
    return service.listBuildingRooms(bin);
  }

  @Override
  public Collection<Component> listBuildingComponents(String bin) {
    return service.listBuildingComponents(bin);
  }

  @Override
  public Collection<Notification> listBuildingNotifications(String bin) {
    return service.listBuildingNotifications(bin);
  }

  // "/rooms" urls
  @Override
  public Room createRoom(Room room) {
    return service.createRoom(room);
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

  // "/components" urls
  @Override
  public Component createComponent(Component component) {
    return service.createComponent(component);
  }

  @Override
  public Component getComponent(String cin) {
    return service.getComponent(cin);
  }

  @Override
  public Component updateComponent(Component component) {
    return service.updateComponent(component);
  }

  @Override
  public void removeComponent(String cin) {
    service.removeComponent(cin);
  }

  @Override
  public Collection<Notification> listComponentNotifications(String cin) {
    return service.listComponentNotifications(cin);
  }

  // "/notifications" urls
  @Override
  public Notification getNotification(String nin) {
    return service.getNotification(nin);
  }

  @Override
  public Notification updateNotification(Notification notification) {
    return service.updateNotification(notification);
  }

  @Override
  public void removeNotification(String nin) {
    service.removeNotification(nin);
  }

  @Override
  public Notification createNotification(Notification notification) {
    return service.createNotification(notification);
  }
}
