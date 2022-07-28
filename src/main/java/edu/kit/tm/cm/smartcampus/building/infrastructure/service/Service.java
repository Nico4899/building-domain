package edu.kit.tm.cm.smartcampus.building.infrastructure.service;

import edu.kit.tm.cm.smartcampus.building.api.requests.BuildingRequest;
import edu.kit.tm.cm.smartcampus.building.api.requests.ComponentRequest;
import edu.kit.tm.cm.smartcampus.building.api.requests.NotificationRequest;
import edu.kit.tm.cm.smartcampus.building.api.requests.RoomRequest;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.validator.BuildingValidator;
import edu.kit.tm.cm.smartcampus.building.infrastructure.validator.ComponentValidator;
import edu.kit.tm.cm.smartcampus.building.infrastructure.validator.NotificationValidator;
import edu.kit.tm.cm.smartcampus.building.infrastructure.validator.RoomValidator;
import edu.kit.tm.cm.smartcampus.building.infrastructure.validator.Validator;
import edu.kit.tm.cm.smartcampus.building.logic.LogicUtils;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;

/**
 * This class represents the {@link org.springframework.stereotype.Service} of this domain service,
 * it provides all logic and holds {@link Bean} instances of {@link Validator} and
 * {@link CrudRepository}* to manage incoming requests and control sent requests.
 */
@org.springframework.stereotype.Service
public class Service {

  private final BuildingRepository buildingRepository;
  private final RoomRepository roomRepository;
  private final ComponentRepository componentRepository;
  private final NotificationRepository notificationRepository;
  private final BuildingValidator buildingValidator;
  private final RoomValidator roomValidator;
  private final ComponentValidator componentValidator;
  private final NotificationValidator notificationValidator;

