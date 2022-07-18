package edu.kit.tm.cm.smartcampus.building.api.error;

import edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.building.infrastructure.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ServerExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(InvalidArgumentsException.class)
  protected ResponseEntity<Object> handleInvalidArgumentsException(
          RuntimeException exception, WebRequest request) {
    return handleExceptionInternal(
            exception, exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  protected ResponseEntity<Object> handleResourceNotFoundException(
          RuntimeException exception, WebRequest request) {
    return handleExceptionInternal(
            exception, exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }
}
