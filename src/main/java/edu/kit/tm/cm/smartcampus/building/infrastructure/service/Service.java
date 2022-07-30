package edu.kit.tm.cm.smartcampus.building.infrastructure.service;

import edu.kit.tm.cm.smartcampus.building.api.controller.building.dto.ServerCreateBuildingRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.building.dto.ServerUpdateBuildingRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.component.dto.ServerCreateComponentRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.component.dto.ServerUpdateComponentRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.notification.dto.ServerCreateNotificationRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.notification.dto.ServerUpdateNotificationRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.room.dto.ServerCreateRoomRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.room.dto.ServerUpdateRoomRequest;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.building.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.component.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.notification.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.room.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.Validator;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.building.BuildingRequestValidator;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.component.ComponentValidator;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.notification.NotificationValidator;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.room.RoomValidator;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import edu.kit.tm.cm.smartcampus.building.logic.operations.utility.DataTransferUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class represents the {@link org.springframework.stereotype.Service} of this domain service,
 * it provides all logic and holds {@link Bean} instances of {@link Validator} and {@link
 * CrudRepository}* to manage incoming requests and control sent requests.
 */
@org.springframework.stereotype.Service
public class Service {

  private final BuildingRepository buildingRepository;
  private final RoomRepository roomRepository;
  private final ComponentRepository componentRepository;
  private final NotificationRepository notificationRepository;
  private final BuildingRequestValidator buildingRequestValidator;
  private final RoomValidator roomValidator;
  private final ComponentValidator componentValidator;
  private final NotificationValidator notificationValidator;

  /**
   * Constructs a new service instance for this domain service.
   *
   * @param buildingRepository repository in which building entities are stored (constructor
   *     injected)
   * @param roomRepository repository in which room entities are stored (constructor injected)
   * @param componentRepository repository in which component entities are stored (constructor
   *     injected)
   * @param notificationRepository repository in which notification entities are stored (constructor
   *     injected)
   * @param buildingRequestValidator validator which validates various building related requests
   *     (constructor injected)
   * @param roomValidator validator which validates various room related requests (constructor
   *     injected)
   * @param componentValidator validator which validates various component related requests
   *     (constructor injected)
   * @param notificationValidator validator which validates various notification related requests
   *     (constructor injected)
   */
  @Autowired
  public Service(
      BuildingRepository buildingRepository,
      RoomRepository roomRepository,
      ComponentRepository componentRepository,
      NotificationRepository notificationRepository,
      BuildingRequestValidator buildingRequestValidator,
      RoomValidator roomValidator,
      ComponentValidator componentValidator,
      NotificationValidator notificationValidator) {
    this.buildingRepository = buildingRepository;
    this.roomRepository = roomRepository;
    this.componentRepository = componentRepository;
    this.notificationRepository = notificationRepository;
    this.buildingRequestValidator = buildingRequestValidator;
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
    this.buildingRequestValidator.validate(identificationNumber);
    return buildingRepository.findById(identificationNumber).get();
  }

  /**
   * Create a new {@link Building} in this domain service.
   *
   * @param serverCreateBuildingRequest the request for the building to be created
   * @return the created building
   */
  public Building createBuilding(ServerCreateBuildingRequest serverCreateBuildingRequest) {
    this.buildingRequestValidator.validateCreate(serverCreateBuildingRequest);
    return buildingRepository.save(
        DataTransferUtils.ServerRequestReader.readServerCreateBuildingRequest(
            serverCreateBuildingRequest));
  }

  /**
   * Remove a {@link Building} from this domain service.
   *
   * @param identificationNumber the identification number of the building
   */
  public void removeBuilding(String identificationNumber) {
    this.buildingRequestValidator.validate(identificationNumber);
    buildingRepository.deleteById(identificationNumber);
    this.cleanUpBuilding(identificationNumber);
  }

  /**
   * Update a {@link Building} in this domain service.
   *
   * @param serverUpdateBuildingRequest the building to be updated
   * @return the updated building
   */
  public Building updateBuilding(ServerUpdateBuildingRequest serverUpdateBuildingRequest) {
    this.buildingRequestValidator.validateUpdate(serverUpdateBuildingRequest);
    Building building =
        DataTransferUtils.ServerRequestReader.readServerUpdateBuildingRequest(
            serverUpdateBuildingRequest);
    return buildingRepository.save(building);
  }

  /**
   * List all {@link Notification} that belong to a certain building.
   *
   * @param identificationNumber the identification number of the building
   * @return the requested notifications
   */
  public Collection<Notification> listNotifications(String identificationNumber) {
    this.buildingRequestValidator.validate(identificationNumber);
    return notificationRepository.findAllNotifications(identificationNumber);
  }

