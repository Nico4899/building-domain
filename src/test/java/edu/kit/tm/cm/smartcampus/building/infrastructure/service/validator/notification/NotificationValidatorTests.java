package edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.notification;

import static org.mockito.Mockito.mock;

import edu.kit.tm.cm.smartcampus.building.api.controller.notification.dto.ServerCreateNotificationRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.notification.dto.ServerUpdateNotificationRequest;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.building.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.component.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.notification.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.room.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.error.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.error.exceptions.ResourceNotFoundException;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.Validator;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

public class NotificationValidatorTests {

  //Attributes
  private static final String NOTIFICATION_IDENTIFICATION_NUMBER = "n-1";
  private static final String INVALID_NOTIFICATION_IDENTIFICATION_NUMBER = "n1";
  private static final String NOT_EXISTING_NOTIFICATION_IDENTIFICATION_NUMBER = "n-42";
  private static final String NOTIFICATION_PARENT_BUILDING_IDENTIFICATION_NUMBER = "b-1";
  private static final String INVALID_NOTIFICATION_PARENT_BUILDING_IDENTIFICATION_NUMBER = "b1";
  private static final String NOT_EXISTING_NOTIFICATION_PARENT_BUILDING_IDENTIFICATION_NUMBER =
      "b-42";
  private static final String NOTIFICATION_PARENT_ROOM_IDENTIFICATION_NUMBER = "r-1";
  private static final String INVALID_NOTIFICATION_PARENT_ROOM_IDENTIFICATION_NUMBER = "r1";
  private static final String NOT_EXISTING_NOTIFICATION_PARENT_ROOM_IDENTIFICATION_NUMBER = "r-42";
  private static final String NOTIFICATION_PARENT_COMPONENT_IDENTIFICATION_NUMBER = "c-1";
  private static final String INVALID_NOTIFICATION_PARENT_COMPONENT_IDENTIFICATION_NUMBER = "c1";
  private static final String NOT_EXISTING_NOTIFICATION_PARENT_COMPONENT_IDENTIFICATION_NUMBER =
      "c-42";
  private static final String NOTIFICATION_TITLE = "Eine test Notification.";
  private static final String NOTIFICATION_DESCRIPTION = "Eine test Beschreibung, die sehr "
      + "aussagekräftig ist 0_0.";
  private static final String EMPTY_STRING = "";
  //Mocks
  private static final BuildingRepository BUILDING_REPOSITORY = mock(BuildingRepository.class);
  private static final RoomRepository ROOM_REPOSITORY = mock(RoomRepository.class);
  private static final ComponentRepository COMPONENT_REPOSITORY = mock(ComponentRepository.class);
  private static final NotificationRepository NOTIFICATION_REPOSITORY = mock(
      NotificationRepository.class);
  private static final NotificationValidator NOTIFICATION_VALIDATOR =
      new NotificationValidator(BUILDING_REPOSITORY, ROOM_REPOSITORY, COMPONENT_REPOSITORY,
          NOTIFICATION_REPOSITORY);

  @BeforeAll
  static void setUp() {
    Mockito.when(NOTIFICATION_REPOSITORY.existsById(NOTIFICATION_IDENTIFICATION_NUMBER))
        .thenReturn(true);
    Mockito.when(BUILDING_REPOSITORY.existsById(NOTIFICATION_PARENT_BUILDING_IDENTIFICATION_NUMBER))
        .thenReturn(true);
    Mockito.when(ROOM_REPOSITORY.existsById(NOTIFICATION_PARENT_ROOM_IDENTIFICATION_NUMBER))
        .thenReturn(true);
    Mockito.when(
            COMPONENT_REPOSITORY.existsById(NOTIFICATION_PARENT_COMPONENT_IDENTIFICATION_NUMBER))
        .thenReturn(true);

    Mockito.when(
            NOTIFICATION_REPOSITORY.existsById(NOT_EXISTING_NOTIFICATION_IDENTIFICATION_NUMBER))
        .thenReturn(false);
    Mockito.when(BUILDING_REPOSITORY.existsById(
        NOT_EXISTING_NOTIFICATION_PARENT_BUILDING_IDENTIFICATION_NUMBER)).thenReturn(false);
    Mockito.when(
            ROOM_REPOSITORY.existsById(NOT_EXISTING_NOTIFICATION_PARENT_ROOM_IDENTIFICATION_NUMBER))
        .thenReturn(false);
    Mockito.when(COMPONENT_REPOSITORY.existsById(
        NOT_EXISTING_NOTIFICATION_PARENT_COMPONENT_IDENTIFICATION_NUMBER)).thenReturn(false);

  }

  @Test
  void getValidateRegex_ShouldReturnBINPattern() {
    Assertions.assertEquals(Validator.NIN_PATTERN, NOTIFICATION_VALIDATOR.getValidateIdentificationNumberRegex());
  }

  @ParameterizedTest
  @MethodSource("provideValidServerCreateNotificationRequests")
  void validateCreate_ShouldNotThrowException(
      ServerCreateNotificationRequest serverCreateNotificationRequest) {
    Assertions.assertDoesNotThrow(
        () -> NOTIFICATION_VALIDATOR.validateCreate(serverCreateNotificationRequest));
  }

