package edu.kit.tm.cm.smartcampus.building.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions.ResourceNotFoundException;
import edu.kit.tm.cm.smartcampus.building.logic.model.GeographicalLocation;
import edu.kit.tm.cm.smartcampus.building.GlobalStringCollection;
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

  public static final int MAX_LATITUDE_VALUE = 90;
  public static final int MIN_LATITUDE_VALUE = -90;
  public static final int MAX_LONGITUDE_VALUE = 180;
  public static final double MIN_LONGITUDE_VALUE = -180.0;

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
      if (entry.getValue().getLatitude() > MAX_LATITUDE_VALUE || entry.getValue().getLatitude() < MIN_LATITUDE_VALUE) {
        invalidArgumentsException.appendWrongArguments(
            entry.getKey() + GlobalStringCollection.SPACE + GlobalStringCollection.LATITUDE_NAME,
          Double.toString(entry.getValue().getLatitude()),
            "should be between -90.000000 and 90.000000",
            true);
        valid = false;
      }
      if (entry.getValue().getLongitude() > MAX_LONGITUDE_VALUE || entry.getValue().getLongitude() < MIN_LONGITUDE_VALUE) {
        invalidArgumentsException.appendWrongArguments(
            entry.getKey() + GlobalStringCollection.LONGITUDE_NAME,
            Double.toString(entry.getValue().getLongitude()),
            "should be between -180.000000 and 180.000000",
            true);
        valid = false;
      }
    }

    if (!valid) {
      throw invalidArgumentsException;
    }
  }

  protected void validateExists(String inputIdentificationNumber, String name) {
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
          String.format(GlobalStringCollection.RESOURCE_NOT_FOUND_MESSAGE, name, inputIdentificationNumber));
    }
  }


  public void validate(String identificationNumber) {
    validateNotNull(Map.of(GlobalStringCollection.IDENTIFICATION_NUMBER_NAME, identificationNumber));
    validateMatchesRegex(Map.of(GlobalStringCollection.IDENTIFICATION_NUMBER_NAME, Pair.of(identificationNumber, getValidateRegex())));
    validateExists(identificationNumber, GlobalStringCollection.IDENTIFICATION_NUMBER_NAME);
  }

  protected abstract String getValidateRegex();

  public abstract void validateCreate(T object);

  public abstract void validateUpdate(T object);

}
