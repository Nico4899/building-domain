package edu.kit.tm.cm.smartcampus.building.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions.ResourceNotFoundException;
import edu.kit.tm.cm.smartcampus.building.utils;
import org.springframework.beans.factory.annotation.Autowired;

import static edu.kit.tm.cm.smartcampus.building.utils.*;

public final class ServiceValidator {


  private final BuildingRepository buildingRepository;
  private final RoomRepository roomRepository;
  private final ComponentRepository componentRepository;
  private final NotificationRepository notificationRepository;

  private final String buildingPrefix = "b";

  private final String roomPrefix = "r";
  private final String componentPrefix = "c";

  private final String notificationPrefix = "n";

  @Autowired
  public ServiceValidator(
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
   * Validates whether the referencedId / referenceId of an object is within the constraints. Cannot be invoked on a
   * building as it has no referencedId. Does NOT check whether the given strings are null.
   * @param id id of the considered object, must not be a buildingId
   * @param referencedId referencedId / referenceId of the considered object, whether it is valid depends on the id,
   *                     must not be a notificationId
   * @return Optional of the name of the parent / referenced object (e.g. "building")
   */
  public void validateReferencedId(String id, String referencedId) { //todo regex als variable in text und notification parent
    if (id == null || referencedId == null) {
      throw new ResourceNotFoundException("Either identification number or parent number is null!");
    }
    String prefixReferenced = referencedId.substring(0, 1);
    String prefixId = id.substring(0, 1);
    InvalidArgumentsException inArgsEx = new InvalidArgumentsException();
    boolean exceptionThrown = false;
    switch (prefixId) {
      case buildingPrefix -> {
        inArgsEx.appendWrongArguments(BUILDING, id, PARENT_NOT_ALLOWED_ERROR, true);
        throw inArgsEx;
      }
      case roomPrefix -> {
        if (!referencedId.matches(BIN_PATTERN)) {
          inArgsEx.appendWrongArguments(PARENT, referencedId, EXPECTED_FORMAT + SPACE + BIN_PATTERN,
              true);
          exceptionThrown = true;
        }
        if (!checkParentOfRoom(referencedId)) {
          inArgsEx.appendWrongArguments(PARENT, referencedId, PARENT_NOT_FOUND_ERROR, true);
          exceptionThrown = true;
        }
      }
      case componentPrefix -> {
        if (!(referencedId.matches(BIN_PATTERN) || referencedId.matches(RIN_PATTERN))) {
          inArgsEx.appendWrongArguments(PARENT, referencedId, EXPECTED_FORMAT + SPACE + BIN_PATTERN + SPACE +
              OR + SPACE + RIN_PATTERN, true);
          exceptionThrown = true;
        }
        if (!checkParentOfComponent(referencedId, prefixReferenced)) {
          inArgsEx.appendWrongArguments(PARENT, referencedId, PARENT_NOT_FOUND_ERROR, true);
          exceptionThrown = true;
        }
      }
      case notificationPrefix -> {
        if (!(referencedId.matches(BIN_PATTERN) || referencedId.matches(RIN_PATTERN)
            || referencedId.matches(CIN_PATTERN))) {
          inArgsEx.appendWrongArguments(PARENT, referencedId, EXPECTED_FORMAT + SPACE + BIN_PATTERN + SPACE +
              OR + SPACE + RIN_PATTERN + SPACE + OR + CIN_PATTERN, true);
          exceptionThrown = true;
        }
        if (!checkReferencedOfNotification(referencedId, prefixReferenced)) {
          inArgsEx.appendWrongArguments(REFERENCED, referencedId, REFERENCED_NOT_FOUND_ERROR, true);
          exceptionThrown = true;
        }
      }
      default -> {
      }
    }
    if (exceptionThrown) {
      throw inArgsEx;
    }
  }



  /**
   * Checks whether room parent is actually a building
   * @param parentId
   * @return Optional.of("room") if true or Optional.empty() otherwise
   */
  private boolean checkParentOfRoom(String parentId) throws ResourceNotFoundException {
    return buildingRepository.findById(parentId).isPresent();
  }


  /**
   * Checks whether parentId of a component references a building or a room
   * @param parentId
   * @param parentPrefix
   * @return
   */
  private boolean checkParentOfComponent(String parentId, String parentPrefix) {
    switch (parentPrefix) {
      case buildingPrefix -> {
        return buildingRepository.findById(parentId).isPresent();
      }
      case roomPrefix -> {
        return roomRepository.findById(parentId).isPresent();
      }
      default -> { return false; }
    }
  }

  private boolean checkReferencedOfNotification(String parentId, String parentPrefix) {
    switch (parentPrefix) {
      case buildingPrefix -> {
        return buildingRepository.findById(parentId).isPresent();
      }
      case roomPrefix -> {
        return roomRepository.findById(parentId).isPresent();
      }
      case componentPrefix -> {
        return componentRepository.findById(parentId).isPresent();
      }
      default -> { return false; }
    }
  }



}
