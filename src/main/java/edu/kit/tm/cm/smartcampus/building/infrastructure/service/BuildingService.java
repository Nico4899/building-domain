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
    serviceValidator.validateInIsMapped(buildingRepository, bin);
    return buildingRepository.findById(bin).get();
  }

  public Building createBuilding(Building building) {
    serviceValidator.validateInDoesNotExist(buildingRepository, building.getBin());
    return buildingRepository.save(building);
  }

  public void removeBuilding(String bin) {
    serviceValidator.validateInIsMapped(buildingRepository, bin);
    buildingRepository.deleteById(bin);
  }

  public Building updateBuilding(Building building) {
    serviceValidator.validateInIsMapped(buildingRepository, building.getBin());
    return buildingRepository.save(building);
  }

  public Collection<Notification> listBuildingNotifications(String bin) {
    serviceValidator.validateInIsMapped(buildingRepository, bin);
    return notificationRepository.findAllBuildingNotifications(bin);
  }

  public Collection<Room> listBuildingRooms(String bin) {
    serviceValidator.validateInIsMapped(buildingRepository, bin);
    return roomRepository.findAllBuildingRooms(bin);
  }

  public Collection<Component> listBuildingComponents(String bin) {
    serviceValidator.validateInIsMapped(buildingRepository, bin);
    return componentRepository.findAllBuildingComponents(bin);
  }

  // rooms
  public Room getRoom(String rin) throws ResourceNotFoundException {
    serviceValidator.validateInIsMapped(roomRepository, rin);
    throw new ResourceNotFoundException(String.format(NOT_FOUND, rin));
  }

  public Room createRoom(Room room) {
    serviceValidator.validateInDoesNotExist(roomRepository, room.getRin());
    serviceValidator.validateReferencedId(room.getRin(), room.getParentIn());
    return this.roomRepository.save(room);
  }

  public Room updateRoom(Room room) {
    serviceValidator.validateInIsMapped(roomRepository, room.getRin());
    serviceValidator.validateReferencedId(room.getRin(), room.getParentIn());
    return this.roomRepository.save(room);
  }

  public void removeRoom(String rin) {
    serviceValidator.validateInIsMapped(roomRepository, rin);
    roomRepository.deleteById(rin);
  }

  public Collection<Component> listRoomComponents(String rin) {
    serviceValidator.validateInIsMapped(roomRepository, rin);
    return componentRepository.findAllRoomComponents(rin);
  }

  public Collection<Notification> listRoomNotifications(String rin) {
    serviceValidator.validateInIsMapped(roomRepository, rin);
    return notificationRepository.findAllRoomNotifications(rin);
  }

  // components
  public Component createComponent(Component component) {
    serviceValidator.validateInDoesNotExist(componentRepository, component.getCin());
    serviceValidator.validateReferencedId(component.getCin(), component.getParentIn());
    return componentRepository.save(component);
  }

  public Component getComponent(String cin) {
    serviceValidator.validateInIsMapped(componentRepository, cin);
    return componentRepository.findById(cin).get();
  }

  public Component updateComponent(Component component) {
    serviceValidator.validateInIsMapped(componentRepository, component.getCin());
    serviceValidator.validateReferencedId(component.getCin(), component.getParentIn());
    return componentRepository.save(component);
  }

  public void removeComponent(String cin) {
    serviceValidator.validateInIsMapped(componentRepository, cin);
    componentRepository.deleteById(cin);
  }

  public Collection<Notification> listComponentNotifications(String cin) {
    serviceValidator.validateInIsMapped(componentRepository, cin);
    return notificationRepository.findAllComponentNotifications(cin);
  }

  // notifications
  public Notification getNotification(String nin) {
  serviceValidator.validateInIsMapped(notificationRepository, nin);
    return notificationRepository.findById(nin).orElse(null);
  }

  public Notification updateNotification(Notification notification) {
    serviceValidator.validateInIsMapped(notificationRepository, notification.getNin());
    return notificationRepository.save(notification);
  }

  public Notification createNotification(Notification notification) {
    serviceValidator.validateInDoesNotExist(notificationRepository, notification.getNin());
    serviceValidator.validateReferencedId(notification.getNin(), notification.getParentIn());
    return notificationRepository.save(notification);
  }

  public void removeNotification(String nin) {
    serviceValidator.validateInIsMapped(notificationRepository, nin);
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
