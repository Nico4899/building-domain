package edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.component;

import static org.mockito.Mockito.mock;

import edu.kit.tm.cm.smartcampus.building.api.controller.GeographicalLocationDTO;
import edu.kit.tm.cm.smartcampus.building.api.controller.component.dto.ServerCreateComponentRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.component.dto.ServerUpdateComponentRequest;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.building.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.component.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.notification.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.room.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.error.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.error.exceptions.ResourceNotFoundException;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.Validator;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

public class ComponentValidatorTests {

  //Attributes
  private static final String COMPONENT_IDENTIFICATION_NUMBER = "c-1";
  private static final String INVALID_COMPONENT_IDENTIFICATION_NUMBER = "c1";
  private static final String NOT_EXISTING_COMPONENT_IDENTIFICATION_NUMBER = "c-42";
  private static final Component.Type COMPONENT_TYPE = Component.Type.STAIRS;
  private static final String COMPONENT_DESCRIPTION = "Eine test Treppe.";
  private static final GeographicalLocationDTO COMPONENT_GEOGRAPHICAL_LOCATION =
      new GeographicalLocationDTO(42.123, 43.111111);
  private static final GeographicalLocationDTO INVALID_COMPONENT_GEOGRAPHICAL_LOCATION_1 =
      new GeographicalLocationDTO(42.123, Double.NaN);
  private static final GeographicalLocationDTO INVALID_COMPONENT_GEOGRAPHICAL_LOCATION_2 =
      new GeographicalLocationDTO(Double.POSITIVE_INFINITY, 43.111111);
  private static final GeographicalLocationDTO INVALID_COMPONENT_GEOGRAPHICAL_LOCATION_3 =
      new GeographicalLocationDTO(Double.NEGATIVE_INFINITY, 1);
  private static final GeographicalLocationDTO INVALID_COMPONENT_GEOGRAPHICAL_LOCATION_4 =
      new GeographicalLocationDTO(91, 43.111111);
  private static final GeographicalLocationDTO INVALID_COMPONENT_GEOGRAPHICAL_LOCATION_5 =
      new GeographicalLocationDTO(-91, 43.111111);
  private static final GeographicalLocationDTO INVALID_COMPONENT_GEOGRAPHICAL_LOCATION_6 =
      new GeographicalLocationDTO(42.123, 181);
  private static final GeographicalLocationDTO INVALID_COMPONENT_GEOGRAPHICAL_LOCATION_7 =
      new GeographicalLocationDTO(42.123, -181);
  private static final String COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER = "b-1";
  private static final String COMPONENT_PARENT_ROOM_IDENTIFICATION_NUMBER = "r-1";
  private static final String INVALID_COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER = "b1";
  private static final String INVALID_COMPONENT_PARENT_ROOM_IDENTIFICATION_NUMBER = "r1";
  private static final String NOT_EXISTING_COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER = "b-42";
  private static final String NOT_EXISTING_COMPONENT_PARENT_ROOM_IDENTIFICATION_NUMBER = "r-42";
  private static final String EMPTY_STRING = "";
  //Mocks
  private static final BuildingRepository BUILDING_REPOSITORY = mock(BuildingRepository.class);
  private static final RoomRepository ROOM_REPOSITORY = mock(RoomRepository.class);
  private static final ComponentRepository COMPONENT_REPOSITORY = mock(ComponentRepository.class);
  private static final NotificationRepository NOTIFICATION_REPOSITORY = mock(
      NotificationRepository.class);
  private static final ComponentValidator COMPONENT_VALIDATOR =
      new ComponentValidator(BUILDING_REPOSITORY, ROOM_REPOSITORY, COMPONENT_REPOSITORY,
          NOTIFICATION_REPOSITORY);

  @BeforeAll
  static void setUp() {
    Mockito.when(COMPONENT_REPOSITORY.existsById(COMPONENT_IDENTIFICATION_NUMBER)).thenReturn(true);
    Mockito.when(COMPONENT_REPOSITORY.existsById(NOT_EXISTING_COMPONENT_IDENTIFICATION_NUMBER))
        .thenReturn(false);
    Mockito.when(BUILDING_REPOSITORY.existsById(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER))
        .thenReturn(true);
    Mockito.when(ROOM_REPOSITORY.existsById(COMPONENT_PARENT_ROOM_IDENTIFICATION_NUMBER))
        .thenReturn(true);
  }

  @Test
  void getValidateRegex_ShouldReturnBINPattern() {
    Assertions.assertEquals(Validator.CIN_PATTERN, COMPONENT_VALIDATOR.getValidateRegex());
  }

