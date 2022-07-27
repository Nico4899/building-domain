package edu.kit.tm.cm.smartcampus.building.logic;

import edu.kit.tm.cm.smartcampus.building.api.requests.BuildingRequest;
import edu.kit.tm.cm.smartcampus.building.api.requests.ComponentRequest;
import edu.kit.tm.cm.smartcampus.building.api.requests.NotificationRequest;
import edu.kit.tm.cm.smartcampus.building.api.requests.RoomRequest;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;

/**
 * This class provides utilities to work within the building domain.
 */
public class LogicUtils {

  private LogicUtils() {
  }

  /**
   * Converts a BuildingRequest to a Building and sets all attributes of the Building to the values
   * of the existing attributes of the Request.
   *
   * @param buildingRequest the request to be converted
   * @return the resulting Building
   */
  public static Building convertBuildingRequestToBuilding(BuildingRequest buildingRequest) {
    Building building = new Building();
    building.setName(buildingRequest.getName());
    building.setNumber(buildingRequest.getNumber());
    building.setFloors(buildingRequest.getFloors());
    building.setCampusLocation(buildingRequest.getCampusLocation());
    building.setGeographicalLocation(buildingRequest.getGeographicalLocation());
    return building;
  }

  /**
   * Converts a RoomRequest to a Room and sets all attributes of the Room to the values of the
   * existing attributes of the Request.
   *
   * @param roomRequest the Request to be converted
   * @return the resulting Room
   */
  public static Room convertRoomRequestToRoom(RoomRequest roomRequest) {
    Room room = new Room();
    room.setName(roomRequest.getName());
    room.setNumber(roomRequest.getNumber());
    room.setFloor(roomRequest.getFloor());
    room.setParentIdentificationNumber(roomRequest.getParentIdentificationNumber());
    room.setGeographicalLocation(roomRequest.getGeographicalLocation());
    room.setType(roomRequest.getType());
    return room;
  }

  /**
   * Converts a ComponentRequest to a Component and sets all attributes of the Component to the
   * values of the existing attributes of the Request.
   *
   * @param componentRequest the Request to be converted
   * @return the resulting Component
   */
  public static Component convertComponentRequestToComponent(ComponentRequest componentRequest) {
    Component component = new Component();
    component.setDescription(componentRequest.getDescription());
    component.setParentIdentificationNumber(componentRequest.getParentIdentificationNumber());
    component.setGeographicalLocation(componentRequest.getGeographicalLocation());
    component.setType(componentRequest.getType());
    return component;
  }

  /**
   * Converts a NotificationRequest to a Notification and sets all attributes of the Notification to
   * the values of the existing attributes of the Request.
   *
   * @param notificationRequest the Request to be converted
   * @return the resulting Notification
   */
  public static Notification convertNotificationRequestToNotification(
      NotificationRequest notificationRequest) {
    Notification notification = new Notification();
    notification.setDescription(notificationRequest.getDescription());
    notification.setParentIdentificationNumber(notificationRequest.getParentIdentificationNumber());
    notification.setTitle(notificationRequest.getTitle());
    return notification;
  }

}
