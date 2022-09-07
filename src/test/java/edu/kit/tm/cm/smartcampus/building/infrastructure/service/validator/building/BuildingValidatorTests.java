package edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.building;

import static org.mockito.Mockito.mock;

import edu.kit.tm.cm.smartcampus.building.api.controller.FloorsDTO;
import edu.kit.tm.cm.smartcampus.building.api.controller.GeographicalLocationDTO;
import edu.kit.tm.cm.smartcampus.building.api.controller.building.dto.ServerCreateBuildingRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.building.dto.ServerUpdateBuildingRequest;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.building.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.component.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.notification.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.room.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.error.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.error.exceptions.ResourceNotFoundException;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.Validator;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building.CampusLocation;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

public class BuildingValidatorTests {

  //Attributes
  private static final String BUILDING_IDENTIFICATION_NUMBER = "b-1";
  private static final String BUILDING_NAME = "Testgebäude";
  private static final String BUILDING_ADDRESS = "Teststraße 33";
  private static final String BUILDING_NUMBER_1 = "12.12";
  private static final String BUILDING_NUMBER_2 = "123/4";
  private static final CampusLocation BUILDING_CAMPUS_LOCATION = CampusLocation.SOUTH_CAMPUS;
  private static final GeographicalLocationDTO BUILDING_GEOGRAPHICAL_LOCATION =
      new GeographicalLocationDTO(42.123, 43.111111);
  private static final FloorsDTO BUILDING_FLOORS = new FloorsDTO(5, -1);
  private static final String EMPTY_STRING = "";
  private static final String INVALID_BUILDING_NUMBER = "10";
  private static final GeographicalLocationDTO INVALID_BUILDING_GEOGRAPHICAL_LOCATION_1 =
      new GeographicalLocationDTO(42.123, Double.NaN);
  private static final GeographicalLocationDTO INVALID_BUILDING_GEOGRAPHICAL_LOCATION_2 =
      new GeographicalLocationDTO(Double.POSITIVE_INFINITY, 43.111111);
  private static final GeographicalLocationDTO INVALID_BUILDING_GEOGRAPHICAL_LOCATION_3 =
      new GeographicalLocationDTO(Double.NEGATIVE_INFINITY, 1);
  private static final GeographicalLocationDTO INVALID_BUILDING_GEOGRAPHICAL_LOCATION_4 =
      new GeographicalLocationDTO(91, 43.111111);
  private static final GeographicalLocationDTO INVALID_BUILDING_GEOGRAPHICAL_LOCATION_5 =
      new GeographicalLocationDTO(-91, 43.111111);
  private static final GeographicalLocationDTO INVALID_BUILDING_GEOGRAPHICAL_LOCATION_6 =
      new GeographicalLocationDTO(42.123, 181);
  private static final GeographicalLocationDTO INVALID_BUILDING_GEOGRAPHICAL_LOCATION_7 =
      new GeographicalLocationDTO(42.123, -181);
  private static final FloorsDTO INVALID_BUILDING_FLOORS = new FloorsDTO(1, 2);
  private static final String INVALID_BUILDING_IDENTIFICATION_NUMBER = "b1";
  private static final String NOT_EXISTING_BUILDING_IDENTIFICATION_NUMBER = "b-42";
  //Mocks
  private static final BuildingRepository BUILDING_REPOSITORY = mock(BuildingRepository.class);
  private static final RoomRepository ROOM_REPOSITORY = mock(RoomRepository.class);
  private static final ComponentRepository COMPONENT_REPOSITORY = mock(ComponentRepository.class);
  private static final NotificationRepository NOTIFICATION_REPOSITORY = mock(
      NotificationRepository.class);
  private static final BuildingValidator BUILDING_VALIDATOR =
      new BuildingValidator(BUILDING_REPOSITORY, ROOM_REPOSITORY, COMPONENT_REPOSITORY,
          NOTIFICATION_REPOSITORY);

  @BeforeAll
  static void setUp() {
    Mockito.when(BUILDING_REPOSITORY.existsById(BUILDING_IDENTIFICATION_NUMBER)).thenReturn(true);
    Mockito.when(BUILDING_REPOSITORY.existsById(NOT_EXISTING_BUILDING_IDENTIFICATION_NUMBER))
        .thenReturn(false);
  }

  @Test
  void getValidateRegex_ShouldReturnBINPattern() {
    Assertions.assertEquals(Validator.BIN_PATTERN, BUILDING_VALIDATOR.getValidateRegex());
  }

  @ParameterizedTest
  @MethodSource("provideValidServerCreateBuildingRequests")
  void validateCreate_ShouldNotThrowException(
      ServerCreateBuildingRequest serverCreateBuildingRequest) {
    Assertions.assertDoesNotThrow(
        () -> BUILDING_VALIDATOR.validateCreate(serverCreateBuildingRequest));
  }

