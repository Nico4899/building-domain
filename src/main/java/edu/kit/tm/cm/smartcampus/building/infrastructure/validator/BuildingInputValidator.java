package edu.kit.tm.cm.smartcampus.building.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;

import java.util.Map;

/**
 * Class representing a building input validator which checks given objects from the building model and thereby
 * validates them and throws the right exceptions when an input is invalid.
 */
@org.springframework.stereotype.Component
@AllArgsConstructor
public class BuildingInputValidator extends InputValidator {

  /**
   * Validates a building regarding everything that has nothing to do with the database (e.g. whether attributes are
   * null, empty or do nat match a regex ...)
   *
   * @param building the building to be validated
   */
  public void validate(Building building) { //TODO validate numFloors
    validateNotNull(Map.of(
            "building", building,
            "building name", building.getBuildingName(),
            "building number", building.getBuildingNumber(),
            "building identification number", building.getBin(),
            "building floors", building.getBuildingFloors(),
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

  /**
   * Validates a Room regarding everything that has nothing to do with the database (e.g. whether attributes are
   * null, empty or do nat match a regex ...)
   *
   * @param room the room to be validated
   */
  public void validate(Room room) { //TODO validate numFloors
    validateNotNull(Map.of(
            "room ", room,
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

  /**
   * Validates a component regarding everything that has nothing to do with the database (e.g. whether attributes are
   * null, empty or do nat match a regex ...)
   *
   * @param component the component to be validated
   */
  public void validate(Component component) {
    validateNotNull(Map.of(
            "component ", component,
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

  /**
   * Validates a notification regarding everything that has nothing to do with the database (e.g. whether attributes are
   * null, empty or do nat match a regex ...)
   *
   * @param notification the notification to be validated
   */
  public void validate(Notification notification) {
    validateNotNull(Map.of(
            "notification ", notification,
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

  /**
   * Validates an identification number regarding everything that has nothing to do with the database.
   *
   * @param in      the identification number to be validated
   * @param inName  the name of the identification number
   * @param inRegex the regex that the identification number should match
   */
  public void validateIn(String in, String inName, String inRegex) {
    validateNotNull(Map.of(inName, in));
    validateNotNull(Map.of(inName, Pair.of(in, inRegex)));
  }

}
