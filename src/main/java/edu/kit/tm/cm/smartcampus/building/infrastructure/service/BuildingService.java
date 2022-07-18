package edu.kit.tm.cm.smartcampus.building.infrastructure.service;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions.ResourceNotFoundException;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

@Service
public class BuildingService {

  private static final String NOT_FOUND = "Object with id %s does not exist";

  private static final String UNKNOWN_ERROR = "Something went wrong.";
  private final BuildingRepository buildingRepository;

  private final RoomRepository roomRepository;

  private final ComponentRepository componentRepository;

  private final NotificationRepository notificationRepository;

  private final ServiceValidation serviceValidation;



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
    this.serviceValidation = new ServiceValidation(buildingRepository, roomRepository, componentRepository,
        notificationRepository);
  }

  // buildings
  public Collection<Building> listBuildings() {
    Collection<Building> buildings = new ArrayList<>();
    for (Building building : buildingRepository.findAll()) buildings.add(building);
    return buildings;
  }

  public Building getBuilding(String bin) throws ResourceNotFoundException {
    if (!buildingRepository.existsById(bin)) throw new ResourceNotFoundException(String.format(NOT_FOUND, bin));
    return buildingRepository.findById(bin).get();
  }

  public Building createBuilding(Building building) {
    return buildingRepository.save(building);
  }

  public void removeBuilding(String bin) {
    if (!buildingRepository.existsById(bin)) throw new ResourceNotFoundException(String.format(NOT_FOUND, bin));
    buildingRepository.deleteById(bin);
  }

  public Building updateBuilding(Building building) {
    if (!buildingRepository.existsById(building.getIdentificationNumber()))
      throw new ResourceNotFoundException(String.format(NOT_FOUND, building.getIdentificationNumber()));
    return buildingRepository.save(building);
  }

  public Collection<Notification> listBuildingNotifications(String bin) {
    if (!roomRepository.existsById(bin)) throw new NoSuchElementException();
    return notificationRepository.findAllBuildingNotifications(bin);
  }

  public Collection<Room> listBuildingRooms(String bin) {
    if (!buildingRepository.existsById(bin)) throw new ResourceNotFoundException(String.format(NOT_FOUND, bin));
    return roomRepository.findAllBuildingRooms(bin);
  }

  public Collection<Component> listBuildingComponents(String bin) {
    if (!buildingRepository.existsById(bin)) throw new ResourceNotFoundException(String.format(NOT_FOUND, bin));
    return componentRepository.findAllBuildingComponents(bin);
  }

  // rooms
  public Room getRoom(String rin) throws ResourceNotFoundException {
    if (roomRepository.existsById(rin)) {
      return roomRepository.findById(rin).get();
    }
    throw new ResourceNotFoundException(String.format(NOT_FOUND, rin));
  }

  public Room createRoom(Room room) {
    if (serviceValidation.validateReferencedId(room.getIdentificationNumber(), room.getParentIdentificationNumber())) {
      return this.roomRepository.save(room);
    } else {
      throw new ResourceNotFoundException(UNKNOWN_ERROR);
    }

  }

  public Room updateRoom(Room room) { //todo Nochmal parent checken?
    if (!roomRepository.existsById(room.getIdentificationNumber()))
      throw new ResourceNotFoundException(String.format(NOT_FOUND, room.getIdentificationNumber()));
    return this.roomRepository.save(room);
  }

  public void removeRoom(String rin) {
    if (!roomRepository.existsById(rin)) throw new ResourceNotFoundException(String.format(NOT_FOUND, rin));
    roomRepository.deleteById(rin);
  }

  public Collection<Component> listRoomComponents(String rin) {
    if (!roomRepository.existsById(rin))
      throw new ResourceNotFoundException(String.format(NOT_FOUND, rin));
    return componentRepository.findAllRoomComponents(rin);
  }

  public Collection<Notification> listRoomNotifications(String rin) {
    if (!roomRepository.existsById(rin)) throw new NoSuchElementException();
    return notificationRepository.findAllRoomNotifications(rin);
  }

  // components
  public Component createComponent(Component component) {
    if(serviceValidation.validateReferencedId(component.getIdentificationNumber(),
        component.getParentIdentificationNumber())) {
      return componentRepository.save(component);
    } else {
      throw new ResourceNotFoundException(String.format(UNKNOWN_ERROR, component.getIdentificationNumber()));
    }
  }

  public Component getComponent(String cin) {
    if (!componentRepository.existsById(cin))
      throw new ResourceNotFoundException(String.format(NOT_FOUND, cin));
    return componentRepository.findById(cin).get();
  }

  public Component updateComponent(Component component) {
    if (!componentRepository.existsById(component.getIdentificationNumber()))
      throw new ResourceNotFoundException(String.format(NOT_FOUND, component.getIdentificationNumber()));
    return componentRepository.save(component);
  }

  public void removeComponent(String cin) {
    if (!componentRepository.existsById(cin))
      throw new ResourceNotFoundException(String.format(NOT_FOUND, cin));
    componentRepository.deleteById(cin);
  }

  public Collection<Notification> listComponentNotifications(String cin) {
    if (!componentRepository.existsById(cin)) throw new NoSuchElementException();
    return notificationRepository.findAllComponentNotifications(cin);
  }

  // notifications
  public Notification getNotification(String nin) {
    if (!notificationRepository.existsById(nin)) throw new ResourceNotFoundException(String.format(NOT_FOUND, nin));
    return notificationRepository.findById(nin).orElse(null);
  }

  public Notification updateNotification(Notification notification) {
    if (!notificationRepository.existsById(notification.getIdentificationNumber()))
      throw new ResourceNotFoundException(String.format(NOT_FOUND, notification.getIdentificationNumber()));
    return notificationRepository.save(notification);
  }

  public Notification createNotification(Notification notification) {
    if (!notificationRepository.existsById(notification.getIdentificationNumber()))
      throw new ResourceNotFoundException(String.format(NOT_FOUND, notification.getIdentificationNumber()));
    return notificationRepository.save(notification);
  }

  public void removeNotification(String nin) {
    if (!notificationRepository.existsById(nin)) throw new ResourceNotFoundException(String.format(NOT_FOUND, nin));
    notificationRepository.deleteById(nin);
  }
}
