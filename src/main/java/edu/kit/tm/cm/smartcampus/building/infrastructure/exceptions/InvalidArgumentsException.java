package edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions;

/**
 * Exception thrown on invalid arguments.
 */
public class InvalidArgumentsException extends RuntimeException {

  public static final String COLON = ": ";
  public static final String LEFT_PARENTHESIS = "(";
  public static final String RIGHT_PARENTHESIS = ")";
  public static final String COMMA = ", ";
  private static final String INVALID_ARGUMENTS_EXCEPTION_MESSAGE =
          "Arguments are Invalid! Please check the following: %s";
  private String invalidArguments;

  public InvalidArgumentsException() {
    super();
  }

  public void appendWrongArguments(String name, String input, String hint, boolean hasHint) {
    if (invalidArguments.isBlank()) {
      if (hasHint) {
        invalidArguments = name + COLON + input + LEFT_PARENTHESIS + hint + RIGHT_PARENTHESIS;
      } else {
        invalidArguments = name + COLON + input;
      }
    }
    if (hasHint) {
      invalidArguments +=
              invalidArguments
                      + COMMA
                      + name
                      + COLON
                      + input
                      + LEFT_PARENTHESIS
                      + hint
                      + RIGHT_PARENTHESIS;
    } else {
      invalidArguments += invalidArguments + COMMA + name + COLON + input;
    }
  }

  @Override
  public String getMessage() {
    return String.format(INVALID_ARGUMENTS_EXCEPTION_MESSAGE, invalidArguments);
  }
}
