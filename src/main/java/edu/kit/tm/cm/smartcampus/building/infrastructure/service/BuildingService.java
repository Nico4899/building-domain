package edu.kit.tm.cm.smartcampus.building.infrastructure.service;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions.ResourceNotFoundException;
import edu.kit.tm.cm.smartcampus.building.infrastructure.validator.BuildingInputValidator;
import edu.kit.tm.cm.smartcampus.building.infrastructure.validator.DataValidator;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class BuildingService {

  private static final String NOT_FOUND = "Object with id %s does not exist";

  private static final String UNKNOWN_ERROR = "Something went wrong.";
  private final BuildingRepository buildingRepository;

  private final RoomRepository roomRepository;

  private final ComponentRepository componentRepository;

  private final NotificationRepository notificationRepository;

  private final BuildingInputValidator buildingInputValidator;

  private final DataValidator dataValidator;


  @Autowired
  public BuildingService(
          BuildingRepository buildingRepository,
          RoomRepository roomRepository,
          ComponentRepository componentRepository,
          NotificationRepository notificationRepository,
          BuildingInputValidator buildingInputValidator) {
    this.buildingRepository = buildingRepository;
    this.roomRepository = roomRepository;
    this.componentRepository = componentRepository;
    this.notificationRepository = notificationRepository;
    this.dataValidator = new DataValidator(buildingRepository, roomRepository, componentRepository,
            notificationRepository);
    this.buildingInputValidator = buildingInputValidator;
  }

  // buildings
  public Collection<Building> listBuildings() {
    Collection<Building> buildings = new ArrayList<>();
    for (Building building : buildingRepository.findAll()) buildings.add(building);
    return buildings;
  }

  public Building getBuilding(String bin) throws ResourceNotFoundException {
    buildingInputValidator.validateBin(bin);
    dataValidator.validateInIsMapped(buildingRepository, bin);
    return buildingRepository.findById(bin).get();
  }

  public Building createBuilding(Building building) {
    buildingInputValidator.validateBuilding(building);
    dataValidator.validateInDoesNotExist(buildingRepository, building.getBin());
    return buildingRepository.save(building);
  }

  public void removeBuilding(String bin) {
    buildingInputValidator.validateBin(bin);
    dataValidator.validateInIsMapped(buildingRepository, bin);
    buildingRepository.deleteById(bin);
  }

  public Building updateBuilding(Building building) {
    buildingInputValidator.validateBuilding(building);
    dataValidator.validateInIsMapped(buildingRepository, building.getBin());
    return buildingRepository.save(building);
  }

  public Collection<Notification> listBuildingNotifications(String bin) {
    buildingInputValidator.validateBin(bin);
    dataValidator.validateInIsMapped(buildingRepository, bin);
    return notificationRepository.findAllBuildingNotifications(bin);
  }

  public Collection<Room> listBuildingRooms(String bin) {
    buildingInputValidator.validateBin(bin);
    dataValidator.validateInIsMapped(buildingRepository, bin);
    return roomRepository.findAllBuildingRooms(bin);
  }

  public Collection<Component> listBuildingComponents(String bin) {
    buildingInputValidator.validateBin(bin);
    dataValidator.validateInIsMapped(buildingRepository, bin);
    return componentRepository.findAllBuildingComponents(bin);
  }

  // rooms
  public Room getRoom(String rin) throws ResourceNotFoundException {
    buildingInputValidator.validateRin(rin);
    dataValidator.validateInIsMapped(roomRepository, rin);
    throw new ResourceNotFoundException(String.format(NOT_FOUND, rin));
  }

  public Room createRoom(Room room) {
    buildingInputValidator.validateRoom(room);
    dataValidator.validateInDoesNotExist(roomRepository, room.getRin());
    dataValidator.validateReferencedId(room.getRin(), room.getParentIn());
    return this.roomRepository.save(room);
  }

  public Room updateRoom(Room room) {
    buildingInputValidator.validateRoom(room);
    dataValidator.validateInIsMapped(roomRepository, room.getRin());
    dataValidator.validateReferencedId(room.getRin(), room.getParentIn());
    return this.roomRepository.save(room);
  }

  public void removeRoom(String rin) {
    buildingInputValidator.validateRin(rin);
    dataValidator.validateInIsMapped(roomRepository, rin);
    roomRepository.deleteById(rin);
  }

  public Collection<Component> listRoomComponents(String rin) {
    buildingInputValidator.validateRin(rin);
    dataValidator.validateInIsMapped(roomRepository, rin);
    return componentRepository.findAllRoomComponents(rin);
  }

  public Collection<Notification> listRoomNotifications(String rin) {
    buildingInputValidator.validateRin(rin);
    dataValidator.validateInIsMapped(roomRepository, rin);
    return notificationRepository.findAllRoomNotifications(rin);
  }

  // components
  public Component createComponent(Component component) {
    buildingInputValidator.validateComponent(component);
    dataValidator.validateInDoesNotExist(componentRepository, component.getCin());
    dataValidator.validateReferencedId(component.getCin(), component.getParentIn());
    return componentRepository.save(component);
  }

  public Component getComponent(String cin) {
    buildingInputValidator.validateCin(cin);
    dataValidator.validateInIsMapped(componentRepository, cin);
    return componentRepository.findById(cin).get();
  }

  public Component updateComponent(Component component) {
    buildingInputValidator.validateComponent(component);
    dataValidator.validateInIsMapped(componentRepository, component.getCin());
    dataValidator.validateReferencedId(component.getCin(), component.getParentIn());
    return componentRepository.save(component);
  }

  public void removeComponent(String cin) {
    buildingInputValidator.validateCin(cin);
    dataValidator.validateInIsMapped(componentRepository, cin);
    componentRepository.deleteById(cin);
  }

  public Collection<Notification> listComponentNotifications(String cin) {
    buildingInputValidator.validateCin(cin);
    dataValidator.validateInIsMapped(componentRepository, cin);
    return notificationRepository.findAllComponentNotifications(cin);
  }

  // notifications
  public Notification getNotification(String nin) {
    buildingInputValidator.validateNin(nin);
    dataValidator.validateInIsMapped(notificationRepository, nin);
    return notificationRepository.findById(nin).orElse(null);
  }

  public Notification updateNotification(Notification notification) {
    buildingInputValidator.validateNotification(notification);
    dataValidator.validateInIsMapped(notificationRepository, notification.getNin());
    return notificationRepository.save(notification);
  }

  public Notification createNotification(Notification notification) {
    buildingInputValidator.validateNotification(notification);
    dataValidator.validateInDoesNotExist(notificationRepository, notification.getNin());
    dataValidator.validateReferencedId(notification.getNin(), notification.getParentIn());
    return notificationRepository.save(notification);
  }

  public void removeNotification(String nin) {
    buildingInputValidator.validateNin(nin);
    dataValidator.validateInIsMapped(notificationRepository, nin);
    notificationRepository.deleteById(nin);
  }

}