  /**
   * List all {@link Room} that belong to a certain building.
   *
   * @param identificationNumber the identification number of the building
   * @return the requested rooms
   */
  public Collection<Room> listRooms(String identificationNumber) {
    this.buildingRequestValidator.validate(identificationNumber);
    return roomRepository.findAllRooms(identificationNumber);
  }

  /**
   * List all {@link Component} that belong to a certain building.
   *
   * @param identificationNumber the identification number of the building
   * @return the requested components
   */
  public Collection<Component> listComponents(String identificationNumber) {
    this.buildingRequestValidator.validate(identificationNumber);
    return componentRepository.findAllComponents(identificationNumber);
  }

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
   * @param serverCreateRoomRequest the roomRequest for the room to be created
   * @return the created room
   */
  public Room createRoom(ServerCreateRoomRequest serverCreateRoomRequest) {
    this.roomValidator.validateCreate(serverCreateRoomRequest);
    Room room =
        DataTransferUtils.ServerRequestReader.readServerCreateRoomRequest(serverCreateRoomRequest);
    return this.roomRepository.save(room);
  }

  /**
   * Update a {@link Room} in this domain service.
   *
   * @param serverUpdateRoomRequest the room to be updated
   * @return the updated room
   */
  public Room updateRoom(ServerUpdateRoomRequest serverUpdateRoomRequest) {
    this.roomValidator.validateUpdate(serverUpdateRoomRequest);
    Room room =
        DataTransferUtils.ServerRequestReader.readServerUpdateRoomRequest(serverUpdateRoomRequest);
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
   * Create a new {@link Component} in this domain service.
   *
   * @param serverCreateComponentRequest the componentRequest for the component to be created
   * @return the created component
   */
  public Component createComponent(ServerCreateComponentRequest serverCreateComponentRequest) {
    this.componentValidator.validateCreate(serverCreateComponentRequest);
    return componentRepository.save(
        DataTransferUtils.ServerRequestReader.readServerCreateComponentRequest(
            serverCreateComponentRequest));
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
   * @param serverUpdateComponentRequest the component to be updated
   * @return the updated component
   */
  public Component updateComponent(ServerUpdateComponentRequest serverUpdateComponentRequest) {
    this.componentValidator.validateUpdate(serverUpdateComponentRequest);
    Component component =
        DataTransferUtils.ServerRequestReader.readServerUpdateComponentRequest(
            serverUpdateComponentRequest);
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
   * @param serverUpdateNotificationRequest the notification to be updated
   * @return the updated notification
   */
  public Notification updateNotification(ServerUpdateNotificationRequest serverUpdateNotificationRequest) {
    this.notificationValidator.validateUpdate(serverUpdateNotificationRequest);
    Notification notification =
        DataTransferUtils.ServerRequestReader.readServerUpdateNotificationRequest(
            serverUpdateNotificationRequest);
    return notificationRepository.save(notification);
  }

  /**
   * Create a new {@link Notification} in this domain service.
   *
   * @param serverCreateNotificationRequest the notificationRequest for the notification to be
   *     created
   * @return the created notification
   */
  public Notification createNotification(
      ServerCreateNotificationRequest serverCreateNotificationRequest) {
    this.notificationValidator.validateCreate(serverCreateNotificationRequest);
    Notification notification =
        this.notificationRepository.save(
            DataTransferUtils.ServerRequestReader.readServerCreateNotificationRequest(
                serverCreateNotificationRequest));
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

  private void cleanUpBuilding(String buildingIdentificationNumber) {
    //Get all rooms belonging to building
    Collection<Room> roomsOfBuilding = roomRepository.findAllRooms(buildingIdentificationNumber);
    //Delete rooms and everything attached
    for (Room room : roomsOfBuilding) {
      cleanUpRoom(room.getIdentificationNumber());
    }
    //Delete components of Building and attached notifications
    componentRepository.cleanUp(buildingIdentificationNumber);
    //Delete notifications of Building
    notificationRepository.cleanUp(buildingIdentificationNumber);
  }

  private void cleanUpRoom(String roomIdentificationNumber) {
    //Get all components belonging to room
    Collection<Component> componentsOfRoom = componentRepository.findAllComponents(roomIdentificationNumber);
    //Delete notifications belonging to components
    for (Component component : componentsOfRoom) {
      cleanUpComponent(component.getIdentificationNumber());
    }
    //Delete notifications belonging to room
    notificationRepository.cleanUp(roomIdentificationNumber);
    //Delete components belonging to room
    componentRepository.cleanUp(roomIdentificationNumber);
  }

  private void cleanUpComponent(String identificationNumber) {
    notificationRepository.cleanUp(identificationNumber);
  }
}