  @ParameterizedTest
  @MethodSource("provideValidServerCreateComponentRequests")
  void validateCreate_ShouldNotThrowException(
      ServerCreateComponentRequest serverCreateComponentRequest) {
    Assertions.assertDoesNotThrow(
        () -> COMPONENT_VALIDATOR.validateCreate(serverCreateComponentRequest));
  }

  @ParameterizedTest
  @MethodSource("provideInvalidArgumentServerCreateComponentRequests")
  void validateCreate_ShouldThrowInvalidArgumentsException(
      ServerCreateComponentRequest serverCreateComponentRequest) {
    Assertions.assertThrows(InvalidArgumentsException.class,
        () -> COMPONENT_VALIDATOR.validateCreate(serverCreateComponentRequest));
  }

  @ParameterizedTest
  @MethodSource("provideInvalidResourceServerCreateComponentRequests")
  void validateCreate_ShouldThrowResourceNotFoundException(
      ServerCreateComponentRequest serverCreateComponentRequest) {
    Assertions.assertThrows(ResourceNotFoundException.class,
        () -> COMPONENT_VALIDATOR.validateCreate(serverCreateComponentRequest));
  }

  @ParameterizedTest
  @MethodSource("provideValidServerUpdateComponentRequests")
  void validateUpdate_ShouldNotThrowException(
      ServerUpdateComponentRequest serverUpdateComponentRequest) {
    Assertions.assertDoesNotThrow(
        () -> COMPONENT_VALIDATOR.validateUpdate(serverUpdateComponentRequest));
  }

  @ParameterizedTest
  @MethodSource("provideInvalidArgumentsServerUpdateComponentRequests")
  void validateUpdate_ShouldThrowInvalidArgumentsException(
      ServerUpdateComponentRequest serverUpdateComponentRequest) {
    Assertions.assertThrows(InvalidArgumentsException.class,
        () -> COMPONENT_VALIDATOR.validateUpdate(serverUpdateComponentRequest));
  }

  @ParameterizedTest
  @MethodSource("provideInvalidResourceServerUpdateComponentRequests")
  void validateUpdate_ShouldThrowResourceNotFoundException(
      ServerUpdateComponentRequest serverUpdateComponentRequest) {
    Assertions.assertThrows(ResourceNotFoundException.class,
        () -> COMPONENT_VALIDATOR.validateUpdate(serverUpdateComponentRequest));
  }

