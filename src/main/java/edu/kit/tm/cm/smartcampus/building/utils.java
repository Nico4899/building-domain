package edu.kit.tm.cm.smartcampus.building;

public final class utils {

  private utils() {}

  //Syntax
  public final static String SPACE = " ";

  public final static String TAB = "  ";


  //General
  public final static String BUILDING = "building";

  public final static String ROOM = "room";

  public final static String COMPONENT = "component";

  public final static String NOTIFICATION = "notification";

  public final static String PROBLEM = "problem";


  public final static String BUILDING_PATTERN = "^b-\\d+$";

  public final static String ROOM_PATTERN = "^r-\\d+$";

  public final static String COMPONENT_PATTERN = "^c-\\d+$";

  public final static String NOTIFICATION_PATTERN = "^n-\\d+$";

  public final static String PROBLEM_PATTERN = "^p-\\d+$";


  //Error messages
  public final static String PARENT_NOT_FOUND_ERROR = "Parent was not found.";

  public final static String PARENT_NOT_ALLOWED_ERROR = "Specified object must not have a parent.";

  public final static String REFERENCED_NOT_FOUND_ERROR = "Referenced object was not found.";



}
