package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.api.operations.BuildingOperations;
import edu.kit.tm.cm.smartcampus.building.api.operations.ComponentOperations;
import edu.kit.tm.cm.smartcampus.building.api.operations.NotificationOperations;
import edu.kit.tm.cm.smartcampus.building.api.operations.RoomOperations;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.BuildingService;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class BuildingController
        implements BuildingOperations, RoomOperations, ComponentOperations, NotificationOperations {

  private final BuildingService buildingService;

  @Autowired
  public BuildingController(BuildingService buildingService) {
    this.buildingService = buildingService;
  }

  // "/buildings" urls
  @Override
  public Collection<Building> listBuildings() {
    return buildingService.listBuildings();
  }

  @Override
  public Building createBuilding(Building building) {
    return buildingService.createBuilding(building);
  }

  @Override
  public Building getBuilding(String bin) {
    return buildingService.getBuilding(bin);
  }

  @Override
  public Building updateBuilding(Building building) {
    return buildingService.updateBuilding(building);
  }

  @Override
  public void removeBuilding(String bin) {
    buildingService.removeBuilding(bin);
  }

  @Override
  public Collection<Room> listBuildingRooms(String bin) {
    return buildingService.listBuildingRooms(bin);
  }

  @Override
  public Collection<Component> listBuildingComponents(String bin) {
    return buildingService.listBuildingComponents(bin);
  }

  @Override
  public Collection<Notification> listBuildingNotifications(String bin) {
    return buildingService.listBuildingNotifications(bin);
  }

  // "/rooms" urls
  @Override
  public Room createRoom(Room room) {
    return buildingService.createRoom(room);
  }

  @Override
  public Room getRoom(String rin) {
    return buildingService.getRoom(rin);
  }

  @Override
  public Room updateRoom(Room room) {
    return buildingService.updateRoom(room);
  }

  @Override
  public void removeRoom(String rin) {
    buildingService.removeRoom(rin);
  }

  @Override
  public Collection<Component> listRoomComponents(String rin) {
    return buildingService.listRoomComponents(rin);
  }

  @Override
  public Collection<Notification> listRoomNotifications(String rin) {
    return buildingService.listRoomNotifications(rin);
  }

  // "/components" urls
  @Override
  public Component createComponent(Component component) {
    return buildingService.createComponent(component);
  }

  @Override
  public Component getComponent(String cin) {
    return buildingService.getComponent(cin);
  }

  @Override
  public Component updateComponent(Component component) {
    return buildingService.updateComponent(component);
  }

  @Override
  public void removeComponent(String cin) {
    buildingService.removeComponent(cin);
  }

  @Override
  public Collection<Notification> listComponentNotifications(String cin) {
    return buildingService.listComponentNotifications(cin);
  }

  // "/notifications" urls
  @Override
  public Notification getNotification(String nin) {
    return buildingService.getNotification(nin);
  }

  @Override
  public Notification updateNotification(Notification notification) {
    return buildingService.updateNotification(notification);
  }

  @Override
  public void removeNotification(String nin) {
    buildingService.removeNotification(nin);
  }

  @Override
  public Notification createNotification(Notification notification) {
    return buildingService.createNotification(notification);
  }
}
