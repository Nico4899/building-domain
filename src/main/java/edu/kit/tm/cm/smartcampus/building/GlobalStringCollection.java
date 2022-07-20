package edu.kit.tm.cm.smartcampus.building;

public final class GlobalStringCollection {

  // modifiers
  public static final String SPACE = " ";

  // variable names
  public static final String IDENTIFICATION_NUMBER_NAME = "identification_number";
  public static final String PARENT_IDENTIFICATION_NUMBER_NAME = "parent_identification_number";
  public static final String REFERENCE_IDENTIFICATION_NUMBER_NAME = "reference_identification_number";
  public static final String LONGITUDE_NAME = "longitude";
  public static final String LATITUDE_NAME = "latitude";
  public static final String GEOGRAPHICAL_LOCATION_NAME = "geographical_location";



  // Error messages
  public static final String RESOURCE_NOT_FOUND_MESSAGE = "Resource [%s: %s] does not exist.";

  // patterns
  public static final String BIN_PATTERN = "^b-\\d+$";
  public static final String RIN_PATTERN = "^r-\\d+$";
  public static final String CIN_PATTERN = "^c-\\d+$";
  public static final String NIN_PATTERN = "^n-\\d+$";

  private GlobalStringCollection() {}
}
