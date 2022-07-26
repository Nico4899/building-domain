package edu.kit.tm.cm.smartcampus.building.logic;

import edu.kit.tm.cm.smartcampus.building.api.requests.BuildingRequest;
import edu.kit.tm.cm.smartcampus.building.api.requests.ComponentRequest;
import edu.kit.tm.cm.smartcampus.building.api.requests.NotificationRequest;
import edu.kit.tm.cm.smartcampus.building.api.requests.RoomRequest;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
//TODO javadoc (überall!^^)
public class LogicUtils {

  private LogicUtils(){}
  
  public static Building convertBuildingRequestToBuilding(BuildingRequest buildingRequest) {
    Building building = new Building();
    building.setName(buildingRequest.getName());
    building.setNumber(buildingRequest.getNumber());
    building.setFloors(buildingRequest.getFloors());
    building.setCampusLocation(buildingRequest.getCampusLocation());
    building.setGeographicalLocation(buildingRequest.getGeographicalLocation());
    return building;
  }

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

  public static Component convertComponentRequestToComponent(ComponentRequest componentRequest) {
    Component component = new Component();
    component.setDescription(componentRequest.getDescription());
    component.setParentIdentificationNumber(componentRequest.getParentIdentificationNumber());
    component.setGeographicalLocation(componentRequest.getGeographicalLocation());
    component.setType(componentRequest.getType());
    return component;
  }

  public static Notification convertNotificationRequestToNotification(NotificationRequest notificationRequest) {
    Notification notification = new Notification();
    notification.setDescription(notificationRequest.getDescription());
    notification.setParentIdentificationNumber(notificationRequest.getParentIdentificationNumber());
    notification.setTitle(notificationRequest.getTitle());
    return notification;
  }
  
}
