package edu.kit.tm.cm.smartcampus.building.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions.ResourceNotFoundException;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import lombok.NoArgsConstructor;
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

  // singles
  public static final String SPACE = " ";
  public static final String COLON = ": ";
  public static final String NULL = "null";
  public static final String ROOM = "room";
  public static final String BUILDING = "building";
  public static final String NOTIFICATION = "notification";
  public static final String COMPONENT = "component";
  // entity variable names
  public static final String IDENTIFICATION_NUMBER_NAME = "identification_number";
  public static final String PARENT_IDENTIFICATION_NUMBER_NAME = "parent_identification_number";
  public static final String LONGITUDE_NAME = "longitude";
  public static final String LATITUDE_NAME = "latitude";
  public static final String ROOM_NAME = "room_name";
  public static final String ROOM_NUMBER = "room_number";
  public static final String BUILDING_NAME = "building_name";
  public static final String BUILDING_NUMBER = "building_number";
  public static final String ROOM_TYPE_NAME = "room_type";
  public static final String CAMPUS_LOCATION_NAME = "campus_location";
  public static final String COMPONENT_TYPE_NAME = "component_type";
  public static final String FLOOR_NAME = "floor";
  public static final String COMPONENT_DESCRIPTION_NAME = "component_description";
  public static final String NOTIFICATION_DESCRIPTION_NAME = "notification_description";
  public static final String NOTIFICATION_TITLE_NAME = "notification_title";
  public static final String CREATION_TIME_NAME = "creation_time";
  public static final String FLOORS_NAME = "floors";
  public static final String COORDINATES_NAME = "coordinates";
  // messages
  public static final String SHOULD_MATCH_MESSAGE = "should match: %s";
  public static final String SHOULD_BE_BETWEEN_MESSAGE = "should be between %s and %s";
  public static final String SHOULD_NOT_BE_EMPTY_MESSAGE = "should not be empty";
  public static final String SHOULD_NOT_BE_NULL_MESSAGE = "should not be null";
  // patterns
  public static final String BIN_PATTERN = "^b-\\d+$";
  public static final String RIN_PATTERN = "^r-\\d+$";
  public static final String CIN_PATTERN = "^c-\\d+$";
  public static final String NIN_PATTERN = "^n-\\d+$";
  public static final String BIN_RIN_PATTERN = BIN_PATTERN + "|" + RIN_PATTERN;
  public static final String BIN_RIN_CIN_PATTERN =
          BIN_PATTERN + "|" + RIN_PATTERN + "|" + CIN_PATTERN;
  public static final String FLOOR_HAS_TO_BE_BETWEEN_MESSAGE = "floor has to be between: ";
  public static final String AND = " and ";
  private static final double MAX_LATITUDE_VALUE = 90;
  private static final double MIN_LATITUDE_VALUE = -90;
  private static final double MAX_LONGITUDE_VALUE = 180;
  private static final double MIN_LONGITUDE_VALUE = -180;
  private final BuildingRepository buildingRepository;
  private final RoomRepository roomRepository;
  private final ComponentRepository componentRepository;
  private final NotificationRepository notificationRepository;

  /**
   * Instantiates a new validator.
   *
   * @param buildingRepository     the building repository in which all building are saved
   * @param roomRepository         the room repository in which all room are saved
   * @param componentRepository    the component repository in which all component are saved
   * @param notificationRepository the notification repository in which all notification are saved
   */
  protected Validator(BuildingRepository buildingRepository, RoomRepository roomRepository,
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
    InvalidArgumentsStringBuilder invalidArgumentsStringBuilder =
            new InvalidArgumentsStringBuilder();
    boolean valid = true;

    for (Map.Entry<String, Object> entry : objects.entrySet()) {
      if (entry.getValue() == null) {
        invalidArgumentsStringBuilder.appendMessage(entry.getKey(), NULL,
                SHOULD_NOT_BE_NULL_MESSAGE, true);
        valid = false;
      }
    }

    if (!valid) {
      throw new InvalidArgumentsException(invalidArgumentsStringBuilder.build());
    }
  }

  /**
   * Validates weather Strings are not empty or not.
   *
   * @param strings Map of strings to be checked and their names (key=name, value=string)
   */
  protected void validateNotEmpty(Map<String, String> strings) {
    InvalidArgumentsStringBuilder invalidArgumentsStringBuilder =
            new InvalidArgumentsStringBuilder();
    boolean valid = true;

    for (Map.Entry<String, String> entry : strings.entrySet()) {
      if (entry.getValue().isEmpty()) {
        invalidArgumentsStringBuilder.appendMessage(entry.getKey(), entry.getValue(),
                SHOULD_NOT_BE_EMPTY_MESSAGE, true);
        valid = false;
      }
    }

    if (!valid) {
      throw new InvalidArgumentsException(invalidArgumentsStringBuilder.build());
    }
  }

  /**
   * Validates weather Strings match given regexes or not.
   *
   * @param strings Map of strings and their regexes to be checked and their names (key=name,
   *                value=pair of string and regex)
   */
  protected void validateMatchesRegex(Map<String, Pair<String, String>> strings) {
    InvalidArgumentsStringBuilder invalidArgumentsStringBuilder =
            new InvalidArgumentsStringBuilder();
    boolean valid = true;

    for (Map.Entry<String, Pair<String, String>> entry : strings.entrySet()) {
      if (!entry.getValue().getFirst().matches(entry.getValue().getSecond())) {
        invalidArgumentsStringBuilder.appendMessage(entry.getKey(), entry.getValue().getFirst(),
                String.format(SHOULD_MATCH_MESSAGE, entry.getValue().getSecond()), true);
        valid = false;
      }
    }

    if (!valid) {
      throw new InvalidArgumentsException(invalidArgumentsStringBuilder.build());
    }
  }

  /**
   * Validates weather coordinates have valid latitude and longitude values or not.
   *
   * @param coordinates coordinates to be checked mapped by their names (key = name,
   *                    value=<latitude, longitude>)
   */
  protected void validateCoordinates(Map<String, Pair<Double, Double>> coordinates) {
    InvalidArgumentsStringBuilder invalidArgumentsStringBuilder =
            new InvalidArgumentsStringBuilder();
    boolean valid = true;

    for (Map.Entry<String, Pair<Double, Double>> entry : coordinates.entrySet()) {
      if (entry.getValue().getFirst() > MAX_LATITUDE_VALUE || entry.getValue().getFirst() < MIN_LATITUDE_VALUE) {
        invalidArgumentsStringBuilder.appendMessage(entry.getKey() + SPACE + LATITUDE_NAME,
                Double.toString(entry.getValue().getFirst()),
                String.format(SHOULD_BE_BETWEEN_MESSAGE, MIN_LATITUDE_VALUE, MAX_LATITUDE_VALUE),
                true);
        valid = false;
      }
      if (entry.getValue().getSecond() > MAX_LONGITUDE_VALUE || entry.getValue().getSecond() < MIN_LONGITUDE_VALUE) {
        invalidArgumentsStringBuilder.appendMessage(entry.getKey() + SPACE + LONGITUDE_NAME,
                Double.toString(entry.getValue().getSecond()),
                String.format(SHOULD_BE_BETWEEN_MESSAGE, MIN_LONGITUDE_VALUE,
                        MAX_LONGITUDE_VALUE), true);
        valid = false;
      }
    }

    if (!valid) {
      throw new InvalidArgumentsException(invalidArgumentsStringBuilder.build());
    }
  }

  /**
   * Validates weather floors have valid attributes or not.
   *
   * @param maxAndMinFloor max and min floor number to be checked and their names (key = name,
   *                       value=<minFloor, maxFloor>)
   */
  public void validateFloorValues(Map<String, Pair<Integer, Integer>> maxAndMinFloor) {
    InvalidArgumentsStringBuilder invalidArgumentsStringBuilder =
            new InvalidArgumentsStringBuilder();
    boolean valid = true;

    for (Map.Entry<String, Pair<Integer, Integer>> entry : maxAndMinFloor.entrySet()) {
      if (entry.getValue().getSecond() < entry.getValue().getFirst()) {
        invalidArgumentsStringBuilder.appendMessage(entry.getKey() + " highest floor",
                entry.getValue().getSecond() + "",
                "should be higher than lowest floor: " + entry.getValue().getFirst(), true);
        valid = false;
      }
    }

    if (!valid) {
      throw new InvalidArgumentsException(invalidArgumentsStringBuilder.build());
    }
  }

  /**
   * Validates weather a given room floor is valid or not.
   *
   * @param floor max and min floor number to be checked and their names (key = name,
   *              value=<minFloor, maxFloor>)
   */
  protected void validateValidRoomFloor(int floor, String parentIdentificationNumber) {
    InvalidArgumentsStringBuilder invalidArgumentsStringBuilder =
            new InvalidArgumentsStringBuilder();
    boolean valid = true;

    Building building = buildingRepository.findById(parentIdentificationNumber).get();
    if (floor < building.getLowestFloor() || floor > building.getHighestFloor()) {
      valid = false;
      invalidArgumentsStringBuilder.appendMessage(FLOOR_NAME, Integer.toString(floor),
              FLOOR_HAS_TO_BE_BETWEEN_MESSAGE + building.getLowestFloor() + AND + building.getHighestFloor(), true);
    }
    if (!valid) {
      throw new InvalidArgumentsException(invalidArgumentsStringBuilder.build());
    }
  }

  /**
   * Validate if entity exists.
   *
   * @param inputIdentificationNumber the input identification number
   * @param name                      the name of the given value
   */
  protected void validateExists(String inputIdentificationNumber, String name) {
    Collection<CrudRepository<?, String>> repositories = List.of(buildingRepository,
            roomRepository, componentRepository, notificationRepository);
    boolean found = false;
    for (CrudRepository<?, String> repository : repositories) {
      if (repository.existsById(inputIdentificationNumber)) {
        found = true;
      }
    }
    if (!found) {
      throw new ResourceNotFoundException(name, inputIdentificationNumber);
    }
  }

  /**
   * Validate a given identification number for requests containing only the identification number.
   *
   * @param identificationNumber the identification number
   */
  public void validate(String identificationNumber) {
    validateNotNull(Map.of(IDENTIFICATION_NUMBER_NAME, identificationNumber));
    validateMatchesRegex(Map.of(IDENTIFICATION_NUMBER_NAME, Pair.of(identificationNumber,
            getValidateRegex())));
    validateExists(identificationNumber, IDENTIFICATION_NUMBER_NAME);
  }

  /**
   * Gets validate regex for the {@link Validator#validate(String)} method.
   *
   * @return the validate regex
   */
  protected abstract String getValidateRegex();

  /**
   * Validate create operation.
   *
   * @param object the object to be validated
   */
  public abstract void validateCreate(T object);

  /**
   * Validate update operation.
   *
   * @param object the object to be validated
   */
  public abstract void validateUpdate(T object);

  @NoArgsConstructor
  private static class InvalidArgumentsStringBuilder {

    private static final String COLON = ": ";
    private static final String LEFT_PARENTHESIS = "(";
    private static final String RIGHT_PARENTHESIS = ")";
    private static final String COMMA = ", ";

    private final StringBuilder stringBuilder = new StringBuilder();

    /**
     * Append error message.
     *
     * @param name    the name
     * @param input   the input
     * @param hint    the hint
     * @param hasHint if a hint is provided
     */
    public void appendMessage(String name, String input, String hint, boolean hasHint) {
      if (stringBuilder.isEmpty()) {
        if (hasHint) {
          appendFirstIterationWithHint(name, input, hint);
        } else {
          appendFirstIterationWithoutHint(name, input);
        }
      } else if (hasHint) {
        appendWithHint(name, input, hint);
      } else {
        appendWithoutHint(name, input);
      }
    }

    /**
     * Build error message string.
     *
     * @return the built string
     */
    public String build() {
      return stringBuilder.toString();
    }

    private void appendWithHint(String name, String input, String hint) {
      stringBuilder.append(COMMA).append(name).append(COLON).append(input).append(LEFT_PARENTHESIS).append(hint).append(RIGHT_PARENTHESIS);
    }

    private void appendWithoutHint(String name, String input) {
      stringBuilder.append(COMMA).append(name).append(COLON).append(input);
    }

    private void appendFirstIterationWithoutHint(String name, String input) {
      stringBuilder.append(name).append(COLON).append(input);
    }

    private void appendFirstIterationWithHint(String name, String input, String hint) {
      stringBuilder.append(name).append(COLON).append(input).append(LEFT_PARENTHESIS).append(hint).append(RIGHT_PARENTHESIS);
    }
  }
}
