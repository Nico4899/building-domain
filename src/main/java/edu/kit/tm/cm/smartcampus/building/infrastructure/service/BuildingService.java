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

  public Building getBuilding(String identificationNumber) throws ResourceNotFoundException {
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

  public void removeRoom(String rin) {
    buildingInputValidator.validateIdentificationNumber(
        rin, "room identification number", "rin regex todo");
    dataValidator.validateInIsMapped(roomRepository, rin);
    roomRepository.deleteById(rin);
  }

  public Collection<Component> listRoomComponents(String rin) {
    buildingInputValidator.validateIdentificationNumber(
        rin, "room identification number", "rin regex todo");
    dataValidator.validateInIsMapped(roomRepository, rin);
    return componentRepository.findAllRoomComponents(rin);
  }

  public Collection<Notification> listRoomNotifications(String rin) {
    buildingInputValidator.validateIdentificationNumber(
        rin, "room identification number", "rin regex todo");
    dataValidator.validateInIsMapped(roomRepository, rin);
    return notificationRepository.findAllRoomNotifications(rin);
  }

  // components
  public Component createComponent(Component component) {
    buildingInputValidator.validateCreate(component);
    dataValidator.validateInDoesNotExist(componentRepository, component.getCin());
    dataValidator.validateReferencedId(component.getCin(), component.getParentIn());
    return componentRepository.save(component);
  }

  public Component getComponent(String cin) {
    buildingInputValidator.validateIdentificationNumber(
        cin, "component identification number", "cin regex todo");
    dataValidator.validateInIsMapped(componentRepository, cin);
    return componentRepository.findById(cin).get();
  }

  public Component updateComponent(Component component) {
    buildingInputValidator.validateCreate(component);
    dataValidator.validateInIsMapped(componentRepository, component.getCin());
    dataValidator.validateReferencedId(component.getCin(), component.getParentIn());
    return componentRepository.save(component);
  }

  public void removeComponent(String cin) {
    buildingInputValidator.validateIdentificationNumber(
        cin, "component identification number", "cin regex todo");
    dataValidator.validateInIsMapped(componentRepository, cin);
    componentRepository.deleteById(cin);
  }

  public Collection<Notification> listComponentNotifications(String cin) {
    buildingInputValidator.validateIdentificationNumber(
        cin, "component identification number", "cin regex todo");
    dataValidator.validateInIsMapped(componentRepository, cin);
    return notificationRepository.findAllComponentNotifications(cin);
  }

  // notifications
  public Notification getNotification(String nin) {
    buildingInputValidator.validateIdentificationNumber(
        nin, "notification identification number", "nin regex todo");
    dataValidator.validateInIsMapped(notificationRepository, nin);
    return notificationRepository.findById(nin).orElse(null);
  }

  public Notification updateNotification(Notification notification) {
    buildingInputValidator.validateCreate(notification);
    dataValidator.validateInIsMapped(notificationRepository, notification.getNin());
    return notificationRepository.save(notification);
  }

  public Notification createNotification(Notification notification) {
    buildingInputValidator.validateCreate(notification);
    dataValidator.validateInDoesNotExist(notificationRepository, notification.getNin());
    dataValidator.validateReferencedId(notification.getNin(), notification.getParentIn());
    return notificationRepository.save(notification);
  }

  public void removeNotification(String nin) {
    buildingInputValidator.validateIdentificationNumber(
        nin, "notification identification number", "nin regex todo");
    dataValidator.validateInIsMapped(notificationRepository, nin);
    notificationRepository.deleteById(nin);
  }
}
