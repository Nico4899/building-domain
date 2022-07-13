package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.infrastructure.service.BuildingService;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class BuildingController implements BuildingApi {

  private final BuildingService buildingService;

  @Autowired
  public BuildingController(BuildingService buildingService) {
    this.buildingService = buildingService;
  }

  @Override
  public Collection<Building> listBuildings() {
    // return buildingService.listBuildings();
    return null;
  }

  @Override
  public Building createBuilding(Building building) {
    // return buildingService.createBuilding(building);
    return null;
  }

  @Override
  public Building getBuilding(String bin) {
    // return buildingService.getBuilding(bin);
    return null;
  }

  @Override
  public Building updateBuilding(Building building) {
    // return buildingService.updateBuilding(building);
    return null;
  }

  @Override
  public void removeBuilding(String bin) {
    // buildingService.removeBuilding(bin);
  }

  @Override
  public Collection<Room> listRooms(String bin) {
    // return buildingService.listRooms(bin);
    return null;
  }

  @Override
  public Room createRoom(Room room) {
    // return buildingService.createRoom(room);
    return null;
  }

  @Override
  public Room getRoom(String rin) {
    // return buildingService.getRoom(rin);
    return null;
  }

  @Override
  public Room updateRoom(Room room) {
    // return buildingService.updateRoom(room);
    return null;
  }

  @Override
  public void removeRoom(String rin) {
    // buildingService.removeRoom(rin);
  }

  @Override
  public Collection<Component> listBuildingComponents(String bin) {
    // return buildingService.listBuildingComponents(bin);
    return null;
  }

  @Override
  public Collection<Component> listRoomComponents(String rin) {
    // return buildingService.listRoomComponents(rin);
    return null;
  }

  @Override
  public Component createComponent(Component component) {
    // return buildingService.createComponent(component);
    return null;
  }

  @Override
  public Component getComponent(String cin) {
    // return buildingService.getComponent(cin);
    return null;
  }

  @Override
  public Component updateComponent(Component component) {
    // return buildingService.updateComponent(component);
    return null;
  }

  @Override
  public void removeComponent(String cin) {
    // buildingService.removeComponent(cin);
  }

  @Override
  public Notification getNotification(String nin) {
    // return buildingService.getComponent(nin);
    return null;
  }

  @Override
  public Notification updateNotification(Notification notification) {
    // return buildingService.updateNotification(notification);
    return null;
  }

  @Override
  public void removeNotification(String nin) {
    // buildingService.removeNotification(nin);
  }

  @Override
  public Collection<Building> listBuildingNotifications(String bin) {
    // return buildingService.listBuildingNotifications(bin);
    return null;
  }

  @Override
  public Collection<Notification> listRoomNotifications(String rin) {
    // return buildingService.listRoomNotifications(rin);
    return null;
  }

  @Override
  public Collection<Notification> listComponentNotifications(String cin) {
    // return buildingService.listComponentNotifications(cin);
    return null;
  }

  @Override
  public Notification createNotification(Notification notification) {
    // return buildingService.createNotification(notification);
    return null;
  }
}
