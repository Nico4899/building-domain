package edu.kit.tm.cm.smartcampus.building.infrastructure.service;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions.ResourceNotFoundException;
import edu.kit.tm.cm.smartcampus.building.infrastructure.validator.BuildingValidator;
import edu.kit.tm.cm.smartcampus.building.infrastructure.validator.ComponentValidator;
import edu.kit.tm.cm.smartcampus.building.infrastructure.validator.NotificationValidator;
import edu.kit.tm.cm.smartcampus.building.infrastructure.validator.RoomValidator;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class BuildingService {

  private final BuildingRepository buildingRepository;
  private final RoomRepository roomRepository;
  private final ComponentRepository componentRepository;
  private final NotificationRepository notificationRepository;
  private final BuildingValidator buildingValidator;
  private final RoomValidator roomValidator;
  private final ComponentValidator componentValidator;
  private final NotificationValidator notificationValidator;

  @Autowired
  public BuildingService(
      BuildingRepository buildingRepository,
      RoomRepository roomRepository,
      ComponentRepository componentRepository,
      NotificationRepository notificationRepository,
      BuildingValidator buildingValidator,
      RoomValidator roomValidator,
      ComponentValidator componentValidator,
      NotificationValidator notificationValidator) {
    this.buildingRepository = buildingRepository;
    this.roomRepository = roomRepository;
    this.componentRepository = componentRepository;
    this.notificationRepository = notificationRepository;
    this.buildingValidator = buildingValidator;
    this.roomValidator = roomValidator;
    this.componentValidator = componentValidator;
    this.notificationValidator = notificationValidator;
  }

  // buildings
  public Collection<Building> listBuildings() {
    Collection<Building> buildings = new ArrayList<>();
    for (Building building : this.buildingRepository.findAll()) buildings.add(building);
    return buildings;
  }

  public Building getBuilding(String identificationNumber){
    this.buildingValidator.validate(identificationNumber);
    return buildingRepository.findById(identificationNumber).get();
  }

  public Building createBuilding(Building building) {
    this.buildingValidator.validateCreate(building);
    return buildingRepository.save(building);
  }

  public void removeBuilding(String identificationNumber) {
    this.buildingValidator.validate(identificationNumber);
    buildingRepository.deleteById(identificationNumber);
  }

  public Building updateBuilding(Building building) {
    this.buildingValidator.validateUpdate(building);
    return buildingRepository.save(building);
  }

  public Collection<Notification> listBuildingNotifications(String identificationNumber) {
    this.buildingValidator.validate(identificationNumber);
    return notificationRepository.findAllBuildingNotifications(identificationNumber);
  }

  public Collection<Room> listBuildingRooms(String identificationNumber) {
    this.buildingValidator.validate(identificationNumber);
    return roomRepository.findAllBuildingRooms(identificationNumber);
  }

  public Collection<Component> listBuildingComponents(String identificationNumber) {
    this.buildingValidator.validate(identificationNumber);
    return componentRepository.findAllBuildingComponents(identificationNumber);
  }

  // rooms
  public Room getRoom(String identificationNumber) {
    this.roomValidator.validate(identificationNumber);
    return this.roomRepository.findById(identificationNumber).get();
  }

  public Room createRoom(Room room) {
    this.roomValidator.validateCreate(room);
    return this.roomRepository.save(room);
  }

  public Room updateRoom(Room room) {
    this.roomValidator.validateUpdate(room);
    return this.roomRepository.save(room);
  }

  public void removeRoom(String identificationNumber) {
    this.roomValidator.validate(identificationNumber);
    roomRepository.deleteById(identificationNumber);
  }

  public Collection<Component> listRoomComponents(String identificationNumber) {
    this.roomValidator.validate(identificationNumber);
    return componentRepository.findAllRoomComponents(identificationNumber);
  }

  public Collection<Notification> listRoomNotifications(String identificationNumber) {
    this.roomValidator.validate(identificationNumber);
    return notificationRepository.findAllRoomNotifications(identificationNumber);
  }

  // components
  public Component createComponent(Component component) {
    this.componentValidator.validateCreate(component);
    return componentRepository.save(component);
  }

  public Component getComponent(String identificationNumber) {
    this.componentValidator.validate(identificationNumber);
    return componentRepository.findById(identificationNumber).get();
  }

  public Component updateComponent(Component component) {
    this.componentValidator.validateUpdate(component);
    return componentRepository.save(component);
  }

  public void removeComponent(String identificationNumber) {
    this.componentValidator.validate(identificationNumber);
    componentRepository.deleteById(identificationNumber);
  }

  public Collection<Notification> listComponentNotifications(String identificationNumber) {
    this.componentValidator.validate(identificationNumber);
    return notificationRepository.findAllComponentNotifications(identificationNumber);
  }

  // notifications
  public Notification getNotification(String identificationNumber) {
    this.notificationValidator.validate(identificationNumber);
    return notificationRepository.findById(identificationNumber).get();
  }

  public Notification updateNotification(Notification notification) {
    this.notificationValidator.validateUpdate(notification);
    return notificationRepository.save(notification);
  }

  public Notification createNotification(Notification notification) {
    this.notificationValidator.validateCreate(notification);
    return notificationRepository.save(notification);
  }

  public void removeNotification(String identificationNumber) {
    this.notificationValidator.validate(identificationNumber);
    notificationRepository.deleteById(identificationNumber);
  }
}