  @ParameterizedTest
  @MethodSource("provideInvalidArgumentServerCreateNotificationRequests")
  void validateCreate_ShouldThrowInvalidArgumentsException(
      ServerCreateNotificationRequest serverCreateNotificationRequest) {
    Assertions.assertThrows(InvalidArgumentsException.class,
        () -> NOTIFICATION_VALIDATOR.validateCreate(serverCreateNotificationRequest));
  }

  @ParameterizedTest
  @MethodSource("provideInvalidResourceServerCreateNotificationRequests")
  void validateCreate_ShouldThrowResourceNotFoundException(
      ServerCreateNotificationRequest serverCreateNotificationRequest) {
    Assertions.assertThrows(ResourceNotFoundException.class,
        () -> NOTIFICATION_VALIDATOR.validateCreate(serverCreateNotificationRequest));
  }

  @ParameterizedTest
  @MethodSource("provideValidServerUpdateNotificationRequests")
  void validateUpdate_ShouldNotThrowException(
      ServerUpdateNotificationRequest serverUpdateNotificationRequest) {
    Assertions.assertDoesNotThrow(
        () -> NOTIFICATION_VALIDATOR.validateUpdate(serverUpdateNotificationRequest));
  }

  @ParameterizedTest
  @MethodSource("provideInvalidArgumentsServerUpdateNotificationRequests")
  void validateUpdate_ShouldThrowInvalidArgumentsException(
      ServerUpdateNotificationRequest serverUpdateNotificationRequest) {
    Assertions.assertThrows(InvalidArgumentsException.class,
        () -> NOTIFICATION_VALIDATOR.validateUpdate(serverUpdateNotificationRequest));
  }

  @ParameterizedTest
  @MethodSource("provideInvalidResourceServerUpdateNotificationRequests")
  void validateUpdate_ShouldThrowResourceNotFoundException(
      ServerUpdateNotificationRequest serverUpdateNotificationRequest) {
    Assertions.assertThrows(ResourceNotFoundException.class,
        () -> NOTIFICATION_VALIDATOR.validateUpdate(serverUpdateNotificationRequest));
  }

  private static Stream<Arguments> provideValidServerCreateNotificationRequests() {
    return Stream.of(
        Arguments.of(
            new ServerCreateNotificationRequest(NOTIFICATION_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                NOTIFICATION_TITLE, NOTIFICATION_DESCRIPTION)),
        Arguments.of(
            new ServerCreateNotificationRequest(NOTIFICATION_PARENT_ROOM_IDENTIFICATION_NUMBER,
                NOTIFICATION_TITLE, NOTIFICATION_DESCRIPTION)),
        Arguments.of(
            new ServerCreateNotificationRequest(NOTIFICATION_PARENT_COMPONENT_IDENTIFICATION_NUMBER,
                NOTIFICATION_TITLE, NOTIFICATION_DESCRIPTION))
    );
  }

  private static Stream<Arguments> provideInvalidArgumentServerCreateNotificationRequests() {
    return Stream.of(
        //invalid because of null:
        Arguments.of(
            (ServerCreateNotificationRequest) null),
        Arguments.of(
            new ServerCreateNotificationRequest(null, null, null)),
        Arguments.of(
            new ServerCreateNotificationRequest(null,
                NOTIFICATION_TITLE, NOTIFICATION_DESCRIPTION)),
        Arguments.of(
            new ServerCreateNotificationRequest(NOTIFICATION_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                null, NOTIFICATION_DESCRIPTION)),
        Arguments.of(
            new ServerCreateNotificationRequest(NOTIFICATION_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                NOTIFICATION_TITLE, null)),
        //invalid because of empty
        Arguments.of(
            new ServerCreateNotificationRequest(NOTIFICATION_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                EMPTY_STRING, NOTIFICATION_DESCRIPTION)),
        Arguments.of(
            new ServerCreateNotificationRequest(NOTIFICATION_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                NOTIFICATION_TITLE, EMPTY_STRING)),
        //invalid because of regex
        Arguments.of(
            new ServerCreateNotificationRequest(
                INVALID_NOTIFICATION_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                NOTIFICATION_TITLE, NOTIFICATION_DESCRIPTION)),
        Arguments.of(
            new ServerCreateNotificationRequest(
                INVALID_NOTIFICATION_PARENT_ROOM_IDENTIFICATION_NUMBER,
                NOTIFICATION_TITLE, NOTIFICATION_DESCRIPTION)),
        Arguments.of(
            new ServerCreateNotificationRequest(
                INVALID_NOTIFICATION_PARENT_COMPONENT_IDENTIFICATION_NUMBER,
                NOTIFICATION_TITLE, NOTIFICATION_DESCRIPTION))
    );
  }

