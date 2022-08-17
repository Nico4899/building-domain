package edu.kit.tm.cm.smartcampus.building.infrastructure.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;

import edu.kit.tm.cm.smartcampus.building.TestUtils;
import edu.kit.tm.cm.smartcampus.building.api.controller.FloorsDTO;
import edu.kit.tm.cm.smartcampus.building.api.controller.GeographicalLocationDTO;
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
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.building.BuildingValidator;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.component.ComponentValidator;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.notification.NotificationValidator;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.room.RoomValidator;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building.CampusLocation;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Floors;
import edu.kit.tm.cm.smartcampus.building.logic.model.GeographicalLocation;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room.Type;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServiceTests {

  //Attributes
  //-building
  private static final String BUILDING_IDENTIFICATION_NUMBER = "b-1";
  private static final String BUILDING_NAME = "Testgebäude";
  private static final String BUILDING_NUMBER = "12.12";
  private static final CampusLocation BUILDING_CAMPUS_LOCATION = CampusLocation.SOUTH_CAMPUS;
  private static final GeographicalLocation BUILDING_GEOGRAPHICAL_LOCATION =
      new GeographicalLocation();
  private static final Floors BUILDING_FLOORS = new Floors();
  private static final double BUILDING_GEOGRAPHICAL_LOCATION_LATITUDE = 12.3456;
  private static final double BUILDING_GEOGRAPHICAL_LOCATION_LONGITUDE = 43.21987;
  private static final int BUILDING_FLOORS_HIGHEST_FLOOR = 12;
  private static final int BUILDING_FLOORS_LOWEST_FLOOR = -1;
  private static final GeographicalLocationDTO BUILDING_GEOGRAPHICAL_LOCATION_DTO =
      new GeographicalLocationDTO(BUILDING_GEOGRAPHICAL_LOCATION_LATITUDE,
          BUILDING_GEOGRAPHICAL_LOCATION_LONGITUDE);
  private static final FloorsDTO BUILDING_FLOORS_DTO = new FloorsDTO(BUILDING_FLOORS_HIGHEST_FLOOR,
      BUILDING_FLOORS_LOWEST_FLOOR);
  private static final String UPDATED_BUILDING_NAME = "Testgebäude v2";
  private static final String UPDATED_BUILDING_NUMBER = "12.55";
  private static final CampusLocation UPDATED_BUILDING_CAMPUS_LOCATION = CampusLocation.WEST_CAMPUS;
  private static final GeographicalLocation UPDATED_BUILDING_GEOGRAPHICAL_LOCATION =
      new GeographicalLocation();
  private static final Floors UPDATED_BUILDING_FLOORS = new Floors();
  private static final double UPDATED_BUILDING_GEOGRAPHICAL_LOCATION_LATITUDE = 22.3456;
  private static final double UPDATED_BUILDING_GEOGRAPHICAL_LOCATION_LONGITUDE = 23.21987;
  private static final int UPDATED_BUILDING_FLOORS_HIGHEST_FLOOR = 10;
  private static final int UPDATED_BUILDING_FLOORS_LOWEST_FLOOR = -3;
  private static final GeographicalLocationDTO UPDATED_BUILDING_GEOGRAPHICAL_LOCATION_DTO =
      new GeographicalLocationDTO(UPDATED_BUILDING_GEOGRAPHICAL_LOCATION_LATITUDE,
          UPDATED_BUILDING_GEOGRAPHICAL_LOCATION_LONGITUDE);
  private static final FloorsDTO UPDATED_BUILDING_FLOORS_DTO = new FloorsDTO(
      UPDATED_BUILDING_FLOORS_HIGHEST_FLOOR,
      UPDATED_BUILDING_FLOORS_LOWEST_FLOOR);
  //-room
  private static final String ROOM_IDENTIFICATION_NUMBER = "r-1";
  private static final String ROOM_NAME = "Testraum";
  private static final String ROOM_NUMBER = "123";
  private static final int ROOM_FLOOR = 0;
  private static final Room.Type ROOM_TYPE = Type.SEMINAR_ROOM;
  private static final String ROOM_PARENT_BUILDING_IDENTIFICATION_NUMBER = BUILDING_IDENTIFICATION_NUMBER;
  private static final GeographicalLocation ROOM_GEOGRAPHICAL_LOCATION =
      new GeographicalLocation();
  private static final double ROOM_GEOGRAPHICAL_LOCATION_LATITUDE = 12.34561;
  private static final double ROOM_GEOGRAPHICAL_LOCATION_LONGITUDE = 43.21;
  private static final GeographicalLocationDTO ROOM_GEOGRAPHICAL_LOCATION_DTO =
      new GeographicalLocationDTO(ROOM_GEOGRAPHICAL_LOCATION_LATITUDE,
          ROOM_GEOGRAPHICAL_LOCATION_LONGITUDE);
  private static final String UPDATED_ROOM_NAME = "Testraum v2";
  private static final String UPDATED_ROOM_NUMBER = "13";
  private static final int UPDATED_ROOM_FLOOR = 3;
  private static final Room.Type UPDATED_ROOM_TYPE = Type.SEMINAR_ROOM;
  private static final String UPDATED_ROOM_PARENT_BUILDING_IDENTIFICATION_NUMBER = BUILDING_IDENTIFICATION_NUMBER;
  private static final GeographicalLocation UPDATED_ROOM_GEOGRAPHICAL_LOCATION =
      new GeographicalLocation();
  private static final double UPDATED_ROOM_GEOGRAPHICAL_LOCATION_LATITUDE = 1.34561;
  private static final double UPDATED_ROOM_GEOGRAPHICAL_LOCATION_LONGITUDE = 43.21112;
  private static final GeographicalLocationDTO UPDATED_ROOM_GEOGRAPHICAL_LOCATION_DTO =
      new GeographicalLocationDTO(UPDATED_ROOM_GEOGRAPHICAL_LOCATION_LATITUDE,
          UPDATED_ROOM_GEOGRAPHICAL_LOCATION_LONGITUDE);
  //-component
  private static final String COMPONENT_IDENTIFICATION_NUMBER = "c-1";
  private static final Component.Type COMPONENT_TYPE = Component.Type.STAIRS;
  private static final String COMPONENT_DESCRIPTION = "Eine test Treppe.";
  private static final double COMPONENT_GEOGRAPHICAL_LOCATION_LATITUDE = 12.34561;
  private static final double COMPONENT_GEOGRAPHICAL_LOCATION_LONGITUDE = 43.21;
  private static final GeographicalLocationDTO COMPONENT_GEOGRAPHICAL_LOCATION_DTO =
      new GeographicalLocationDTO(COMPONENT_GEOGRAPHICAL_LOCATION_LATITUDE,
          COMPONENT_GEOGRAPHICAL_LOCATION_LONGITUDE);
  private static final String COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER = BUILDING_IDENTIFICATION_NUMBER;
  private static final GeographicalLocation COMPONENT_GEOGRAPHICAL_LOCATION =
      new GeographicalLocation();
  private static final Component.Type UPDATED_COMPONENT_TYPE = Component.Type.ELEVATOR;
  private static final String UPDATED_COMPONENT_DESCRIPTION = "Eine test Treppe v2.";
  private static final double UPDATED_COMPONENT_GEOGRAPHICAL_LOCATION_LATITUDE = 1.34561;
  private static final double UPDATED_COMPONENT_GEOGRAPHICAL_LOCATION_LONGITUDE = 3.21;
  private static final GeographicalLocationDTO UPDATED_COMPONENT_GEOGRAPHICAL_LOCATION_DTO =
      new GeographicalLocationDTO(UPDATED_COMPONENT_GEOGRAPHICAL_LOCATION_LATITUDE,
          UPDATED_COMPONENT_GEOGRAPHICAL_LOCATION_LONGITUDE);
  private static final String UPDATED_COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER = BUILDING_IDENTIFICATION_NUMBER;
  private static final GeographicalLocation UPDATED_COMPONENT_GEOGRAPHICAL_LOCATION =
      new GeographicalLocation();
  //-notification
  private static final String NOTIFICATION_IDENTIFICATION_NUMBER = "n-1";
  private static final String NOTIFICATION_PARENT_COMPONENT_IDENTIFICATION_NUMBER =
      COMPONENT_IDENTIFICATION_NUMBER;
  private static final String NOTIFICATION_TITLE = "Eine test Notification.";
  private static final String NOTIFICATION_DESCRIPTION = "Eine test Beschreibung, die sehr "
      + "aussagekräftig ist 0_0.";
  private static final String UPDATED_NOTIFICATION_PARENT_COMPONENT_IDENTIFICATION_NUMBER =
      COMPONENT_IDENTIFICATION_NUMBER;
  private static final String UPDATED_NOTIFICATION_TITLE = "Eine test Notification v2.";
  private static final String UPDATED_NOTIFICATION_DESCRIPTION = "Eine test Beschreibung, die sehr "
      + "aussagekräftig ist 0_0. v2";
  //Mocks
  private static final BuildingRepository BUILDING_REPOSITORY = mock(BuildingRepository.class);
  private static final RoomRepository ROOM_REPOSITORY = mock(RoomRepository.class);
  private static final ComponentRepository COMPONENT_REPOSITORY = mock(ComponentRepository.class);
  private static final NotificationRepository NOTIFICATION_REPOSITORY = mock(
      NotificationRepository.class);
  private static final BuildingValidator BUILDING_VALIDATOR = mock(BuildingValidator.class);
  private static final RoomValidator ROOM_VALIDATOR = mock(RoomValidator.class);
  private static final ComponentValidator COMPONENT_VALIDATOR = mock(ComponentValidator.class);
  private static final NotificationValidator NOTIFICATION_VALIDATOR = mock(
      NotificationValidator.class);
  private static final Service SERVICE = new Service(BUILDING_REPOSITORY, ROOM_REPOSITORY,
      COMPONENT_REPOSITORY, NOTIFICATION_REPOSITORY, BUILDING_VALIDATOR, ROOM_VALIDATOR,
      COMPONENT_VALIDATOR, NOTIFICATION_VALIDATOR);
  //Requests
  private static final ServerCreateBuildingRequest SERVER_CREATE_BUILDING_REQUEST =
      new ServerCreateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER, BUILDING_CAMPUS_LOCATION,
          BUILDING_GEOGRAPHICAL_LOCATION_DTO, BUILDING_FLOORS_DTO);
  private static final ServerCreateRoomRequest SERVER_CREATE_ROOM_REQUEST =
      new ServerCreateRoomRequest(ROOM_PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
          ROOM_NUMBER, ROOM_FLOOR, ROOM_TYPE, ROOM_GEOGRAPHICAL_LOCATION_DTO);
  private static final ServerCreateComponentRequest SERVER_CREATE_COMPONENT_REQUEST =
      new ServerCreateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
          COMPONENT_TYPE, COMPONENT_DESCRIPTION, COMPONENT_GEOGRAPHICAL_LOCATION_DTO);
  private static final ServerCreateNotificationRequest SERVER_CREATE_NOTIFICATION_REQUEST =
      new ServerCreateNotificationRequest(NOTIFICATION_PARENT_COMPONENT_IDENTIFICATION_NUMBER,
          NOTIFICATION_TITLE, NOTIFICATION_DESCRIPTION);
  private static final ServerUpdateBuildingRequest SERVER_UPDATE_BUILDING_REQUEST =
      new ServerUpdateBuildingRequest(UPDATED_BUILDING_NAME, UPDATED_BUILDING_NUMBER,
          UPDATED_BUILDING_CAMPUS_LOCATION, UPDATED_BUILDING_GEOGRAPHICAL_LOCATION_DTO,
          UPDATED_BUILDING_FLOORS_DTO, BUILDING_IDENTIFICATION_NUMBER);
  private static final ServerUpdateRoomRequest SERVER_UPDATE_ROOM_REQUEST =
      new ServerUpdateRoomRequest(UPDATED_ROOM_PARENT_BUILDING_IDENTIFICATION_NUMBER,
          UPDATED_ROOM_NAME, UPDATED_ROOM_NUMBER, UPDATED_ROOM_FLOOR, UPDATED_ROOM_TYPE,
          UPDATED_ROOM_GEOGRAPHICAL_LOCATION_DTO, ROOM_IDENTIFICATION_NUMBER);
  private static final ServerUpdateComponentRequest SERVER_UPDATE_COMPONENT_REQUEST =
      new ServerUpdateComponentRequest(UPDATED_COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
          UPDATED_COMPONENT_TYPE, UPDATED_COMPONENT_DESCRIPTION,
          UPDATED_COMPONENT_GEOGRAPHICAL_LOCATION_DTO, COMPONENT_IDENTIFICATION_NUMBER);
  private static final ServerUpdateNotificationRequest SERVER_UPDATE_NOTIFICATION_REQUEST =
      new ServerUpdateNotificationRequest(
          UPDATED_NOTIFICATION_PARENT_COMPONENT_IDENTIFICATION_NUMBER, UPDATED_NOTIFICATION_TITLE,
          UPDATED_NOTIFICATION_DESCRIPTION, NOTIFICATION_IDENTIFICATION_NUMBER);
  //Instances
  private static final Building BUILDING = new Building();
  private static final Room ROOM = new Room();
  private static final Component COMPONENT = new Component();
  private static final Notification NOTIFICATION = new Notification();
  private static final Building UPDATED_BUILDING = new Building();
  private static final Room UPDATED_ROOM = new Room();
  private static final Component UPDATED_COMPONENT = new Component();
  private static final Notification UPDATED_NOTIFICATION = new Notification();

  @BeforeAll
  public static void setUp() {
    //initialize building(s)
    BUILDING_FLOORS.setHighestFloor(BUILDING_FLOORS_HIGHEST_FLOOR);
    BUILDING_FLOORS.setLowestFloor(BUILDING_FLOORS_LOWEST_FLOOR);
    BUILDING_GEOGRAPHICAL_LOCATION.setLatitude(BUILDING_GEOGRAPHICAL_LOCATION_LATITUDE);
    BUILDING_GEOGRAPHICAL_LOCATION.setLongitude(BUILDING_GEOGRAPHICAL_LOCATION_LONGITUDE);

    BUILDING.setIdentificationNumber(BUILDING_IDENTIFICATION_NUMBER);
    BUILDING.setName(BUILDING_NAME);
    BUILDING.setNumber(BUILDING_NUMBER);
    BUILDING.setFloors(BUILDING_FLOORS);
    BUILDING.setGeographicalLocation(BUILDING_GEOGRAPHICAL_LOCATION);
    BUILDING.setCampusLocation(BUILDING_CAMPUS_LOCATION);

    UPDATED_BUILDING_FLOORS.setHighestFloor(UPDATED_BUILDING_FLOORS_HIGHEST_FLOOR);
    UPDATED_BUILDING_FLOORS.setLowestFloor(UPDATED_BUILDING_FLOORS_LOWEST_FLOOR);
    UPDATED_BUILDING_GEOGRAPHICAL_LOCATION.setLatitude(
        UPDATED_BUILDING_GEOGRAPHICAL_LOCATION_LATITUDE);
    UPDATED_BUILDING_GEOGRAPHICAL_LOCATION.setLongitude(
        UPDATED_BUILDING_GEOGRAPHICAL_LOCATION_LONGITUDE);

    UPDATED_BUILDING.setIdentificationNumber(BUILDING_IDENTIFICATION_NUMBER);
    UPDATED_BUILDING.setName(UPDATED_BUILDING_NAME);
    UPDATED_BUILDING.setNumber(UPDATED_BUILDING_NUMBER);
    UPDATED_BUILDING.setFloors(UPDATED_BUILDING_FLOORS);
    UPDATED_BUILDING.setGeographicalLocation(UPDATED_BUILDING_GEOGRAPHICAL_LOCATION);
    UPDATED_BUILDING.setCampusLocation(UPDATED_BUILDING_CAMPUS_LOCATION);

    //initialize room(s)
    ROOM_GEOGRAPHICAL_LOCATION.setLongitude(ROOM_GEOGRAPHICAL_LOCATION_LONGITUDE);
    ROOM_GEOGRAPHICAL_LOCATION.setLatitude(ROOM_GEOGRAPHICAL_LOCATION_LATITUDE);

    ROOM.setIdentificationNumber(ROOM_IDENTIFICATION_NUMBER);
    ROOM.setName(ROOM_NAME);
    ROOM.setNumber(ROOM_NUMBER);
    ROOM.setFloor(ROOM_FLOOR);
    ROOM.setParentIdentificationNumber(ROOM_PARENT_BUILDING_IDENTIFICATION_NUMBER);
    ROOM.setType(ROOM_TYPE);
    ROOM.setGeographicalLocation(ROOM_GEOGRAPHICAL_LOCATION);

    UPDATED_ROOM_GEOGRAPHICAL_LOCATION.setLongitude(UPDATED_ROOM_GEOGRAPHICAL_LOCATION_LONGITUDE);
    UPDATED_ROOM_GEOGRAPHICAL_LOCATION.setLatitude(UPDATED_ROOM_GEOGRAPHICAL_LOCATION_LATITUDE);

    UPDATED_ROOM.setIdentificationNumber(ROOM_IDENTIFICATION_NUMBER);
    UPDATED_ROOM.setName(UPDATED_ROOM_NAME);
    UPDATED_ROOM.setNumber(UPDATED_ROOM_NUMBER);
    UPDATED_ROOM.setFloor(UPDATED_ROOM_FLOOR);
    UPDATED_ROOM.setParentIdentificationNumber(UPDATED_ROOM_PARENT_BUILDING_IDENTIFICATION_NUMBER);
    UPDATED_ROOM.setType(UPDATED_ROOM_TYPE);
    UPDATED_ROOM.setGeographicalLocation(UPDATED_ROOM_GEOGRAPHICAL_LOCATION);

    //initialize component(s)
    COMPONENT_GEOGRAPHICAL_LOCATION.setLatitude(COMPONENT_GEOGRAPHICAL_LOCATION_LATITUDE);
    COMPONENT_GEOGRAPHICAL_LOCATION.setLongitude(COMPONENT_GEOGRAPHICAL_LOCATION_LONGITUDE);

    COMPONENT.setIdentificationNumber(COMPONENT_IDENTIFICATION_NUMBER);
    COMPONENT.setType(COMPONENT_TYPE);
    COMPONENT.setDescription(COMPONENT_DESCRIPTION);
    COMPONENT.setGeographicalLocation(COMPONENT_GEOGRAPHICAL_LOCATION);
    COMPONENT.setParentIdentificationNumber(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER);

    UPDATED_COMPONENT_GEOGRAPHICAL_LOCATION.setLatitude(
        UPDATED_COMPONENT_GEOGRAPHICAL_LOCATION_LATITUDE);
    UPDATED_COMPONENT_GEOGRAPHICAL_LOCATION.setLongitude(
        UPDATED_COMPONENT_GEOGRAPHICAL_LOCATION_LONGITUDE);

    UPDATED_COMPONENT.setIdentificationNumber(COMPONENT_IDENTIFICATION_NUMBER);
    UPDATED_COMPONENT.setType(UPDATED_COMPONENT_TYPE);
    UPDATED_COMPONENT.setDescription(UPDATED_COMPONENT_DESCRIPTION);
    UPDATED_COMPONENT.setGeographicalLocation(UPDATED_COMPONENT_GEOGRAPHICAL_LOCATION);
    UPDATED_COMPONENT.setParentIdentificationNumber(
        UPDATED_COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER);

    //initialize notification(s) (without creation- and modification time!)
    NOTIFICATION.setIdentificationNumber(NOTIFICATION_IDENTIFICATION_NUMBER);
    NOTIFICATION.setDescription(NOTIFICATION_DESCRIPTION);
    NOTIFICATION.setTitle(NOTIFICATION_TITLE);
    NOTIFICATION.setParentIdentificationNumber(NOTIFICATION_PARENT_COMPONENT_IDENTIFICATION_NUMBER);

    UPDATED_NOTIFICATION.setIdentificationNumber(NOTIFICATION_IDENTIFICATION_NUMBER);
    UPDATED_NOTIFICATION.setDescription(UPDATED_NOTIFICATION_DESCRIPTION);
    UPDATED_NOTIFICATION.setTitle(UPDATED_NOTIFICATION_TITLE);
    UPDATED_NOTIFICATION.setParentIdentificationNumber(
        UPDATED_NOTIFICATION_PARENT_COMPONENT_IDENTIFICATION_NUMBER);
  }

  //buildings:
  @Test
  void listBuildings_ShouldListBuildings() {
    List<Building> buildingListForMock = List.of(BUILDING);
    Mockito.when(BUILDING_REPOSITORY.findAll()).thenReturn(buildingListForMock);
    Assertions.assertEquals(SERVICE.listBuildings(), buildingListForMock);
  }

  @Test
  void getBuilding_ShouldGetBuilding() {
    Mockito.when(BUILDING_REPOSITORY.findById(BUILDING_IDENTIFICATION_NUMBER))
        .thenReturn(Optional.of(BUILDING));
    Assertions.assertEquals(BUILDING, SERVICE.getBuilding(BUILDING_IDENTIFICATION_NUMBER));
  }

  @Test
  void createBuilding_ShouldCreateBuilding() {
    Mockito.when(BUILDING_REPOSITORY.save(any())).thenAnswer(i -> i.getArguments()[0]);
    Assertions.assertTrue(
        TestUtils.buildingsAreEqual(SERVICE.createBuilding(SERVER_CREATE_BUILDING_REQUEST),
            BUILDING));
  }

  @Test
  void removeBuilding_ShouldRemoveBuilding() {
    Mockito.when(ROOM_REPOSITORY.findAllRoomsByParentId(BUILDING_IDENTIFICATION_NUMBER))
        .thenReturn(List.of(ROOM));
    Mockito.when(COMPONENT_REPOSITORY.findAllComponentsByParentId(BUILDING_IDENTIFICATION_NUMBER))
        .thenReturn(List.of(COMPONENT));
    Mockito.when(
            NOTIFICATION_REPOSITORY.findAllNotificationsByParentId(COMPONENT_IDENTIFICATION_NUMBER))
        .thenReturn(List.of(NOTIFICATION));
    SERVICE.removeBuilding(BUILDING_IDENTIFICATION_NUMBER);
    Mockito.verify(BUILDING_REPOSITORY).deleteById(BUILDING_IDENTIFICATION_NUMBER);
    verifyBuildingChildDeletion(BUILDING_IDENTIFICATION_NUMBER);
  }

  @Test
  void updateBuilding_ShouldUpdateBuilding() {
    Mockito.when(BUILDING_REPOSITORY.save(any())).thenAnswer(i -> i.getArguments()[0]);
    Assertions.assertTrue(
        TestUtils.buildingsAreEqual(SERVICE.updateBuilding(SERVER_UPDATE_BUILDING_REQUEST),
            UPDATED_BUILDING));
  }

  //rooms:
  @Test
  void listRooms_ShouldListRooms() {
    List<Room> roomListForMock = List.of(ROOM);
    Mockito.when(ROOM_REPOSITORY.findAllRoomsByParentId(ROOM_PARENT_BUILDING_IDENTIFICATION_NUMBER))
        .thenReturn(roomListForMock);
    Assertions.assertEquals(SERVICE.listRooms(ROOM_PARENT_BUILDING_IDENTIFICATION_NUMBER),
        roomListForMock);
  }

  @Test
  void getRoom_ShouldGetRoom() {
    Mockito.when(ROOM_REPOSITORY.findById(ROOM_IDENTIFICATION_NUMBER))
        .thenReturn(Optional.of(ROOM));
    Assertions.assertEquals(SERVICE.getRoom(ROOM_IDENTIFICATION_NUMBER), ROOM);
  }

  @Test
  void createRoom_ShouldCreateRoom() {
    Mockito.when(ROOM_REPOSITORY.save(any())).thenAnswer(i -> i.getArguments()[0]);
    Assertions.assertTrue(
        TestUtils.roomsAreEqual(SERVICE.createRoom(SERVER_CREATE_ROOM_REQUEST), ROOM));
  }

  @Test
  void removeRoom_ShouldRemoveRoom() {
    SERVICE.removeRoom(ROOM_IDENTIFICATION_NUMBER);
    Mockito.verify(ROOM_REPOSITORY).deleteById(ROOM_IDENTIFICATION_NUMBER);
    verifyRoomChildDeletion(ROOM_IDENTIFICATION_NUMBER);
  }

  @Test
  void updateRoom_ShouldUpdateRoom() {
    Mockito.when(ROOM_REPOSITORY.save(any())).thenAnswer(i -> i.getArguments()[0]);
    Assertions.assertTrue(
        TestUtils.roomsAreEqual(SERVICE.updateRoom(SERVER_UPDATE_ROOM_REQUEST),
            UPDATED_ROOM));
  }

  //components:
  @Test
  void listComponents_ShouldListComponents() {
    List<Component> componentListForMock = List.of(COMPONENT);
    Mockito.when(COMPONENT_REPOSITORY.findAllComponentsByParentId(
        COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER)).thenReturn(componentListForMock);
    Assertions.assertEquals(SERVICE.listComponents(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER),
        componentListForMock);
  }

  @Test
  void getComponent_ShouldGetComponent() {
    Mockito.when(COMPONENT_REPOSITORY.findById(COMPONENT_IDENTIFICATION_NUMBER))
        .thenReturn(Optional.of(COMPONENT));
    Assertions.assertEquals(SERVICE.getComponent(COMPONENT_IDENTIFICATION_NUMBER), COMPONENT);
  }

  @Test
  void createComponent_ShouldCreateComponent() {
    Mockito.when(COMPONENT_REPOSITORY.save(any())).thenAnswer(i -> i.getArguments()[0]);
    Assertions.assertTrue(
        TestUtils.componentsAreEqual(SERVICE.createComponent(SERVER_CREATE_COMPONENT_REQUEST),
            COMPONENT));
  }

  @Test
  void removeComponent_ShouldRemoveComponent() {
    SERVICE.removeComponent(COMPONENT_IDENTIFICATION_NUMBER);
    Mockito.verify(COMPONENT_REPOSITORY).deleteById(COMPONENT_IDENTIFICATION_NUMBER);
    verifyComponentChildDeletion(COMPONENT_IDENTIFICATION_NUMBER);
  }

  @Test
  void updateComponent_ShouldUpdateComponent() {
    Mockito.when(COMPONENT_REPOSITORY.save(any())).thenAnswer(i -> i.getArguments()[0]);
    Assertions.assertTrue(
        TestUtils.componentsAreEqual(SERVICE.updateComponent(SERVER_UPDATE_COMPONENT_REQUEST),
            UPDATED_COMPONENT));
  }

  //notifications:
  @Test
  void listNotifications_ShouldListNotifications() {
    List<Notification> notificationsListForMock = List.of(NOTIFICATION);
    Mockito.when(NOTIFICATION_REPOSITORY.findAllNotificationsByParentId(
        NOTIFICATION_PARENT_COMPONENT_IDENTIFICATION_NUMBER)).thenReturn(notificationsListForMock);
    Assertions.assertEquals(
        SERVICE.listNotifications(NOTIFICATION_PARENT_COMPONENT_IDENTIFICATION_NUMBER),
        notificationsListForMock);
  }

  @Test
  void getNotification_ShouldGetNotification() {
    Mockito.when(NOTIFICATION_REPOSITORY.findById(NOTIFICATION_IDENTIFICATION_NUMBER))
        .thenReturn(Optional.of(NOTIFICATION));
    Assertions.assertEquals(SERVICE.getNotification(NOTIFICATION_IDENTIFICATION_NUMBER),
        NOTIFICATION);
  }

  @Test
  void createNotification_ShouldCreateNotification() {
    Mockito.when(NOTIFICATION_REPOSITORY.save(any())).thenAnswer(i -> i.getArguments()[0]);

    Timestamp beforeCreate = new Timestamp(System.currentTimeMillis());
    Notification createdNotification = SERVICE.createNotification(
        SERVER_CREATE_NOTIFICATION_REQUEST);
    Timestamp afterCreate = new Timestamp(System.currentTimeMillis());

    Assertions.assertTrue(TestUtils.notificationsAreEqual(createdNotification, NOTIFICATION));
    Assertions.assertTrue(
        createdNotification.getCreationTime().getNanos() >= beforeCreate.getNanos()
            && createdNotification.getCreationTime().getNanos() <= afterCreate.getNanos());
    Assertions.assertTrue(
        createdNotification.getLastModifiedTime().getNanos() >= beforeCreate.getNanos()
            && createdNotification.getLastModifiedTime().getNanos() <= afterCreate.getNanos());
  }

  @Test
  void removeNotification_ShouldRemoveNotification() {
    SERVICE.removeNotification(NOTIFICATION_IDENTIFICATION_NUMBER);
    Mockito.verify(NOTIFICATION_REPOSITORY).deleteById(NOTIFICATION_IDENTIFICATION_NUMBER);
  }

  @Test
  void updateNotification_ShouldUpdateNotification() {
    Timestamp creationTimeMock = new Timestamp(System.currentTimeMillis());
    //originalNotification is only used for the original creation time (see SERVICE
    // .updateNotification(...))
    Notification originalNotification = new Notification();
    originalNotification.setCreationTime(creationTimeMock);
    Mockito.when(NOTIFICATION_REPOSITORY.save(any())).thenAnswer(i -> i.getArguments()[0]);
    Mockito.when(NOTIFICATION_REPOSITORY.findById(NOTIFICATION_IDENTIFICATION_NUMBER))
        .thenReturn(Optional.of(originalNotification));

    Timestamp beforeUpdate = new Timestamp(System.currentTimeMillis());
    Notification updatedNotification = SERVICE.updateNotification(
        SERVER_UPDATE_NOTIFICATION_REQUEST);
    Timestamp afterUpdate = new Timestamp(System.currentTimeMillis());

    Assertions.assertTrue(
        TestUtils.notificationsAreEqual(updatedNotification, UPDATED_NOTIFICATION));
    Assertions.assertTrue(
        updatedNotification.getLastModifiedTime().getNanos() >= beforeUpdate.getNanos()
            && updatedNotification.getLastModifiedTime().getNanos() <= afterUpdate.getNanos());
    Assertions.assertTrue(
        updatedNotification.getCreationTime().getNanos() <= beforeUpdate.getNanos());
  }

  /**
   * Verifies that all rooms, components and notifications that belong to a building are also
   * deleted when the building gets deleted.
   *
   * @param buildingIdentificationNumber the identification number of the building
   */
  private void verifyBuildingChildDeletion(String buildingIdentificationNumber) {
    Collection<Room> roomsOfBuilding = ROOM_REPOSITORY.findAllRoomsByParentId(
        buildingIdentificationNumber);
    for (Room room : roomsOfBuilding) {
      verifyRoomChildDeletion(room.getIdentificationNumber());
    }
    Mockito.verify(ROOM_REPOSITORY, atLeastOnce()).deleteByParentId(buildingIdentificationNumber);
    Mockito.verify(COMPONENT_REPOSITORY, atLeastOnce())
        .deleteByParentId(buildingIdentificationNumber);
    Mockito.verify(NOTIFICATION_REPOSITORY, atLeastOnce())
        .deleteByParentId(buildingIdentificationNumber);
  }

  /**
   * Verifies that all components and notifications that belong to a room are also deleted when the
   * room gets deleted.
   *
   * @param roomIdentificationNumber the identification number of the room
   */
  private void verifyRoomChildDeletion(String roomIdentificationNumber) {
    Collection<Component> componentsOfRoom = COMPONENT_REPOSITORY.findAllComponentsByParentId(
        roomIdentificationNumber);
    for (Component component : componentsOfRoom) {
      verifyComponentChildDeletion(component.getIdentificationNumber());
    }
    Mockito.verify(COMPONENT_REPOSITORY, atLeastOnce()).deleteByParentId(roomIdentificationNumber);
    Mockito.verify(NOTIFICATION_REPOSITORY, atLeastOnce())
        .deleteByParentId(roomIdentificationNumber);
  }

  /**
   * Verifies that all notifications that belong to a component are also deleted when the component
   * gets deleted.
   *
   * @param componentIdentificationNumber the identification number of the component
   */
  private void verifyComponentChildDeletion(String componentIdentificationNumber) {
    Mockito.verify(NOTIFICATION_REPOSITORY, atLeastOnce())
        .deleteByParentId(componentIdentificationNumber);
  }

}
