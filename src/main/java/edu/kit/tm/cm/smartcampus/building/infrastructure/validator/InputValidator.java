package edu.kit.tm.cm.smartcampus.building.infrastructure.validator;

import com.sun.istack.NotNull;
import edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.building.logic.model.GeographicalLocation;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Class representing an input validator which checks given inputs and thereby validates them and throws the right
 * exceptions when an input is invalid.
 */
@AllArgsConstructor
public abstract class InputValidator {

  /**
   * Validates weather objects are not null or not.
   *
   * @param objects Map of objects to be checked and their names (key=name, value=object)
   */
  public void validateNotNull(Map<String, Object> objects) {
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
  public void validateNotEmpty(Map<String, String> strings) {
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
   *                value=pair of string and regex)
   */
  public void validateMatchesRegex(Map<String, Pair<String, String>> strings) {
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
   * @param geographicalLocations Map of geographical locations to be checked and their names (key = name,
   *                              value=geographical location)
   */
  public void validateGeographicalLocation(Map<String, GeographicalLocation> geographicalLocations) {
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

}
