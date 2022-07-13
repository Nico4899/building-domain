package edu.kit.tm.cm.smartcampus.building.infrastructure.service;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions.NotFoundException;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Service
public class BuildingService {

  private final BuildingRepository buildingRepository;

  private final RoomRepository roomRepository;

  private final ComponentRepository componentRepository;

  private final NotificationRepository notificationRepository;

  @Autowired
  public BuildingService(
      BuildingRepository buildingRepository,
      RoomRepository roomRepository,
      ComponentRepository componentRepository,
      NotificationRepository notificationRepository) {
    this.buildingRepository = buildingRepository;
    this.roomRepository = roomRepository;
    this.componentRepository = componentRepository;
    this.notificationRepository = notificationRepository;
  }

  public Collection<Building> listBuildings() {
    Collection<Building> buildings = new ArrayList<>();
    for (Building building : buildingRepository.findAll()) buildings.add(building);
    return buildings;
  }

  public Collection<Room> listBuildingRooms() {
    return Collections.emptyList();
  }

  public Collection<Component> listBuildingComponents() {
    return Collections.emptyList();
  }

  public Collection<Component> listRoomComponents() {
    return Collections.emptyList();
  }

  public Collection<Room> listBuildingNotifications() {
    return Collections.emptyList();
  }

  public Collection<Room> listRoomNotifications() {
    return Collections.emptyList();
  }

  public Collection<Room> listComponentNotifications() {
    return Collections.emptyList();
  }

  public Building getBuilding(String bin) throws NotFoundException {
    return null;
  }

  public Room getRoom(String rin) {

  }

  public Building createBuilding(Building building) {
    return this.buildingRepository.save(building);
  }

  public void deleteBuilding(String bin) {
    buildingRepository.deleteById(bin);
  }

  public Building updateBuilding(String bin, Building building) {
    building.setIdentificationNumber(bin);
    return this.buildingRepository.save(building);
  }
}
