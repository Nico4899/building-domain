package edu.kit.tm.cm.smartcampus.building.logic.operations.utility;

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
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Floors;
import edu.kit.tm.cm.smartcampus.building.logic.model.GeographicalLocation;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DataTransferUtils {

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class ServerRequestReader {

    public static Notification readServerCreateNotificationRequest(
        ServerCreateNotificationRequest serverCreateNotificationRequest) {
      Notification notification = new Notification();
      notification.setDescription(serverCreateNotificationRequest.getDescription());
      notification.setParentIdentificationNumber(
          serverCreateNotificationRequest.getParentIdentificationNumber());
      notification.setTitle(serverCreateNotificationRequest.getTitle());
      return notification;
    }

    public static Building readServerCreateBuildingRequest(
        ServerCreateBuildingRequest serverCreateBuildingRequest) {
      Building building = new Building();
      building.setName(serverCreateBuildingRequest.getName());
      building.setNumber(serverCreateBuildingRequest.getNumber());
      building.setFloors(parse(serverCreateBuildingRequest.getFloors()));
      building.setCampusLocation(serverCreateBuildingRequest.getCampusLocation());
      building.setGeographicalLocation(
          parse(serverCreateBuildingRequest.getGeographicalLocation()));
      return building;
    }

    public static Component readServerCreateComponentRequest(
        ServerCreateComponentRequest serverCreateComponentRequest) {
      Component component = new Component();
      component.setDescription(serverCreateComponentRequest.getDescription());
      component.setType(serverCreateComponentRequest.getType());
      component.setGeographicalLocation(
          parse(serverCreateComponentRequest.getGeographicalLocation()));
      component.setParentIdentificationNumber(
          serverCreateComponentRequest.getParentIdentificationNumber());
      return component;
    }

    public static Room readServerCreateRoomRequest(
        ServerCreateRoomRequest serverCreateRoomRequest) {
      Room room = new Room();
      room.setFloor(serverCreateRoomRequest.getFloor());
      room.setName(serverCreateRoomRequest.getName());
      room.setNumber(serverCreateRoomRequest.getNumber());
      room.setType(serverCreateRoomRequest.getType());
      room.setGeographicalLocation(parse(serverCreateRoomRequest.getGeographicalLocation()));
      room.setParentIdentificationNumber(serverCreateRoomRequest.getParentIdentificationNumber());
      return room;
    }

    public static Notification readServerUpdateNotificationRequest(
        ServerUpdateNotificationRequest serverUpdateNotificationRequest) {
      Notification notification = new Notification();
      notification.setDescription(serverUpdateNotificationRequest.getDescription());
      notification.setParentIdentificationNumber(
          serverUpdateNotificationRequest.getParentIdentificationNumber());
      notification.setTitle(serverUpdateNotificationRequest.getTitle());
      notification.setIdentificationNumber(
          serverUpdateNotificationRequest.getIdentificationNumber());
      return notification;
    }

    public static Building readServerUpdateBuildingRequest(
        ServerUpdateBuildingRequest serverUpdateBuildingRequest) {
      Building building = new Building();
      building.setName(serverUpdateBuildingRequest.getName());
      building.setNumber(serverUpdateBuildingRequest.getNumber());
      building.setFloors(parse(serverUpdateBuildingRequest.getFloors()));
      building.setCampusLocation(serverUpdateBuildingRequest.getCampusLocation());
      building.setGeographicalLocation(
          parse(serverUpdateBuildingRequest.getGeographicalLocation()));
      building.setIdentificationNumber(serverUpdateBuildingRequest.getIdentificationNumber());
      return building;
    }

    public static Component readServerUpdateComponentRequest(
        ServerUpdateComponentRequest serverUpdateComponentRequest) {
      Component component = new Component();
      component.setDescription(serverUpdateComponentRequest.getDescription());
      component.setType(serverUpdateComponentRequest.getType());
      component.setGeographicalLocation(
          parse(serverUpdateComponentRequest.getGeographicalLocation()));
      component.setParentIdentificationNumber(
          serverUpdateComponentRequest.getParentIdentificationNumber());
      component.setIdentificationNumber(serverUpdateComponentRequest.getIdentificationNumber());
      return component;
    }

    public static Room readServerUpdateRoomRequest(
        ServerUpdateRoomRequest serverUpdateRoomRequest) {
      Room room = new Room();
      room.setFloor(serverUpdateRoomRequest.getFloor());
      room.setName(serverUpdateRoomRequest.getName());
      room.setNumber(serverUpdateRoomRequest.getNumber());
      room.setGeographicalLocation(parse(serverUpdateRoomRequest.getGeographicalLocation()));
      room.setParentIdentificationNumber(serverUpdateRoomRequest.getParentIdentificationNumber());
      room.setIdentificationNumber(serverUpdateRoomRequest.getIdentificationNumber());
      room.setType(serverUpdateRoomRequest.getType());
      return room;
    }

    private static Floors parse(FloorsDTO floorsDTO) {
      Floors floors = new Floors();
      floors.setHighestFloor(floorsDTO.getHighestFloor());
      floors.setLowestFloor(floorsDTO.getLowestFloor());
      return floors;
    }

    private static GeographicalLocation parse(GeographicalLocationDTO geographicalLocationDTO) {
      GeographicalLocation geographicalLocation = new GeographicalLocation();
      geographicalLocation.setLatitude(geographicalLocationDTO.getLatitude());
      geographicalLocation.setLongitude(geographicalLocationDTO.getLongitude());
      return geographicalLocation;
    }
  }
}