  @ParameterizedTest
  @MethodSource("provideInvalidServerCreateBuildingRequests")
  void validateCreate_ShouldThrowException(
      ServerCreateBuildingRequest serverCreateBuildingRequest) {
    Assertions.assertThrows(InvalidArgumentsException.class,
        () -> BUILDING_VALIDATOR.validateCreate(serverCreateBuildingRequest));
  }

  @ParameterizedTest
  @MethodSource("provideValidServerUpdateBuildingRequests")
  void validateUpdate_ShouldNotThrowException(
      ServerUpdateBuildingRequest serverUpdateBuildingRequest) {
    Assertions.assertDoesNotThrow(
        () -> BUILDING_VALIDATOR.validateUpdate(serverUpdateBuildingRequest));
  }

  @ParameterizedTest
  @MethodSource("provideInvalidArgumentsServerUpdateBuildingRequests")
  void validateUpdate_ShouldThrowInvalidArgumentsException(
      ServerUpdateBuildingRequest serverUpdateBuildingRequest) {
    Assertions.assertThrows(InvalidArgumentsException.class,
        () -> BUILDING_VALIDATOR.validateUpdate(serverUpdateBuildingRequest));
  }

  @ParameterizedTest
  @MethodSource("provideInvalidResourceServerUpdateBuildingRequests")
  void validateUpdate_ShouldThrowResourceNotFoundException(
      ServerUpdateBuildingRequest serverUpdateBuildingRequest) {
    Assertions.assertThrows(ResourceNotFoundException.class,
        () -> BUILDING_VALIDATOR.validateUpdate(serverUpdateBuildingRequest));
  }