  /**
   * Constructs a new service instance for this domain service.
   *
   * @param buildingRepository     repository in which building entities are stored (constructor
   *                               injected)
   * @param roomRepository         repository in which room entities are stored (constructor
   *                               injected)
   * @param componentRepository    repository in which component entities are stored (constructor
   *                               injected)
   * @param notificationRepository repository in which notification entities are stored (constructor
   *                               injected)
   * @param buildingValidator      validator which validates various building related requests
   *                               (constructor injected)
   * @param roomValidator          validator which validates various room related requests
   *                               (constructor injected)
   * @param componentValidator     validator which validates various component related requests
   *                               (constructor injected)
   * @param notificationValidator  validator which validates various notification related requests
   *                               (constructor injected)
   */
  @Autowired
  public Service(BuildingRepository buildingRepository, RoomRepository roomRepository,
      ComponentRepository componentRepository,
      NotificationRepository notificationRepository,
      BuildingValidator buildingValidator, RoomValidator roomValidator,
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

  /**
   * List all {@link Building} this service manages.
   *
   * @return all buildings of this service
   */
  public Collection<Building> listBuildings() {
    Collection<Building> buildings = new ArrayList<>();
    for (Building building : this.buildingRepository.findAll()) {
      buildings.add(building);
    }
    return buildings;
  }

  /**
   * Gets a specific {@link Building} this domain manages.
   *
   * @param identificationNumber the identification number of the requested building
   * @return the requested building
   */
  public Building getBuilding(String identificationNumber) {
    this.buildingValidator.validate(identificationNumber);
    return buildingRepository.findById(identificationNumber).get();
  }

  /**
   * Create a new {@link Building} in this domain service.
   *
   * @param buildingRequest the request for the building to be created
   * @return the created building
   */
  public Building createBuilding(BuildingRequest buildingRequest) {
    this.buildingValidator.validateCreate(buildingRequest);
    return buildingRepository.save(LogicUtils.convertBuildingRequestToBuilding(buildingRequest));
  }

  /**
   * Remove a {@link Building} from this domain service.
   *
   * @param identificationNumber the identification number of the building
   */
  public void removeBuilding(String identificationNumber) {
    this.buildingValidator.validate(identificationNumber);
    buildingRepository.deleteById(identificationNumber);
  }

  /**
   * Update a {@link Building} in this domain service.
   *
   * @param building the building to be updated
   * @return the updated building
   */
  public Building updateBuilding(Building building) {
    this.buildingValidator.validateUpdate(building);
    return buildingRepository.save(building);
  }

  /**
   * List all {@link Notification} that belong to a certain building.
   *
   * @param identificationNumber the identification number of the building
   * @return the requested notifications
   */
  public Collection<Notification> listBuildingNotifications(String identificationNumber) {
    this.buildingValidator.validate(identificationNumber);
    return notificationRepository.findAllBuildingNotifications(identificationNumber);
  }

  /**
   * List all {@link Room} that belong to a certain building.
   *
   * @param identificationNumber the identification number of the building
   * @return the requested rooms
   */
  public Collection<Room> listBuildingRooms(String identificationNumber) {
    this.buildingValidator.validate(identificationNumber);
    return roomRepository.findAllBuildingRooms(identificationNumber);
  }

  /**
   * List all {@link Component} that belong to a certain building.
   *
   * @param identificationNumber the identification number of the building
   * @return the requested components
   */
  public Collection<Component> listBuildingComponents(String identificationNumber) {
    this.buildingValidator.validate(identificationNumber);
    return componentRepository.findAllBuildingComponents(identificationNumber);
  }

  // rooms

  /**
   * Gets a specific {@link Room} this domain manages.
   *
   * @param identificationNumber the identification number of the requested room
   * @return the requested room
   */
  public Room getRoom(String identificationNumber) {
    this.roomValidator.validate(identificationNumber);
    return this.roomRepository.findById(identificationNumber).get();
  }

  /**
   * Create a new {@link Room} in this domain service.
   *
   * @param roomRequest the roomRequest for the room to be created
   * @return the created room
   */
  public Room createRoom(RoomRequest roomRequest) {
    this.roomValidator.validateCreate(roomRequest);
    Room room = LogicUtils.convertRoomRequestToRoom(roomRequest);
    //TODO was ist schöner? das hier machen oder in der Utils klasse?
    room.setParentBuilding(
        buildingRepository.findById(roomRequest.getParentIdentificationNumber()).get());
    return this.roomRepository.save(room);
  }

  /**
   * Update a {@link Room} in this domain service.
   *
   * @param room the room to be updated
   * @return the updated room
   */
  public Room updateRoom(Room room) {
    this.roomValidator.validateUpdate(room);
    return this.roomRepository.save(room);
  }

  /**
   * Remove a {@link Room} from this domain service.
   *
   * @param identificationNumber the identification number of the room
   */
  public void removeRoom(String identificationNumber) {
    this.roomValidator.validate(identificationNumber);
    roomRepository.deleteById(identificationNumber);
    this.cleanUpRoom(identificationNumber);
  }

  /**
   * List all {@link Component} that belong to a certain room.
   *
   * @param identificationNumber the identification number of the room
   * @return the requested components
   */
  public Collection<Component> listRoomComponents(String identificationNumber) {
    this.roomValidator.validate(identificationNumber);
    return componentRepository.findAllRoomComponents(identificationNumber);
  }

  /**
   * List all {@link Notification} that belong to a certain room.
   *
   * @param identificationNumber the identification number of the room
   * @return the requested notifications
   */
  public Collection<Notification> listRoomNotifications(String identificationNumber) {
    this.roomValidator.validate(identificationNumber);
    return notificationRepository.findAllRoomNotifications(identificationNumber);
  }

  // components

  /**
   * Create a new {@link Component} in this domain service.
   *
   * @param componentRequest the componentRequest for the component to be created
   * @return the created component
   */
  public Component createComponent(ComponentRequest componentRequest) {
    this.componentValidator.validateCreate(componentRequest);
    return componentRepository.save(
        LogicUtils.convertComponentRequestToComponent(componentRequest));
  }

  /**
   * Gets a specific {@link Component} this domain manages.
   *
   * @param identificationNumber the identification number of the requested component
   * @return the requested component
   */
  public Component getComponent(String identificationNumber) {
    this.componentValidator.validate(identificationNumber);
    return componentRepository.findById(identificationNumber).get();
  }

  /**
   * Update a {@link Component} in this domain service.
   *
   * @param component the component to be updated
   * @return the updated component
   */
  public Component updateComponent(Component component) {
    this.componentValidator.validateUpdate(component);
    return componentRepository.save(component);
  }

  /**
   * Remove a {@link Component} from this domain service.
   *
   * @param identificationNumber the identification number of the component
   */
  public void removeComponent(String identificationNumber) {
    this.componentValidator.validate(identificationNumber);
    componentRepository.deleteById(identificationNumber);
    this.cleanUpComponent(identificationNumber);
  }

  /**
   * List all {@link Notification} that belong to a certain component.
   *
   * @param identificationNumber the identification number of the component
   * @return the requested notifications
   */
  public Collection<Notification> listComponentNotifications(String identificationNumber) {
    this.componentValidator.validate(identificationNumber);
    return notificationRepository.findAllComponentNotifications(identificationNumber);
  }

  // notifications

  /**
   * Gets a specific {@link Notification} this domain manages.
   *
   * @param identificationNumber the identification number of the requested notification
   * @return the requested notification
   */
  public Notification getNotification(String identificationNumber) {
    this.notificationValidator.validate(identificationNumber);
    return notificationRepository.findById(identificationNumber).get();
  }

  /**
   * Update a {@link Notification} in this domain service.
   *
   * @param notification the notification to be updated
   * @return the updated notification
   */
  public Notification updateNotification(Notification notification) {
    this.notificationValidator.validateUpdate(notification);
    return notificationRepository.save(notification);
  }

  /**
   * Create a new {@link Notification} in this domain service.
   *
   * @param notificationRequest the notificationRequest for the notification to be created
   * @return the created notification
   */
  public Notification createNotification(NotificationRequest notificationRequest) {
    this.notificationValidator.validateCreate(notificationRequest);
    Notification notification = this.notificationRepository.save(
        LogicUtils.convertNotificationRequestToNotification(notificationRequest));
    notification.setCreationTime(new Timestamp(System.currentTimeMillis()));
    return notification;
  }

  /**
   * Remove a {@link Notification} from this domain service.
   *
   * @param identificationNumber the identification number of the notification
   */
  public void removeNotification(String identificationNumber) {
    this.notificationValidator.validate(identificationNumber);
    notificationRepository.deleteById(identificationNumber);
  }

  /**
   * Cleans a building up. That means: deletes all rooms, components and notifications that belong
   * to a specified building.
   *
   * @param identificationNumber the identification number of the building
   */
  private void cleanUpBuilding(String identificationNumber) {
    roomRepository.cleanUp(identificationNumber);
    componentRepository.cleanUp(identificationNumber);
    notificationRepository.cleanUp(identificationNumber);
  }

  /**
   * Cleans a room up. That means: deletes all components and notifications that belong to a
   * specified room.
   *
   * @param identificationNumber the identification number of the room.
   */
  private void cleanUpRoom(String identificationNumber) {
    componentRepository.cleanUp(identificationNumber);
    notificationRepository.cleanUp(identificationNumber);
  }

  /**
   * Cleans a component up. That means: deletes all notifications that belong to a specified
   * component.
   *
   * @param identificationNumber the identification number of the component
   */
  private void cleanUpComponent(String identificationNumber) {
    notificationRepository.cleanUp(identificationNumber);
  }

}
