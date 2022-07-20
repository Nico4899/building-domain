package edu.kit.tm.cm.smartcampus.building.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions.ResourceNotFoundException;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

import static edu.kit.tm.cm.smartcampus.building.utils.*;

public final class DataValidator {


  private final BuildingRepository buildingRepository;
  private final RoomRepository roomRepository;
  private final ComponentRepository componentRepository;
  private final NotificationRepository notificationRepository;

  private final String buildingPrefix = "b";

  private final String roomPrefix = "r";
  private final String componentPrefix = "c";

  private final String notificationPrefix = "n";

  public DataValidator(
          BuildingRepository buildingRepository,
          RoomRepository roomRepository,
          ComponentRepository componentRepository,
          NotificationRepository notificationRepository) {
    this.buildingRepository = buildingRepository;
    this.roomRepository = roomRepository;
    this.componentRepository = componentRepository;
    this.notificationRepository = notificationRepository;
  }

  public void validateBuildingExists(String identificationNumber) throws ResourceNotFoundException {
    if (!buildingRepository.existsById(identificationNumber)) {
      throw new ResourceNotFoundException(String.format(NOT_FOUND, identificationNumber));
    }
  }

  /*public void validateInDoesNotExist(String in) {
    String prefixIn = in.substring(0, 1);
    InvalidArgumentsException inArgsEx = new InvalidArgumentsException();
    boolean exceptionThrown = false;
    switch (prefixIn) {
      case buildingPrefix -> {
        if(buildingRepository.findById(in).isPresent()) {
          inArgsEx.appendWrongArguments(BUILDING, in, RESOURCE_ALREADY_EXISTS, true);
          exceptionThrown = true;
        }
      }
      case roomPrefix -> {
        if(roomRepository.findById(in).isPresent()) {
          inArgsEx.appendWrongArguments(ROOM, in, RESOURCE_ALREADY_EXISTS, true);
          exceptionThrown = true;
        }
      }
      case componentPrefix -> {
        if(componentRepository.findById(in).isPresent()) {
          inArgsEx.appendWrongArguments(COMPONENT, in, RESOURCE_ALREADY_EXISTS, true);
          exceptionThrown = true;
        }
      }
      case notificationPrefix -> {
        if(notificationRepository.findById(in).isPresent()) {
          inArgsEx.appendWrongArguments(NOTIFICATION, in, RESOURCE_ALREADY_EXISTS, true);
          exceptionThrown = true;
        }
      }
    }
    if(exceptionThrown) {
      throw inArgsEx;
    }
  }*/

  public void validateInDoesNotExist(CrudRepository repository, String in) {
    if (repository.existsById(in)) {
      InvalidArgumentsException inArgsEx = new InvalidArgumentsException();
      inArgsEx.appendWrongArguments(IN, in, RESOURCE_ALREADY_EXISTS, true);
      throw inArgsEx;
    }
  }


  /**
   * Validates whether the relatedIdentificationNumber / referenceId of an object is within the constraints. Cannot be invoked on a
   * building as it has no relatedIdentificationNumber. Does NOT check whether the given strings are null.
   *
   * @param id           id of the considered object, must not be a buildingId
   * @param relatedIdentificationNumber relatedIdentificationNumber / referenceId of the considered object, whether it is valid depends on the id,
   *                     must not be a notificationId
   * @return Optional of the name of the parent / referenced object (e.g. "building")
   */
  public void validateRelationExists(String relatedIdentificationNumber) throws InvalidArgumentsException,
          ResourceNotFoundException {
    Collection<CrudRepository<?, String>> repositories = List.of(buildingRepository, roomRepository, componentRepository);
    boolean found = false;
    for (CrudRepository<?,String> repository : repositories) {
      if (repository.existsById(relatedIdentificationNumber)) {
        found = true;
      }
    }
    if (!found) {
      throw new InvalidArgumentsException();
    }


    /*
    if (id == null || relatedIdentificationNumber == null) {
      throw new ResourceNotFoundException(OBJECTS_ARE_NULL + SPACE + id + COMMA + SPACE + relatedIdentificationNumber);
    }
    String prefixReferenced = relatedIdentificationNumber.substring(0, 1);
    String prefixId = id.substring(0, 1);
    InvalidArgumentsException inArgsEx = new InvalidArgumentsException();
    boolean exceptionThrown = false;
    switch (prefixId) {
      case buildingPrefix -> {
        inArgsEx.appendWrongArguments(BUILDING, id, PARENT_NOT_ALLOWED_ERROR, true);
        throw inArgsEx;
      }
      case roomPrefix -> {
        if (!relatedIdentificationNumber.matches(BIN_PATTERN)) {
          inArgsEx.appendWrongArguments(PARENT, relatedIdentificationNumber, EXPECTED_FORMAT + SPACE + BIN_PATTERN,
                  true);
          exceptionThrown = true;
        }
        if (!checkParentOfRoom(relatedIdentificationNumber)) {
          inArgsEx.appendWrongArguments(PARENT, relatedIdentificationNumber, PARENT_NOT_FOUND_ERROR, true);
          exceptionThrown = true;
        }
      }
      case componentPrefix -> {
        if (!(relatedIdentificationNumber.matches(BIN_PATTERN) || relatedIdentificationNumber.matches(RIN_PATTERN))) {
          inArgsEx.appendWrongArguments(PARENT, relatedIdentificationNumber, EXPECTED_FORMAT + SPACE + BIN_PATTERN + SPACE +
                  OR + SPACE + RIN_PATTERN, true);
          exceptionThrown = true;
        }
        if (!checkParentOfComponent(relatedIdentificationNumber, prefixReferenced)) {
          inArgsEx.appendWrongArguments(PARENT, relatedIdentificationNumber, PARENT_NOT_FOUND_ERROR, true);
          exceptionThrown = true;
        }
      }
      case notificationPrefix -> {
        if (!(relatedIdentificationNumber.matches(BIN_PATTERN) || relatedIdentificationNumber.matches(RIN_PATTERN)
                || relatedIdentificationNumber.matches(CIN_PATTERN))) {
          inArgsEx.appendWrongArguments(PARENT, relatedIdentificationNumber, EXPECTED_FORMAT + SPACE + BIN_PATTERN + SPACE +
                  OR + SPACE + RIN_PATTERN + SPACE + OR + CIN_PATTERN, true);
          exceptionThrown = true;
        }
        if (!checkReferencedOfNotification(relatedIdentificationNumber, prefixReferenced)) {
          inArgsEx.appendWrongArguments(REFERENCED, relatedIdentificationNumber, REFERENCED_NOT_FOUND_ERROR, true);
          exceptionThrown = true;
        }
      }
      default -> {
      }
    }
    if (exceptionThrown) {
      throw inArgsEx;
    }
    */
  }


  /**
   * Checks whether room parent is actually a building
   *
   * @param parentId
   * @return Optional.of(" room ") if true or Optional.empty() otherwise
   */
  private boolean checkParentOfRoom(String parentId) throws ResourceNotFoundException {
    return buildingRepository.findById(parentId).isPresent();
  }


  /**
   * Checks whether parentId of a component references a building or a room
   *
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
      default -> {
        return false;
      }
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
      default -> {
        return false;
      }
    }
  }

}
