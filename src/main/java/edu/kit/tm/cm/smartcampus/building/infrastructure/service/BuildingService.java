package edu.kit.tm.cm.smartcampus.building.infrastructure.service;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions.ResourceNotFoundException;
import edu.kit.tm.cm.smartcampus.building.infrastructure.validator.InputValidator;
import edu.kit.tm.cm.smartcampus.building.infrastructure.validator.ServiceValidator;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class BuildingService {

  private static final String NOT_FOUND = "Object with id %s does not exist";

  private static final String UNKNOWN_ERROR = "Something went wrong.";
  private final BuildingRepository buildingRepository;

  private final RoomRepository roomRepository;

  private final ComponentRepository componentRepository;

  private final NotificationRepository notificationRepository;

  private final InputValidator inputValidator;

  private final ServiceValidator serviceValidator;


  @Autowired
  public BuildingService(
          BuildingRepository buildingRepository,
          RoomRepository roomRepository,
          ComponentRepository componentRepository,
          NotificationRepository notificationRepository,
          InputValidator inputValidator) {
    this.buildingRepository = buildingRepository;
    this.roomRepository = roomRepository;
    this.componentRepository = componentRepository;
    this.notificationRepository = notificationRepository;
    this.serviceValidator = new ServiceValidator(buildingRepository, roomRepository, componentRepository,
            notificationRepository);
    this.inputValidator = inputValidator;
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
    if (!buildingRepository.existsById(building.getBin()))
      throw new ResourceNotFoundException(String.format(NOT_FOUND, building.getBin()));
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
    if (serviceValidator.validateReferencedId(room.getRin(), room.getParentIn())) {
      return this.roomRepository.save(room);
    } else {
      throw new ResourceNotFoundException(UNKNOWN_ERROR);
    }

  }

  public Room updateRoom(Room room) { //todo Nochmal parent checken?
    if (!roomRepository.existsById(room.getRin()))
      throw new ResourceNotFoundException(String.format(NOT_FOUND, room.getRin()));
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
    if (serviceValidator.validateReferencedId(component.getCin(),
            component.getParentIn())) {
      return componentRepository.save(component);
    } else {
      throw new ResourceNotFoundException(String.format(UNKNOWN_ERROR, component.getCin()));
    }
  }

  public Component getComponent(String cin) {
    if (!componentRepository.existsById(cin))
      throw new ResourceNotFoundException(String.format(NOT_FOUND, cin));
    return componentRepository.findById(cin).get();
  }

  public Component updateComponent(Component component) {
    if (!componentRepository.existsById(component.getCin()))
      throw new ResourceNotFoundException(String.format(NOT_FOUND, component.getCin()));
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
    if (!notificationRepository.existsById(notification.getNin()))
      throw new ResourceNotFoundException(String.format(NOT_FOUND, notification.getNin()));
    return notificationRepository.save(notification);
  }

  public Notification createNotification(Notification notification) {
    if (!notificationRepository.existsById(notification.getNin()))
      throw new ResourceNotFoundException(String.format(NOT_FOUND, notification.getNin()));
    return notificationRepository.save(notification);
  }

  public void removeNotification(String nin) {
    if (!notificationRepository.existsById(nin)) throw new ResourceNotFoundException(String.format(NOT_FOUND, nin));
    notificationRepository.deleteById(nin);
  }

  public void validateBuilding(Building building) { //TODO validate numFloors
    inputValidator.validateNotNull(Map.of(
            "building name", building.getBuildingName(),
            "building number", building.getBuildingNumber(),
            "building identification number", building.getBin(),
            "building number of floors", building.getNumFloors(),
            "building campus location", building.getCampusLocation(),
            "building geographical location", building.getGeographicalLocation()
    ));

    inputValidator.validateNotEmpty(Map.of(
            "building name", building.getBuildingName()
    ));

    inputValidator.validateMatchesRegex(Map.of(
            "building number", Pair.of(building.getBuildingNumber(), "TODO building number regex"),
            "building identification number", Pair.of(building.getBin(), "TODO bin regex")
    ));

    inputValidator.validateGeographicalLocation(Map.of(
            "building geographical location", building.getGeographicalLocation()
    ));
  }

  //TODO die ganzen methoden evtl in extra klasse (BuildingInputValidator) die vom InputValidator erbt auslagern?

  public void validateRoom(Room room) { //TODO validate numFloors
    inputValidator.validateNotNull(Map.of(
            "room name", room.getRoomName(),
            "room number", room.getRoomNumber(),
            "room identification number", room.getRin(),
            "room parent identification number", room.getParentIn(),
            "room floor", room.getFloor(),
            "room type", room.getRoomType(),
            "room geographical location", room.getGeographicalLocation()
    ));

    inputValidator.validateNotEmpty(Map.of(
            "room name", room.getRoomName(),
            "room number", room.getRoomNumber()
    ));

    inputValidator.validateMatchesRegex(Map.of(
            "room identification number", Pair.of(room.getRin(), "TODO rin regex"),
            "room parent identification number", Pair.of(room.getParentIn(), "TODO bin regex")
    ));

    inputValidator.validateGeographicalLocation(Map.of(
            "room geographical location", room.getGeographicalLocation()
    ));
  }

  public void validateComponent(Component component) {
    inputValidator.validateNotNull(Map.of(
            "component description", component.getComponentDescription(),
            "component identification number", component.getCin(),
            "component parent identification number", component.getParentIn(),
            "component type", component.getComponentType(),
            "component geographical location", component.getGeographicalLocation()
    ));

    inputValidator.validateNotEmpty(Map.of(
            "component description", component.getComponentDescription()
    ));

    inputValidator.validateMatchesRegex(Map.of(
            "component identification number", Pair.of(component.getCin(), "TODO cin regex"),
            "component parent identification number", Pair.of(component.getParentIn(), "TODO bin/rin regex")
    ));

    inputValidator.validateGeographicalLocation(Map.of(
            "component geographical location", component.getGeographicalLocation()
    ));
  }

  public void validateNotification(Notification notification) { //TODO validate notification.getCreationTime().getTime()
    inputValidator.validateNotNull(Map.of(
            "notification title", notification.getNotificationTitle(),
            "notification description", notification.getNotificationDescription(),
            "notification identification number", notification.getNin(),
            "notification parent identification number", notification.getParentIn(),
            "notification creation time", notification.getCreationTime()
    ));

    inputValidator.validateNotEmpty(Map.of(
            "notification title", notification.getNotificationTitle(),
            "notification description", notification.getNotificationDescription()
    ));

    inputValidator.validateMatchesRegex(Map.of(
            "notification identification number", Pair.of(notification.getNin(), "TODO nin regex"),
            "notification parent identification number", Pair.of(notification.getParentIn(), "TODO bin/rin/cin regex")
    ));
  }

  public void validateBin(String bin) {
    inputValidator.validateNotNull(Map.of("building identification number", bin));
    inputValidator.validateNotNull(Map.of("building identification number", Pair.of(bin, "TODO bin regex")));
  }

  public void validateRin(String rin) {
    inputValidator.validateNotNull(Map.of("room identification number", rin));
    inputValidator.validateNotNull(Map.of("room identification number", Pair.of(rin, "TODO rin regex")));
  }

  public void validateCin(String cin) {
    inputValidator.validateNotNull(Map.of("component identification number", cin));
    inputValidator.validateNotNull(Map.of("component identification number", Pair.of(cin, "TODO cin regex")));
  }

  public void validateNin(String nin) {
    inputValidator.validateNotNull(Map.of("notification identification number", nin));
    inputValidator.validateNotNull(Map.of("notification identification number", Pair.of(nin, "TODO nin regex")));
  }

}