  private static Stream<Arguments> provideInvalidResourceServerCreateNotificationRequests() {
    return Stream.of(
        Arguments.of(
            new ServerCreateNotificationRequest(
                NOT_EXISTING_NOTIFICATION_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                NOTIFICATION_TITLE, NOTIFICATION_DESCRIPTION)),
        Arguments.of(
            new ServerCreateNotificationRequest(
                NOT_EXISTING_NOTIFICATION_PARENT_ROOM_IDENTIFICATION_NUMBER,
                NOTIFICATION_TITLE, NOTIFICATION_DESCRIPTION)),
        Arguments.of(
            new ServerCreateNotificationRequest(
                NOT_EXISTING_NOTIFICATION_PARENT_COMPONENT_IDENTIFICATION_NUMBER,
                NOTIFICATION_TITLE, NOTIFICATION_DESCRIPTION))
    );
  }

  private static Stream<Arguments> provideValidServerUpdateNotificationRequests() {
    return Stream.of(
        Arguments.of(
            new ServerUpdateNotificationRequest(
                NOTIFICATION_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                NOTIFICATION_TITLE, NOTIFICATION_DESCRIPTION, NOTIFICATION_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateNotificationRequest(
                NOTIFICATION_PARENT_ROOM_IDENTIFICATION_NUMBER,
                NOTIFICATION_TITLE, NOTIFICATION_DESCRIPTION, NOTIFICATION_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateNotificationRequest(
                NOTIFICATION_PARENT_COMPONENT_IDENTIFICATION_NUMBER,
                NOTIFICATION_TITLE, NOTIFICATION_DESCRIPTION, NOTIFICATION_IDENTIFICATION_NUMBER))
    );
  }

  private static Stream<Arguments> provideInvalidArgumentsServerUpdateNotificationRequests() {
    return Stream.of(
        //invalid because of null
        Arguments.of(
            (ServerUpdateNotificationRequest) null),
        Arguments.of(
            new ServerUpdateNotificationRequest(null, null, null, null)),
        Arguments.of(
            new ServerUpdateNotificationRequest(
                null,
                NOTIFICATION_TITLE, NOTIFICATION_DESCRIPTION, NOTIFICATION_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateNotificationRequest(
                NOTIFICATION_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                null, NOTIFICATION_DESCRIPTION, NOTIFICATION_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateNotificationRequest(
                NOTIFICATION_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                NOTIFICATION_TITLE, null, NOTIFICATION_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateNotificationRequest(
                NOTIFICATION_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                NOTIFICATION_TITLE, NOTIFICATION_DESCRIPTION, null),
            //invalid because of empty
            Arguments.of(
                new ServerUpdateNotificationRequest(
                    NOTIFICATION_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                    EMPTY_STRING, NOTIFICATION_DESCRIPTION, NOTIFICATION_IDENTIFICATION_NUMBER)),
            Arguments.of(
                new ServerUpdateNotificationRequest(
                    NOTIFICATION_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                    NOTIFICATION_TITLE, EMPTY_STRING, NOTIFICATION_IDENTIFICATION_NUMBER)),
            //invalid because of regex
            Arguments.of(
                new ServerUpdateNotificationRequest(
                    INVALID_NOTIFICATION_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                    NOTIFICATION_TITLE, NOTIFICATION_DESCRIPTION,
                    NOTIFICATION_IDENTIFICATION_NUMBER)),
            Arguments.of(
                new ServerUpdateNotificationRequest(
                    INVALID_NOTIFICATION_PARENT_ROOM_IDENTIFICATION_NUMBER,
                    NOTIFICATION_TITLE, NOTIFICATION_DESCRIPTION,
                    NOTIFICATION_IDENTIFICATION_NUMBER)),
            Arguments.of(
                new ServerUpdateNotificationRequest(
                    INVALID_NOTIFICATION_PARENT_COMPONENT_IDENTIFICATION_NUMBER,
                    NOTIFICATION_TITLE, NOTIFICATION_DESCRIPTION,
                    NOTIFICATION_IDENTIFICATION_NUMBER)),
            Arguments.of(
                new ServerUpdateNotificationRequest(
                    NOTIFICATION_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                    NOTIFICATION_TITLE, NOTIFICATION_DESCRIPTION,
                    INVALID_NOTIFICATION_IDENTIFICATION_NUMBER)))
    );
  }

  private static Stream<Arguments> provideInvalidResourceServerUpdateNotificationRequests() {
    return Stream.of(
        Arguments.of(
            new ServerUpdateNotificationRequest(
                NOT_EXISTING_NOTIFICATION_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                NOTIFICATION_TITLE, NOTIFICATION_DESCRIPTION, NOTIFICATION_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateNotificationRequest(
                NOT_EXISTING_NOTIFICATION_PARENT_ROOM_IDENTIFICATION_NUMBER,
                NOTIFICATION_TITLE, NOTIFICATION_DESCRIPTION, NOTIFICATION_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateNotificationRequest(
                NOT_EXISTING_NOTIFICATION_PARENT_COMPONENT_IDENTIFICATION_NUMBER,
                NOTIFICATION_TITLE, NOTIFICATION_DESCRIPTION, NOTIFICATION_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateNotificationRequest(
                NOTIFICATION_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                NOTIFICATION_TITLE, NOTIFICATION_DESCRIPTION,
                NOT_EXISTING_NOTIFICATION_IDENTIFICATION_NUMBER))
    );
  }

}
