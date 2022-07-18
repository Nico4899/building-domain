package edu.kit.tm.cm.smartcampus.building.infrastructure.service;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.*;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public final class ServiceValidation {

  private static final String PIN_PATTERN = "^p-\\d+$";

  private final BuildingRepository buildingRepository;
  private final RoomRepository roomRepository;
  private final ComponentRepository componentRepository;
  private final NotificationRepository notificationRepository;

  private final String buildingInfo = "building";
  private final String buildinPrefix = "b";

  private final String roomInfo = "room";
  private final String roomPrefix = "r";
  private final String componentInfo = "component";
  private final String componentPrefix = "c";

  private final String notificationInfo = "notification";
  private final String notificationPrefix = "n";

  @Autowired
  public ServiceValidation(
      BuildingRepository buildingRepository,
      RoomRepository roomRepository,
      ComponentRepository componentRepository,
      NotificationRepository notificationRepository) {
    this.buildingRepository = buildingRepository;
    this.roomRepository = roomRepository;
    this.componentRepository = componentRepository;
    this.notificationRepository = notificationRepository;
  }

  /**
   * Validates whether the idReferenced / referenceId of an object is within the constraints. Cannot be invoked on a
   * building as it has no idReferenced. Does NOT check whether the given strings are null.
   * @param id id of the considered object, must not be a buildingId
   * @param idReferenced idReferenced / referenceId of the considered object, whether it is valid depends on the id,
   *                     must not be a notificationId
   * @return Optional of the name of the parent / referenced object (e.g. "building")
   */
  public Optional<String> validateReferencedId(String id, String idReferenced) {
    String prefixReferenced = idReferenced.substring(0, 1);
    String prefixId = id.substring(0, 1);
    switch (prefixId) {
      case roomPrefix -> {return checkForRoom(idReferenced);}
      case componentPrefix -> {return checkForComponent(idReferenced, prefixReferenced);}
      case notificationPrefix -> {return checkForNotification(idReferenced, prefixReferenced);}
      default -> {return Optional.empty();}
    }
  }

  /**
   * Checks whether room parent is actually a building
   * @param referencedId
   * @return Optional.of("room") if true or Optional.empty() otherwise
   */
  private Optional<String> checkForRoom(String referencedId) {
    Optional<Building> parentBuilding =  buildingRepository.findById(referencedId);
    if (parentBuilding.isPresent()) {
      return Optional.of("building");
    } else {
      return Optional.empty();
    }
  }

  /**
   * Checks whether referencedId of a component references a building or a room
   * @param referencedId
   * @param referencedPrefix
   * @return
   */
  private Optional<String> checkForComponent(String referencedId, String referencedPrefix) {
    switch (referencedPrefix) {
      case roomPrefix -> { return roomRepository.findById(referencedId).isPresent() ?
          Optional.of("room") : Optional.empty(); }
      case buildinPrefix -> { return buildingRepository.findById(referencedId).isPresent() ?
          Optional.of("building") : Optional.empty(); }
      default -> {return Optional.empty(); }
    }
  }

  /**
   * Checks whether the referenceId of a notification is not null. If true, Optional.of("problem") is returned as a
   * notification can only reference a problem
   * @param referencedId
   * @param referencedPrefix
   * @return Optional.of("problem") or Optional.empty
   */
  private Optional<String> checkForNotification(String referencedId, String referencedPrefix) {
    if(referencedId == null) {
      return Optional.empty();
    } else {
      return Optional.of("problem");
    }
  }






}