  private static Stream<Arguments> provideValidServerCreateBuildingRequests() {
    return Stream.of(
        Arguments.of(
            new ServerCreateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION, BUILDING_GEOGRAPHICAL_LOCATION, BUILDING_FLOORS)),
        Arguments.of(
            new ServerCreateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_2, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION, BUILDING_GEOGRAPHICAL_LOCATION, BUILDING_FLOORS))
    );
  }

  private static Stream<Arguments> provideInvalidServerCreateBuildingRequests() {
    return Stream.of(
        //invalid because of null:
        Arguments.of(
            (ServerCreateBuildingRequest) null),
        Arguments.of(
            new ServerCreateBuildingRequest(null, null, null,
                null, null, null)),
        Arguments.of(
            new ServerCreateBuildingRequest(null, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION, BUILDING_GEOGRAPHICAL_LOCATION, BUILDING_FLOORS)),
        Arguments.of(
            new ServerCreateBuildingRequest(BUILDING_NAME, null, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION, BUILDING_GEOGRAPHICAL_LOCATION, BUILDING_FLOORS)),
        Arguments.of(
            new ServerCreateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, null,
                BUILDING_CAMPUS_LOCATION, BUILDING_GEOGRAPHICAL_LOCATION, BUILDING_FLOORS)),
        Arguments.of(
            new ServerCreateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                null, BUILDING_GEOGRAPHICAL_LOCATION, BUILDING_FLOORS)),
        Arguments.of(
            new ServerCreateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION, null, BUILDING_FLOORS)),
        Arguments.of(
            new ServerCreateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION, BUILDING_GEOGRAPHICAL_LOCATION, null)),
        //invalid because of empty
        Arguments.of(
            new ServerCreateBuildingRequest(EMPTY_STRING, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION, BUILDING_GEOGRAPHICAL_LOCATION, BUILDING_FLOORS)),
        //invalid because of regex
        Arguments.of(
            new ServerCreateBuildingRequest(BUILDING_NAME, INVALID_BUILDING_NUMBER, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION, BUILDING_GEOGRAPHICAL_LOCATION, BUILDING_FLOORS)),
        //invalid because of invalid geographical location
        Arguments.of(
            new ServerCreateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION, INVALID_BUILDING_GEOGRAPHICAL_LOCATION_1,
                BUILDING_FLOORS)),
        Arguments.of(
            new ServerCreateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION, INVALID_BUILDING_GEOGRAPHICAL_LOCATION_2,
                BUILDING_FLOORS)),
        Arguments.of(
            new ServerCreateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION, INVALID_BUILDING_GEOGRAPHICAL_LOCATION_3,
                BUILDING_FLOORS)),
        Arguments.of(
            new ServerCreateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION, INVALID_BUILDING_GEOGRAPHICAL_LOCATION_4,
                BUILDING_FLOORS)),
        Arguments.of(
            new ServerCreateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION, INVALID_BUILDING_GEOGRAPHICAL_LOCATION_5,
                BUILDING_FLOORS)),
        Arguments.of(
            new ServerCreateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION, INVALID_BUILDING_GEOGRAPHICAL_LOCATION_6,
                BUILDING_FLOORS)),
        Arguments.of(
            new ServerCreateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION, INVALID_BUILDING_GEOGRAPHICAL_LOCATION_7,
                BUILDING_FLOORS)),
        //invalid because of invalid floors
        Arguments.of(
            new ServerCreateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION, BUILDING_GEOGRAPHICAL_LOCATION, INVALID_BUILDING_FLOORS))
    );
  }

  private static Stream<Arguments> provideValidServerUpdateBuildingRequests() {
    return Stream.of(
        Arguments.of(
            new ServerUpdateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION,
                BUILDING_GEOGRAPHICAL_LOCATION, BUILDING_FLOORS,
                BUILDING_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_2, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION,
                BUILDING_GEOGRAPHICAL_LOCATION, BUILDING_FLOORS,
                BUILDING_IDENTIFICATION_NUMBER))
    );
  }

  private static Stream<Arguments> provideInvalidArgumentsServerUpdateBuildingRequests() {
    return Stream.of(
        //invalid because of null
        Arguments.of(
            (ServerUpdateBuildingRequest) null),
        Arguments.of(
            new ServerUpdateBuildingRequest(null, null, null,
                null, null, null, null)),
        Arguments.of(
            new ServerUpdateBuildingRequest(null, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION,
                BUILDING_GEOGRAPHICAL_LOCATION, BUILDING_FLOORS,
                BUILDING_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateBuildingRequest(BUILDING_NAME, null, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION,
                BUILDING_GEOGRAPHICAL_LOCATION, BUILDING_FLOORS,
                BUILDING_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, null,
                BUILDING_CAMPUS_LOCATION,
                BUILDING_GEOGRAPHICAL_LOCATION, BUILDING_FLOORS,
                BUILDING_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                null,
                BUILDING_GEOGRAPHICAL_LOCATION, BUILDING_FLOORS,
                BUILDING_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION,
                null, BUILDING_FLOORS, BUILDING_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION,
                BUILDING_GEOGRAPHICAL_LOCATION, null, BUILDING_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION,
                BUILDING_GEOGRAPHICAL_LOCATION, BUILDING_FLOORS, null)),
        //invalid because of empty
        Arguments.of(
            new ServerUpdateBuildingRequest(EMPTY_STRING, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION,
                BUILDING_GEOGRAPHICAL_LOCATION, BUILDING_FLOORS,
                BUILDING_IDENTIFICATION_NUMBER)),
        //invalid because of regex
        Arguments.of(
            new ServerUpdateBuildingRequest(BUILDING_NAME, INVALID_BUILDING_NUMBER, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION,
                BUILDING_GEOGRAPHICAL_LOCATION, BUILDING_FLOORS,
                BUILDING_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION,
                BUILDING_GEOGRAPHICAL_LOCATION, BUILDING_FLOORS,
                INVALID_BUILDING_IDENTIFICATION_NUMBER)),
        //invalid because of invalid geographical location
        Arguments.of(
            new ServerUpdateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION,
                INVALID_BUILDING_GEOGRAPHICAL_LOCATION_1, BUILDING_FLOORS,
                BUILDING_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION,
                INVALID_BUILDING_GEOGRAPHICAL_LOCATION_2, BUILDING_FLOORS,
                BUILDING_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION,
                INVALID_BUILDING_GEOGRAPHICAL_LOCATION_3, BUILDING_FLOORS,
                BUILDING_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION,
                INVALID_BUILDING_GEOGRAPHICAL_LOCATION_4, BUILDING_FLOORS,
                BUILDING_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION,
                INVALID_BUILDING_GEOGRAPHICAL_LOCATION_5, BUILDING_FLOORS,
                BUILDING_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION,
                INVALID_BUILDING_GEOGRAPHICAL_LOCATION_6, BUILDING_FLOORS,
                BUILDING_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION,
                INVALID_BUILDING_GEOGRAPHICAL_LOCATION_7, BUILDING_FLOORS,
                BUILDING_IDENTIFICATION_NUMBER)),
        //invalid because of invalid floors
        Arguments.of(
            new ServerUpdateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION,
                BUILDING_GEOGRAPHICAL_LOCATION, INVALID_BUILDING_FLOORS,
                BUILDING_IDENTIFICATION_NUMBER))
    );
  }

  private static Stream<Arguments> provideInvalidResourceServerUpdateBuildingRequests() {
    return Stream.of(
        Arguments.of(
            new ServerUpdateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER_1, BUILDING_ADDRESS,
                BUILDING_CAMPUS_LOCATION, BUILDING_GEOGRAPHICAL_LOCATION, BUILDING_FLOORS,
                NOT_EXISTING_BUILDING_IDENTIFICATION_NUMBER))
    );
  }

}
