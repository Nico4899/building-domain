package edu.kit.tm.cm.smartcampus.building.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.building.GlobalBuildingStringCollection;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions.ResourceNotFoundException;
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

  public static final double MAX_LATITUDE_VALUE = 90;
  public static final double MIN_LATITUDE_VALUE = -90;
  public static final double MAX_LONGITUDE_VALUE = 180;
  public static final double MIN_LONGITUDE_VALUE = -180;

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
            entry.getKey(),
            GlobalBuildingStringCollection.NULL,
            GlobalBuildingStringCollection.SHOULD_NOT_BE_NULL_MESSAGE,
            true);
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
            entry.getKey() + GlobalBuildingStringCollection.COLON,
            entry.getValue(),
            GlobalBuildingStringCollection.SHOULD_NOT_BE_EMPTY_MESSAGE,
            true);
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
            String.format(
                GlobalBuildingStringCollection.SHOULD_MATCH_MESSAGE, entry.getValue().getSecond()),
            true);
        valid = false;
      }
    }

    if (!valid) {
      throw invalidArgumentsException;
    }
  }

  /**
   * Validates weather coordinates have valid latitude and longitude values or not.
   *
   * @param coordinates coordinates to be checked mapped by their names (key = name,
   *     value=<latitude, longitude>)
   */
  protected void validateCoordinates(Map<String, Pair<Double, Double>> coordinates) {
    InvalidArgumentsException invalidArgumentsException = new InvalidArgumentsException();
    boolean valid = true;

    for (Map.Entry<String, Pair<Double, Double>> entry : coordinates.entrySet()) {
      if (entry.getValue().getFirst() > MAX_LATITUDE_VALUE
          || entry.getValue().getFirst() < MIN_LATITUDE_VALUE) {
        invalidArgumentsException.appendWrongArguments(
            entry.getKey()
                + GlobalBuildingStringCollection.SPACE
                + GlobalBuildingStringCollection.LATITUDE_NAME,
            Double.toString(entry.getValue().getFirst()),
            String.format(
                GlobalBuildingStringCollection.SHOULD_BE_BETWEEN_MESSAGE,
                MIN_LATITUDE_VALUE,
                MAX_LATITUDE_VALUE),
            true);
        valid = false;
      }
      if (entry.getValue().getSecond() > MAX_LONGITUDE_VALUE
          || entry.getValue().getSecond() < MIN_LONGITUDE_VALUE) {
        invalidArgumentsException.appendWrongArguments(
            entry.getKey() + GlobalBuildingStringCollection.LONGITUDE_NAME,
            Double.toString(entry.getValue().getSecond()),
            String.format(
                GlobalBuildingStringCollection.SHOULD_BE_BETWEEN_MESSAGE,
                MIN_LONGITUDE_VALUE,
                MAX_LONGITUDE_VALUE),
            true);
        valid = false;
      }
    }

    if (!valid) {
      throw invalidArgumentsException;
    }
  }

  /**
   * Validates weather floors have valid attributes or not.
   *
   * @param maxAndMinFloor max and min floor number to be checked and their names (key = name,
   *     value=<minFloor, maxFloor>)
   */
  public void validateFloorValues(
      Map<String, Pair<Integer, Integer>> maxAndMinFloor) { // TODO cool formatieren
    InvalidArgumentsException invalidArgumentsException = new InvalidArgumentsException();
    boolean valid = true;

    for (Map.Entry<String, Pair<Integer, Integer>> entry : maxAndMinFloor.entrySet()) {
      if (entry.getValue().getSecond() < entry.getValue().getFirst()) {
        invalidArgumentsException.appendWrongArguments(
            entry.getKey() + " highest floor",
            entry.getValue().getSecond() + "",
            "should be higher than lowest floor: " + entry.getValue().getFirst(),
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
          String.format(
              GlobalBuildingStringCollection.RESOURCE_NOT_FOUND_ERROR_MESSAGE,
              name,
              inputIdentificationNumber));
    }
  }

  public void validate(String identificationNumber) {
    validateNotNull(
        Map.of(GlobalBuildingStringCollection.IDENTIFICATION_NUMBER_NAME, identificationNumber));
    validateMatchesRegex(
        Map.of(
            GlobalBuildingStringCollection.IDENTIFICATION_NUMBER_NAME,
            Pair.of(identificationNumber, getValidateRegex())));
    validateExists(identificationNumber, GlobalBuildingStringCollection.IDENTIFICATION_NUMBER_NAME);
  }

  protected abstract String getValidateRegex();

  public abstract void validateCreate(T object);

  public abstract void validateUpdate(T object);
}
