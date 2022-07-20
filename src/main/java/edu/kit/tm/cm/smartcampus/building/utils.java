package edu.kit.tm.cm.smartcampus.building;

public final class utils {

  private utils() {}

  //Syntax
  public final static String SPACE = " ";

  public final static String TAB = "  ";

  public final static String COMMA = ",";

  public final static String OR = "or";


  //General
  public final static String IN = "in";

  public final static String BUILDING = "building";

  public final static String ROOM = "room";

  public final static String COMPONENT = "component";

  public final static String NOTIFICATION = "notification";

  public final static String PROBLEM = "problem";


  public final static String BIN_PATTERN = "^b-\\d+$";

  public final static String RIN_PATTERN = "^r-\\d+$";

  public final static String CIN_PATTERN = "^c-\\d+$";

  public final static String NIN_PATTERN = "^n-\\d+$";

  public final static String PIN_PATTERN = "^p-\\d+$";

  public final static String PARENT = "parent";

  public final static String REFERENCED = "referenced object";


  //Error messages
  public final static String NOT_FOUND = "Resource %s was not found.";

  public final static String PARENT_NOT_FOUND_ERROR = "Parent was not found.";

  public final static String PARENT_NOT_ALLOWED_ERROR = "Specified resource must not have a parent.";

  public final static String REFERENCED_NOT_FOUND_ERROR = "Referenced resource was not found.";

  public final static String OBJECTS_ARE_NULL = "One of the following objects is null:";

  public final static String RESOURCE_ALREADY_EXISTS = "This resource already exists.";



  //Hints
  public final static String EXPECTED_FORMAT = "Should match:";


}
