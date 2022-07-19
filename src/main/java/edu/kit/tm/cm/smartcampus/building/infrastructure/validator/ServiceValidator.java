package edu.kit.tm.cm.smartcampus.building.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

public final class ServiceValidator {

  private static final String BIN_PATTERN = "^b-\\d+$";
  private static final String RIN_PATTERN = "^r-\\d+$";
  private static final String PIN_PATTERN = "^p-\\d+$";


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
  public boolean validateReferencedId(String id, String referencedId) { //todo regex als variable in text und notification parent
    if(id == null || referencedId == null) {
      throw new ResourceNotFoundException("Either identification number or parent number is null!");
    }
    String prefixReferenced = referencedId.substring(0, 1);
    String prefixId = id.substring(0, 1);
    switch (prefixId) {
      case buildingPrefix -> {throw new InvalidArgumentsException("Id " + id + "belongs to a building but a building " +
          "cannot have a parent.");
      }
      case roomPrefix -> {
        if(referencedId.matches(BIN_PATTERN) && checkParentOfRoom(referencedId)) {
          return true;
        } else {
          throw new InvalidArgumentsException(id + " does not match pattern ^b-\\d+$");
        }
      }
      case componentPrefix -> {
        if( (referencedId.matches(BIN_PATTERN) || referencedId.matches(RIN_PATTERN) )
            && checkParentOfComponent(referencedId, prefixReferenced) ) {
          return true;
        } else {
          throw new InvalidArgumentsException(id + " does not match pattern ^b-\\d+$ or ^r-\\d+$");
        }
      }
      case notificationPrefix -> {
        if(referencedId.matches(PIN_PATTERN)) {
          return true;
        } else {
          throw new InvalidArgumentsException(id + " does not match pattern ^n-\\d+$");
        }
      }
      default -> {return false;}
    }
  }


  /**
   * Checks whether room parent is actually a building
   * @param parentId
   * @return Optional.of("room") if true or Optional.empty() otherwise
   */
  private boolean checkParentOfRoom(String parentId) throws ResourceNotFoundException {
    if(buildingRepository.findById(parentId).isPresent()) {
      return true;
    } else {
      throw new ResourceNotFoundException("Parent is building but there is no building with this parentId (" + parentId
          + ") in the database.");
    }
  }


  /**
   * Checks whether parentId of a component references a building or a room
   * @param parentId
   * @param parentPrefix
   * @return
   */
  private boolean checkParentOfComponent(String parentId, String parentPrefix) throws ResourceNotFoundException {
    switch (parentPrefix) {
      case roomPrefix -> {
        if(roomRepository.findById(parentId).isPresent()) {
          return true;
        } else {
          throw new ResourceNotFoundException("Parent is room but there is no room with this parentId (" + parentId +
          ") in the database.");
          //todo so bei allen machen
        }
      }
      case buildingPrefix -> {
        if(buildingRepository.findById(parentId).isPresent()) {
          return true;
        } else {
          throw new ResourceNotFoundException("Parent is building but there is no building with this parentId (" +
              parentId + ") in the database.");
        }
      }
      default -> { return false; }
    }
  }






}
