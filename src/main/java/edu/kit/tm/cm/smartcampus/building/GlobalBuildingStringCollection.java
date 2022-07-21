package edu.kit.tm.cm.smartcampus.building;

public final class GlobalBuildingStringCollection {

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
  public static final String GEOGRAPHICAL_LOCATION_NAME = "geographical_location";
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

  public static final String LOWEST_FLOOR_NAME = "lowest floor";

  public static final String HIGHEST_FLOOR_NAME = "highest floor";

  // messages
  public static final String SHOULD_MATCH_MESSAGE = "should match: %s";
  public static final String SHOULD_BE_BETWEEN_MESSAGE = "should be between %s and %s";
  public static final String SHOULD_NOT_BE_EMPTY_MESSAGE = "should not be empty";
  public static final String SHOULD_NOT_BE_NULL_MESSAGE = "should not be null";

  // Error messages
  public static final String RESOURCE_NOT_FOUND_ERROR_MESSAGE = "Resource [%s: %s] does not exist.";

  // patterns
  public static final String BIN_PATTERN = "^b-\\d+$";
  public static final String RIN_PATTERN = "^r-\\d+$";
  public static final String CIN_PATTERN = "^c-\\d+$";
  public static final String NIN_PATTERN = "^n-\\d+$";
  public static final String BIN_RIN_PATTERN = BIN_PATTERN + "|" + RIN_PATTERN;
  public static final String BIN_RIN_CIN_PATTERN = BIN_PATTERN + "|" + RIN_PATTERN + "|" + CIN_PATTERN;;


  private GlobalBuildingStringCollection() {}
}
