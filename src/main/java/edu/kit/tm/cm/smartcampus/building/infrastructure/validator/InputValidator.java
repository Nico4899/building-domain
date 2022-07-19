package edu.kit.tm.cm.smartcampus.building.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions.InvalidArgumentsException;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Class representing an input validator which checks given inputs and thereby validates them and throws the right
 * exceptions when an input is invalid.
 */
@Component
@AllArgsConstructor
public class InputValidator {

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

  //TODO location validation + im service aufrufen

}
