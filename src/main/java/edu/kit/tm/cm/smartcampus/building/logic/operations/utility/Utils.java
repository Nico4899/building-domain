package edu.kit.tm.cm.smartcampus.building.logic.operations.utility;

import edu.kit.tm.cm.smartcampus.building.api.controller.dto.BuildingRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.dto.ComponentRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.dto.NotificationRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.dto.RoomRequest;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Utils {

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Writer {

    /**
     * Converts a ComponentRequest to a Component and sets all attributes of the Component to the
     * values of the existing attributes of the Request.
     *
     * @param componentRequest the Request to be converted
     * @return the resulting Component
     */
    public static Component write(ComponentRequest componentRequest) {
      Component component = new Component();
      component.setDescription(componentRequest.getDescription());
      component.setParentIdentificationNumber(componentRequest.getParentIdentificationNumber());
      component.setGeographicalLocation(componentRequest.getGeographicalLocation());
      component.setType(componentRequest.getType());
      return component;
    }

    /**
     * Converts a RoomRequest to a Room and sets all attributes except of the parent Building of the
     * Room to the values of the existing attributes of the Request.
     *
     * @param roomRequest the Request to be converted
     * @return the resulting Room
     */
    public static Room write(RoomRequest roomRequest) {
      Room room = new Room();
      room.setName(roomRequest.getName());
      room.setNumber(roomRequest.getNumber());
      room.setFloor(roomRequest.getFloor());
      room.setGeographicalLocation(roomRequest.getGeographicalLocation());
      room.setType(roomRequest.getType());
      return room;
    }
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Reader {

    /**
     * Converts a NotificationRequest to a Notification and sets all attributes of the Notification to
     * the values of the existing attributes of the Request.
     *
     * @param notificationRequest the Request to be converted
     * @return the resulting Notification
     */
    public static Notification read(
      NotificationRequest notificationRequest) {
      Notification notification = new Notification();
      notification.setDescription(notificationRequest.getDescription());
      notification.setParentIdentificationNumber(notificationRequest.getParentIdentificationNumber());
      notification.setTitle(notificationRequest.getTitle());
      return notification;
    }

    /**
     * Converts a BuildingRequest to a Building and sets all attributes of the Building to the values
     * of the existing attributes of the Request.
     *
     * @param buildingRequest the request to be converted
     * @return the resulting Building
     */
    public static Building read(BuildingRequest buildingRequest) {
      Building building = new Building();
      building.setName(buildingRequest.getName());
      building.setNumber(buildingRequest.getNumber());
      building.setFloors(buildingRequest.getFloors());
      building.setCampusLocation(buildingRequest.getCampusLocation());
      building.setGeographicalLocation(buildingRequest.getGeographicalLocation());
      return building;
    }
  }
}

