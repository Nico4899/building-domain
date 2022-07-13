package edu.kit.tm.cm.smartcampus.building.infrastructure.service;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions.NotFoundException;
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

  public Building getBuilding(String bin) {
    return buildingRepository.findById(bin).get();
  }

  public Building createBuilding(Building building) {
    return buildingRepository.save(building);
  }

  public void removeBuilding(String bin) {
    buildingRepository.deleteById(bin);
  }

  public Building updateBuilding(Building building) {
    if(!buildingRepository.existsById(building.getIdentificationNumber())) throw new NotFoundException();
    return buildingRepository.save(building);
  }


  public Component createComponent(Component component) {
    return componentRepository.save(component);
  }

  public Collection<Component> listRoomComponents(String rin) {
    return componentRepository.findAllRoomComponents(rin);
  }

  public Component getComponent(String cin) {
    return componentRepository.findById(cin).get();
  }

  public Component updateComponent(Component component) {
    if(!componentRepository.existsById(component.getIdentificationNumber())) throw new NotFoundException();
    return componentRepository.save(component);
  }

  public void removeComponent(String cin) {
    componentRepository.deleteById(cin);
  }


  public Collection<Notification> listBuildingNotifications(String bin) {
    if(!roomRepository.existsById(bin)) throw new NoSuchElementException();
    return notificationRepository.findAllBuildingNotifications(bin);
  }

  public Collection<Notification> listRoomNotifications(String rin) {
    if(!roomRepository.existsById(rin)) throw new NoSuchElementException();
    return notificationRepository.findAllRoomNotifications(rin);
  }

  public Collection<Notification> listComponentNotifications(String cin) {
    if(!componentRepository.existsById(cin)) throw new NoSuchElementException();
    return notificationRepository.findAllComponentNotifications(cin);
  }

  public Notification getNotification(String nin) {
    if(!notificationRepository.existsById(nin)) throw new NotFoundException();
    return notificationRepository.findById(nin).orElse(null);
  }

  public Notification updateNotification(Notification notification) {
    if(!notificationRepository.existsById(notification.getIdentificationNumber())) throw new NotFoundException();
    return notificationRepository.save(notification);
  }

  public Notification createNotification(Notification notification) {
    if(!notificationRepository.existsById(notification.getIdentificationNumber())) throw new NotFoundException();
    return notificationRepository.save(notification);
  }

  public void removeNotification(String nin) {
    if(!notificationRepository.existsById(nin)) throw new NotFoundException();
    notificationRepository.deleteById(nin);
  }

  public Collection<Room> listRooms(String bin) {
    if(!buildingRepository.existsById(bin)) throw new NotFoundException();
    return roomRepository.findAllBuildingRooms(bin);
  }

  public Room getRoom(String rin) throws NotFoundException {
    if (roomRepository.existsById(rin)) {
      return roomRepository.findById(rin).get();
    }
    throw new NotFoundException();
  }

  public Room createRoom(Room room) {
    return this.roomRepository.save(room);
  }

  public void deleteRoom(String rin) {
    roomRepository.deleteById(rin);
  }

  public Room updateRoom(Room room) {
    return this.roomRepository.save(room);
  }
}
