package edu.kit.tm.cm.smartcampus.building.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions.ResourceNotFoundException;
import edu.kit.tm.cm.smartcampus.building.logic.model.GeographicalLocation;
import edu.kit.tm.cm.smartcampus.building.utils;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Pair;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Class representing an input validator which checks given inputs and thereby validates them and
 * throws the right exceptions when an input is invalid.
 */
public abstract class Validator<T> {

  public static final String VALIDATE_REGEX = "";

  private final BuildingRepository buildingRepository;
  private final RoomRepository roomRepository;
  private final ComponentRepository componentRepository;
  private final NotificationRepository notificationRepository;

  protected Validator(
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
   * Validates weather objects are not null or not.
   *
   * @param objects Map of objects to be checked and their names (key=name, value=object)
   */
  protected void validateNotNull(Map<String, Object> objects) {
    InvalidArgumentsException invalidArgumentsException = new InvalidArgumentsException();
    boolean valid = true;

    for (Map.Entry<String, Object> entry : objects.entrySet()) {
      if (entry.getValue() == null) {
        invalidArgumentsException.appendWrongArguments(
            entry.getKey(), "null", "should not be null", true);
        valid = false;
      }
    }

    if (!valid) {
      throw invalidArgumentsException;
    }
  }

  /**
   * Validates weather Strings are not empty or not.
   *
   * @param strings Map of strings to be checked and their names (key=name, value=string)
   */
  protected void validateNotEmpty(Map<String, String> strings) {
    InvalidArgumentsException invalidArgumentsException = new InvalidArgumentsException();
    boolean valid = true;

    for (Map.Entry<String, String> entry : strings.entrySet()) {
      if (!entry.getValue().isEmpty()) {
        invalidArgumentsException.appendWrongArguments(
            entry.getKey() + ": ", entry.getValue(), "should not be empty", true);
        valid = false;
      }
    }

    if (!valid) {
      throw invalidArgumentsException;
    }
  }

  /**
   * Validates weather Strings match given regexes or not.
   *
   * @param strings Map of strings and their regexes to be checked and their names (key=name,
   *     value=pair of string and regex)
   */
  protected void validateMatchesRegex(Map<String, Pair<String, String>> strings) {
    InvalidArgumentsException invalidArgumentsException = new InvalidArgumentsException();
    boolean valid = true;

    for (Map.Entry<String, Pair<String, String>> entry : strings.entrySet()) {
      if (!entry.getValue().getFirst().matches(entry.getValue().getSecond())) {
        invalidArgumentsException.appendWrongArguments(
            entry.getKey(),
            entry.getValue().getFirst(),
            "should match: " + entry.getValue().getSecond(),
            true);
        valid = false;
      }
    }

    if (!valid) {
      throw invalidArgumentsException;
    }
  }

  /**
   * Validates weather geographical locations have valid latitude and longitude values or not.
   *
   * @param geographicalLocations Map of geographical locations to be checked and their names (key =
   *     name, value=geographical location)
   */
  protected void validateGeographicalLocation(
      Map<String, GeographicalLocation> geographicalLocations) {
    InvalidArgumentsException invalidArgumentsException = new InvalidArgumentsException();
    boolean valid = true;

    for (Map.Entry<String, GeographicalLocation> entry : geographicalLocations.entrySet()) {
      if (entry.getValue().getLatitude() > 90.0 || entry.getValue().getLatitude() < -90.0) {
        invalidArgumentsException.appendWrongArguments(
            entry.getKey() + " latitude",
            entry.getValue().getLatitude() + "",
            "should be between -90.000000 and 90.000000",
            true);
        valid = false;
      }
      if (entry.getValue().getLongitude() > 180.0 || entry.getValue().getLongitude() < -180.0) {
        invalidArgumentsException.appendWrongArguments(
            entry.getKey() + " longitude",
            entry.getValue().getLongitude() + "",
            "should be between -180.000000 and 180.000000",
            true);
        valid = false;
      }
    }

    if (!valid) {
      throw invalidArgumentsException;
    }
  }

  protected void validateExists(String inputIdentificationNumber, String name)
      throws InvalidArgumentsException, ResourceNotFoundException {
    Collection<CrudRepository<?, String>> repositories =
        List.of(buildingRepository, roomRepository, componentRepository, notificationRepository);
    boolean found = false;
    for (CrudRepository<?, String> repository : repositories) {
      if (repository.existsById(inputIdentificationNumber)) {
        found = true;
      }
    }
    if (!found) {
      throw new ResourceNotFoundException(
          String.format(utils.RESOURCE_NOT_FOUND_MESSAGE, name, inputIdentificationNumber));
    }
  }


  public void validate(String identificationNumber) {
    validateNotNull(Map.of("identification_number", identificationNumber));
    validateMatchesRegex(Map.of("identification_number", Pair.of(identificationNumber, getValidateRegex())));
    validateExists(identificationNumber, "identification_number");
  }

  protected abstract String getValidateRegex();

  public abstract void validateCreate(T object);

  public abstract void validateUpdate(T object);

}
