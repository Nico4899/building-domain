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
    return null;
  }

  @Override
  public Building createBuilding(Building building) {
    return null;
  }

  @Override
  public Building getBuilding(String bin) {
    return null;
  }

  @Override
  public Building updateBuilding(String bin, Building building) {
    return null;
  }

  @Override
  public void deleteBuilding(String bin) {

  }

  @Override
  public Collection<Room> listRooms(String bin) {
    return null;
  }

  @Override
  public Room createRoom(Room room) {
    return null;
  }

  @Override
  public Room getRoom(String rin) {
    return null;
  }

  @Override
  public Room updateRoom(String rin, Room room) {
    return null;
  }

  @Override
  public void deleteRoom(String rin) {

  }

  @Override
  public Collection<Component> listBuildingComponents(String bin) {
    return null;
  }

  @Override
  public Component createBuildingComponent(String bin, Component component) {
    return null;
  }

  @Override
  public Collection<Component> listRoomComponents(String rin) {
    return null;
  }

  @Override
  public Component createRoomComponent(String rin, Component component) {
    return null;
  }

  @Override
  public Component getComponent(String cin) {
    return null;
  }

  @Override
  public Component updateComponent(String cin, Component component) {
    return null;
  }

  @Override
  public void removeComponent(String cin) {

  }

  @Override
  public Notification getNotification(String nin) {
    return null;
  }

  @Override
  public Notification updateNotification(String nin, Notification notification) {
    return null;
  }

  @Override
  public void deleteNotification(String nin) {

  }

  @Override
  public Collection<Building> listBuildingNotifications(String bin) {
    return null;
  }

  @Override
  public Collection<Notification> listRoomNotifications(String rin) {
    return null;
  }

  @Override
  public Collection<Notification> listComponentNotifications(String cin) {
    return null;
  }

  @Override
  public Notification createNotification(Notification notification) {
    return null;
  }
}
