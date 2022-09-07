package edu.kit.tm.cm.smartcampus.building;

import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Floors;
import edu.kit.tm.cm.smartcampus.building.logic.model.GeographicalLocation;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;

/**
 * This Class provides test utils.
 */
public final class TestUtils {

  /**
   * Checks whether two buildings are equal (except for their identification number) or not.
   *
   * @param building1 the first building1 to be checked
   * @param building2 the second building1 to be checked
   * @return whether they are equal or not
   */
  public static boolean buildingsAreEqual(Building building1, Building building2) {
    if (building1 == building2) {
      return true;
    }
    if (building1 == null || building2 == null) {
      return false;
    }

    if (building1.getCampusLocation() != building2.getCampusLocation()) {
      return false;
    }
    if (!building1.getName().equals(building2.getName())) {
      return false;
    }
    if (!building1.getAddress().equals(building2.getAddress())) {
      return false;
    }
    if (!building1.getNumber().equals(building2.getNumber())) {
      return false;
    }
    if (!geographicalLocationsAreEqual(building1.getGeographicalLocation(),
        building2.getGeographicalLocation())) {
      return false;
    }
    return floorsAreEqual(building1.getFloors(), building2.getFloors());
  }

  /**
   * Checks whether two rooms are equal (except for their identification number) or not.
   *
   * @param room1 the first room to be checked
   * @param room2 the second room to be checked
   * @return whether they are equal or not
   */
  public static boolean roomsAreEqual(Room room1, Room room2) {
    if (room1 == room2) {
      return true;
    }
    if (room1 == null || room2 == null) {
      return false;
    }

    if (room1.getFloor() != room2.getFloor()) {
      return false;
    }
    if (!room1.getParentIdentificationNumber().equals(room2.getParentIdentificationNumber())) {
      return false;
    }
    if (!room1.getName().equals(room2.getName())) {
      return false;
    }
    if (!room1.getNumber().equals(room2.getNumber())) {
      return false;
    }
    if (room1.getType() != room2.getType()) {
      return false;
    }
    return geographicalLocationsAreEqual(room1.getGeographicalLocation(),
        room2.getGeographicalLocation());
  }

  /**
   * Checks whether two components are equal (except for their identification number) or not.
   *
   * @param component1 the first component to be checked
   * @param component2 the second component to be checked
   * @return whether they are equal or not
   */
  public static boolean componentsAreEqual(Component component1, Component component2) {
    if (component1 == component2) {
      return true;
    }
    if (component1 == null || component2 == null) {
      return false;
    }

    if (!component1.getParentIdentificationNumber()
        .equals(component2.getParentIdentificationNumber())) {
      return false;
    }
    if (component1.getType() != component2.getType()) {
      return false;
    }
    if (!component1.getDescription().equals(component2.getDescription())) {
      return false;
    }
    return geographicalLocationsAreEqual(component1.getGeographicalLocation(),
        component2.getGeographicalLocation());
  }

  /**
   * Checks whether two notifications are equal (except for their identification number, creation-
   * and modification time!) or not.
   *
   * @param notification1 the first notification to be checked
   * @param notification2 the second notification to be checked
   * @return whether they are equal or not
   */
  public static boolean notificationsAreEqual(Notification notification1,
      Notification notification2) {
    if (notification1 == notification2) {
      return true;
    }
    if (notification1 == null || notification2 == null) {
      return false;
    }

    if (!notification1.getParentIdentificationNumber()
        .equals(notification2.getParentIdentificationNumber())) {
      return false;
    }
    if (!notification1.getTitle().equals(notification2.getTitle())) {
      return false;
    }
    return notification1.getDescription().equals(notification2.getDescription());
  }

  /**
   * Checks whether two geographical locations are equal (except for their identification number) or
   * not.
   *
   * @param geographicalLocation1 the first geographical location to be checked
   * @param geographicalLocation2 the second geographical location to be checked
   * @return whether they are equal or not
   */
  public static boolean geographicalLocationsAreEqual(GeographicalLocation geographicalLocation1,
      GeographicalLocation geographicalLocation2) {
    if (geographicalLocation1 == geographicalLocation2) {
      return true;
    }
    if (geographicalLocation1 == null || geographicalLocation2 == null) {
      return false;
    }

    if (Double.compare(geographicalLocation1.getLatitude(), geographicalLocation2.getLatitude())
        != 0) {
      return false;
    }
    return Double.compare(geographicalLocation1.getLongitude(),
        geographicalLocation2.getLongitude()) == 0;
  }

  /**
   * Checks whether two floors objects equal (except for their identification number) or not.
   *
   * @param floors1 the first floors object to be checked
   * @param floors2 the second floors object to be checked
   * @return whether they are equal or not
   */
  public static boolean floorsAreEqual(Floors floors1, Floors floors2) {
    if (floors1 == floors2) {
      return true;
    }
    if (floors1 == null || floors2 == null) {
      return false;
    }

    if (floors1.getHighestFloor() != floors2.getHighestFloor()) {
      return false;
    }
    return floors1.getLowestFloor() == floors2.getLowestFloor();
  }

}
