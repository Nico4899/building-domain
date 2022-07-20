package edu.kit.tm.cm.smartcampus.building.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class BuildingInputValidator extends InputValidator {

  public void validateBuilding(Building building) { //TODO validate numFloors
    validateNotNull(Map.of(
            "building name", building.getBuildingName(),
            "building number", building.getBuildingNumber(),
            "building identification number", building.getBin(),
            "building number of floors", building.getNumFloors(),
            "building campus location", building.getCampusLocation(),
            "building geographical location", building.getGeographicalLocation()
    ));

    validateNotEmpty(Map.of(
            "building name", building.getBuildingName()
    ));

    validateMatchesRegex(Map.of(
            "building number", Pair.of(building.getBuildingNumber(), "TODO building number regex"),
            "building identification number", Pair.of(building.getBin(), "TODO bin regex")
    ));

    validateGeographicalLocation(Map.of(
            "building geographical location", building.getGeographicalLocation()
    ));
  }

  public void validateRoom(Room room) { //TODO validate numFloors
    validateNotNull(Map.of(
            "room name", room.getRoomName(),
            "room number", room.getRoomNumber(),
            "room identification number", room.getRin(),
            "room parent identification number", room.getParentIn(),
            "room floor", room.getFloor(),
            "room type", room.getRoomType(),
            "room geographical location", room.getGeographicalLocation()
    ));

    validateNotEmpty(Map.of(
            "room name", room.getRoomName(),
            "room number", room.getRoomNumber()
    ));

    validateMatchesRegex(Map.of(
            "room identification number", Pair.of(room.getRin(), "TODO rin regex"),
            "room parent identification number", Pair.of(room.getParentIn(), "TODO bin regex")
    ));

    validateGeographicalLocation(Map.of(
            "room geographical location", room.getGeographicalLocation()
    ));
  }

  public void validateComponent(edu.kit.tm.cm.smartcampus.building.logic.model.Component component) {
    validateNotNull(Map.of(
            "component description", component.getComponentDescription(),
            "component identification number", component.getCin(),
            "component parent identification number", component.getParentIn(),
            "component type", component.getComponentType(),
            "component geographical location", component.getGeographicalLocation()
    ));

    validateNotEmpty(Map.of(
            "component description", component.getComponentDescription()
    ));

    validateMatchesRegex(Map.of(
            "component identification number", Pair.of(component.getCin(), "TODO cin regex"),
            "component parent identification number", Pair.of(component.getParentIn(), "TODO bin/rin regex")
    ));

    validateGeographicalLocation(Map.of(
            "component geographical location", component.getGeographicalLocation()
    ));
  }

  public void validateNotification(Notification notification) { //TODO validate notification.getCreationTime().getTime()
    validateNotNull(Map.of(
            "notification title", notification.getNotificationTitle(),
            "notification description", notification.getNotificationDescription(),
            "notification identification number", notification.getNin(),
            "notification parent identification number", notification.getParentIn(),
            "notification creation time", notification.getCreationTime()
    ));

    validateNotEmpty(Map.of(
            "notification title", notification.getNotificationTitle(),
            "notification description", notification.getNotificationDescription()
    ));

    validateMatchesRegex(Map.of(
            "notification identification number", Pair.of(notification.getNin(), "TODO nin regex"),
            "notification parent identification number", Pair.of(notification.getParentIn(), "TODO bin/rin/cin regex")
    ));
  }

  public void validateBin(String bin) {
    validateNotNull(Map.of("building identification number", bin));
    validateNotNull(Map.of("building identification number", Pair.of(bin, "TODO bin regex")));
  }

  public void validateRin(String rin) {
    validateNotNull(Map.of("room identification number", rin));
    validateNotNull(Map.of("room identification number", Pair.of(rin, "TODO rin regex")));
  }

  public void validateCin(String cin) {
    validateNotNull(Map.of("component identification number", cin));
    validateNotNull(Map.of("component identification number", Pair.of(cin, "TODO cin regex")));
  }

  public void validateNin(String nin) {
    validateNotNull(Map.of("notification identification number", nin));
    validateNotNull(Map.of("notification identification number", Pair.of(nin, "TODO nin regex")));
  }

}
