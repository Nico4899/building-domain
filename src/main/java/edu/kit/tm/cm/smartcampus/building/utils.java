package edu.kit.tm.cm.smartcampus.building;

public final class utils {

  // Syntax
  public static final String SPACE = " ";
  public static final String TAB = "  ";
  public static final String COMMA = ",";
  public static final String OR = "or";
  // General
  public static final String IN = "in";
  public static final String BUILDING = "building";
  public static final String ROOM = "room";
  public static final String COMPONENT = "component";
  public static final String NOTIFICATION = "notification";
  public static final String PROBLEM = "problem";
  // patterns
  public static final String BIN_PATTERN = "^b-\\d+$";
  public static final String RIN_PATTERN = "^r-\\d+$";
  public static final String CIN_PATTERN = "^c-\\d+$";
  public static final String NIN_PATTERN = "^n-\\d+$";
  public static final String PIN_PATTERN = "^p-\\d+$";

  // w/E bullshit
  public static final String PARENT = "parent";
  public static final String REFERENCED = "referenced object";
  // Error messages
  public static final String RESOURCE_NOT_FOUND_MESSAGE = "Resource [%s: %s] does not exist.";

  public static final String REFERENCED_NOT_FOUND_ERROR = "Referenced resource was not found.";
  public static final String OBJECTS_ARE_NULL = "One of the following objects is null:";
  public static final String RESOURCE_ALREADY_EXISTS = "This resource already exists.";
  // Hints
  public static final String EXPECTED_FORMAT = "Should match:";

  private utils() {}
}