  private static Stream<Arguments> provideValidServerCreateComponentRequests() {
    return Stream.of(
        Arguments.of(
            new ServerCreateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, COMPONENT_GEOGRAPHICAL_LOCATION)),
        Arguments.of(
            new ServerCreateComponentRequest(COMPONENT_PARENT_ROOM_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, COMPONENT_GEOGRAPHICAL_LOCATION))
    );
  }

  private static Stream<Arguments> provideInvalidArgumentServerCreateComponentRequests() {
    return Stream.of(
        //invalid because of null:
        Arguments.of(
            (ServerCreateComponentRequest) null),
        Arguments.of(
            new ServerCreateComponentRequest(null, null, null, null)),
        Arguments.of(
            new ServerCreateComponentRequest(null,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, COMPONENT_GEOGRAPHICAL_LOCATION)),
        Arguments.of(
            new ServerCreateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                null, COMPONENT_DESCRIPTION, COMPONENT_GEOGRAPHICAL_LOCATION)),
        Arguments.of(
            new ServerCreateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, null, COMPONENT_GEOGRAPHICAL_LOCATION)),
        Arguments.of(
            new ServerCreateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, null)),
        //invalid because of empty
        Arguments.of(
            new ServerCreateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, EMPTY_STRING, COMPONENT_GEOGRAPHICAL_LOCATION)),
        //invalid because of regex
        Arguments.of(
            new ServerCreateComponentRequest(
                INVALID_COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, COMPONENT_GEOGRAPHICAL_LOCATION)),
        Arguments.of(
            new ServerCreateComponentRequest(INVALID_COMPONENT_PARENT_ROOM_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, COMPONENT_GEOGRAPHICAL_LOCATION)),
        //invalid because of invalid geographical location
        Arguments.of(
            new ServerCreateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, INVALID_COMPONENT_GEOGRAPHICAL_LOCATION_1)),
        Arguments.of(
            new ServerCreateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, INVALID_COMPONENT_GEOGRAPHICAL_LOCATION_2)),
        Arguments.of(
            new ServerCreateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, INVALID_COMPONENT_GEOGRAPHICAL_LOCATION_3)),
        Arguments.of(
            new ServerCreateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, INVALID_COMPONENT_GEOGRAPHICAL_LOCATION_4)),
        Arguments.of(
            new ServerCreateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, INVALID_COMPONENT_GEOGRAPHICAL_LOCATION_5)),
        Arguments.of(
            new ServerCreateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, INVALID_COMPONENT_GEOGRAPHICAL_LOCATION_6)),
        Arguments.of(
            new ServerCreateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, INVALID_COMPONENT_GEOGRAPHICAL_LOCATION_7))
    );
  }

  private static Stream<Arguments> provideInvalidResourceServerCreateComponentRequests() {
    return Stream.of(
        Arguments.of(
            new ServerCreateComponentRequest(
                NOT_EXISTING_COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, COMPONENT_GEOGRAPHICAL_LOCATION)),
        Arguments.of(
            new ServerCreateComponentRequest(
                NOT_EXISTING_COMPONENT_PARENT_ROOM_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, COMPONENT_GEOGRAPHICAL_LOCATION))
    );
  }

  private static Stream<Arguments> provideValidServerUpdateComponentRequests() {
    return Stream.of(
        Arguments.of(
            new ServerUpdateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, COMPONENT_GEOGRAPHICAL_LOCATION,
                COMPONENT_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateComponentRequest(COMPONENT_PARENT_ROOM_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, COMPONENT_GEOGRAPHICAL_LOCATION,
                COMPONENT_IDENTIFICATION_NUMBER))
    );
  }

  private static Stream<Arguments> provideInvalidArgumentsServerUpdateComponentRequests() {
    return Stream.of(
        //invalid because of null
        Arguments.of(
            (ServerUpdateComponentRequest) null),
        Arguments.of(
            new ServerUpdateComponentRequest(null, null, null, null, null)),
        Arguments.of(
            new ServerUpdateComponentRequest(null,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, COMPONENT_GEOGRAPHICAL_LOCATION,
                COMPONENT_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                null, COMPONENT_DESCRIPTION, COMPONENT_GEOGRAPHICAL_LOCATION,
                COMPONENT_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, null, COMPONENT_GEOGRAPHICAL_LOCATION,
                COMPONENT_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, null,
                COMPONENT_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, COMPONENT_GEOGRAPHICAL_LOCATION,
                null)),
        //invalid because of empty
        Arguments.of(
            new ServerUpdateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, EMPTY_STRING, COMPONENT_GEOGRAPHICAL_LOCATION,
                COMPONENT_IDENTIFICATION_NUMBER)),
        //invalid because of regex
        Arguments.of(
            new ServerUpdateComponentRequest(
                INVALID_COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, COMPONENT_GEOGRAPHICAL_LOCATION,
                COMPONENT_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateComponentRequest(INVALID_COMPONENT_PARENT_ROOM_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, COMPONENT_GEOGRAPHICAL_LOCATION,
                COMPONENT_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, COMPONENT_GEOGRAPHICAL_LOCATION,
                INVALID_COMPONENT_IDENTIFICATION_NUMBER)),
        //invalid because of invalid geographical location
        Arguments.of(
            new ServerUpdateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, INVALID_COMPONENT_GEOGRAPHICAL_LOCATION_1,
                COMPONENT_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, INVALID_COMPONENT_GEOGRAPHICAL_LOCATION_2,
                COMPONENT_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, INVALID_COMPONENT_GEOGRAPHICAL_LOCATION_3,
                COMPONENT_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, INVALID_COMPONENT_GEOGRAPHICAL_LOCATION_4,
                COMPONENT_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, INVALID_COMPONENT_GEOGRAPHICAL_LOCATION_5,
                COMPONENT_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, INVALID_COMPONENT_GEOGRAPHICAL_LOCATION_6,
                COMPONENT_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, INVALID_COMPONENT_GEOGRAPHICAL_LOCATION_7,
                COMPONENT_IDENTIFICATION_NUMBER))
    );
  }

  private static Stream<Arguments> provideInvalidResourceServerUpdateComponentRequests() {
    return Stream.of(
        Arguments.of(
            new ServerUpdateComponentRequest(
                NOT_EXISTING_COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, COMPONENT_GEOGRAPHICAL_LOCATION,
                COMPONENT_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateComponentRequest(
                NOT_EXISTING_COMPONENT_PARENT_ROOM_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, COMPONENT_GEOGRAPHICAL_LOCATION,
                COMPONENT_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateComponentRequest(COMPONENT_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                COMPONENT_TYPE, COMPONENT_DESCRIPTION, COMPONENT_GEOGRAPHICAL_LOCATION,
                NOT_EXISTING_COMPONENT_IDENTIFICATION_NUMBER))
    );
  }

}
