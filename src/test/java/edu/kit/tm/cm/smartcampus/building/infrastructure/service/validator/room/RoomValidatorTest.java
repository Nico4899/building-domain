package edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.room;

import static org.mockito.Mockito.mock;

import edu.kit.tm.cm.smartcampus.building.api.controller.GeographicalLocationDTO;
import edu.kit.tm.cm.smartcampus.building.api.controller.room.dto.ServerCreateRoomRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.room.dto.ServerUpdateRoomRequest;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.building.BuildingRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.component.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.notification.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.room.RoomRepository;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.error.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.error.exceptions.ResourceNotFoundException;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.validator.Validator;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building.CampusLocation;
import edu.kit.tm.cm.smartcampus.building.logic.model.Floors;
import edu.kit.tm.cm.smartcampus.building.logic.model.GeographicalLocation;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room.Type;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

public class RoomValidatorTest {

  private static final String ROOM_IDENTIFICATION_NUMBER = "r-1";
  private static final String INVALID_ROOM_IDENTIFICATION_NUMBER = "r1";
  private static final String NOT_EXISTING_ROOM_IDENTIFICATION_NUMBER = "r-42";
  private static final String PARENT_BUILDING_IDENTIFICATION_NUMBER = "b-1";
  private static final String ROOM_NAME = "Testraum";
  private static final String ROOM_NUMBER = "123";
  private static final int ROOM_FLOOR = 0;
  private static final Room.Type ROOM_TYPE = Type.SEMINAR_ROOM;
  private static final GeographicalLocationDTO ROOM_GEOGRAPHICAL_LOCATION =
      new GeographicalLocationDTO(42.123, 43.111111);
  private static final GeographicalLocationDTO INVALID_ROOM_GEOGRAPHICAL_LOCATION_1 =
      new GeographicalLocationDTO(42.123, Double.NaN);
  private static final GeographicalLocationDTO INVALID_ROOM_GEOGRAPHICAL_LOCATION_2 =
      new GeographicalLocationDTO(Double.POSITIVE_INFINITY, 43.111111);
  private static final GeographicalLocationDTO INVALID_ROOM_GEOGRAPHICAL_LOCATION_3 =
      new GeographicalLocationDTO(Double.NEGATIVE_INFINITY, 1);
  private static final GeographicalLocationDTO INVALID_ROOM_GEOGRAPHICAL_LOCATION_4 =
      new GeographicalLocationDTO(91, 43.111111);
  private static final GeographicalLocationDTO INVALID_ROOM_GEOGRAPHICAL_LOCATION_5 =
      new GeographicalLocationDTO(-91, 43.111111);
  private static final GeographicalLocationDTO INVALID_ROOM_GEOGRAPHICAL_LOCATION_6 =
      new GeographicalLocationDTO(42.123, 181);
  private static final GeographicalLocationDTO INVALID_ROOM_GEOGRAPHICAL_LOCATION_7 =
      new GeographicalLocationDTO(42.123, -181);
  private static final String INVALID_PARENT_BUILDING_IDENTIFICATION_NUMBER = "b1";
  private static final String NOT_EXISTING_PARENT_BUILDING_IDENTIFICATION_NUMBER = "b-42";
  private static final int INVALID_ROOM_FLOOR = -33;
  private static final String PARENT_BUILDING_NAME = "Testgebäude";
  private static final String PARENT_BUILDING_NUMBER = "12.12";
  private static final CampusLocation PARENT_BUILDING_CAMPUS_LOCATION = CampusLocation.SOUTH_CAMPUS;
  private static final GeographicalLocation PARENT_BUILDING_GEOGRAPHICAL_LOCATION =
      new GeographicalLocation();
  private static final Floors PARENT_BUILDING_FLOORS = new Floors();
  private static final double PARENT_BUILDING_GEOGRAPHICAL_LOCATION_LATITUDE = 12.3456;
  private static final double PARENT_BUILDING_GEOGRAPHICAL_LOCATION_LONGITUDE = 43.21987;
  private static final int PARENT_BUILDING_FLOORS_HIGHEST_FLOOR = 12;
  private static final int PARENT_BUILDING_FLOORS_LOWEST_FLOOR = -1;
  private static final String EMPTY_STRING = "";


  private static final BuildingRepository BUILDING_REPOSITORY = mock(BuildingRepository.class);
  private static final RoomRepository ROOM_REPOSITORY = mock(RoomRepository.class);
  private static final ComponentRepository COMPONENT_REPOSITORY = mock(ComponentRepository.class);
  private static final NotificationRepository NOTIFICATION_REPOSITORY = mock(
      NotificationRepository.class);
  private static final RoomValidator ROOM_VALIDATOR =
      new RoomValidator(BUILDING_REPOSITORY, ROOM_REPOSITORY, COMPONENT_REPOSITORY,
          NOTIFICATION_REPOSITORY);

  @BeforeAll
  static void setUp() {
    Mockito.when(ROOM_REPOSITORY.existsById(ROOM_IDENTIFICATION_NUMBER)).thenReturn(true);
    Mockito.when(ROOM_REPOSITORY.existsById(NOT_EXISTING_ROOM_IDENTIFICATION_NUMBER))
        .thenReturn(false);
    Mockito.when(BUILDING_REPOSITORY.existsById(PARENT_BUILDING_IDENTIFICATION_NUMBER)).thenReturn(true);
    Mockito.when(BUILDING_REPOSITORY.existsById(NOT_EXISTING_PARENT_BUILDING_IDENTIFICATION_NUMBER))
        .thenReturn(false);

    //create and mock parent building to validate a rooms floor
    Building parent = new Building();
    parent.setIdentificationNumber(PARENT_BUILDING_IDENTIFICATION_NUMBER);
    parent.setName(PARENT_BUILDING_NAME);
    parent.setNumber(PARENT_BUILDING_NUMBER);
    parent.setCampusLocation(PARENT_BUILDING_CAMPUS_LOCATION);
    PARENT_BUILDING_GEOGRAPHICAL_LOCATION.setLongitude(
        PARENT_BUILDING_GEOGRAPHICAL_LOCATION_LONGITUDE);
    PARENT_BUILDING_GEOGRAPHICAL_LOCATION.setLatitude(
        PARENT_BUILDING_GEOGRAPHICAL_LOCATION_LATITUDE);
    parent.setGeographicalLocation(PARENT_BUILDING_GEOGRAPHICAL_LOCATION);
    PARENT_BUILDING_FLOORS.setHighestFloor(PARENT_BUILDING_FLOORS_HIGHEST_FLOOR);
    PARENT_BUILDING_FLOORS.setLowestFloor(PARENT_BUILDING_FLOORS_LOWEST_FLOOR);
    parent.setFloors(PARENT_BUILDING_FLOORS);

    Mockito.when(BUILDING_REPOSITORY.findById(PARENT_BUILDING_IDENTIFICATION_NUMBER)).thenReturn(
        Optional.of(parent));
  }

  @Test
  void getValidateRegex_ShouldReturnBINPattern() {
    Assertions.assertEquals(Validator.RIN_PATTERN, ROOM_VALIDATOR.getValidateRegex());
  }

  @ParameterizedTest
  @MethodSource("provideValidServerCreateRoomRequests")
  void validateCreate_ShouldNotThrowException(
      ServerCreateRoomRequest serverCreateRoomRequest) {
    Assertions.assertDoesNotThrow(
        () -> ROOM_VALIDATOR.validateCreate(serverCreateRoomRequest));
  }

  @ParameterizedTest
  @MethodSource("provideInvalidArgumentServerCreateRoomRequests")
  void validateCreate_ShouldThrowInvalidArgumentsException(
      ServerCreateRoomRequest serverCreateRoomRequest) {
    Assertions.assertThrows(InvalidArgumentsException.class,
        () -> ROOM_VALIDATOR.validateCreate(serverCreateRoomRequest));
  }

  @ParameterizedTest
  @MethodSource("provideInvalidResourceServerCreateRoomRequests")
  void validateCreate_ShouldThrowResourceNotFoundException(
      ServerCreateRoomRequest serverCreateRoomRequest) {
    Assertions.assertThrows(ResourceNotFoundException.class,
        () -> ROOM_VALIDATOR.validateCreate(serverCreateRoomRequest));
  }

  @ParameterizedTest
  @MethodSource("provideValidServerUpdateRoomRequests")
  void validateUpdate_ShouldNotThrowException(
      ServerUpdateRoomRequest serverUpdateRoomRequest) {
    Assertions.assertDoesNotThrow(
        () -> ROOM_VALIDATOR.validateUpdate(serverUpdateRoomRequest));
  }

  @ParameterizedTest
  @MethodSource("provideInvalidArgumentsServerUpdateRoomRequests")
  void validateUpdate_ShouldThrowInvalidArgumentsException(
      ServerUpdateRoomRequest serverUpdateRoomRequest) {
    Assertions.assertThrows(InvalidArgumentsException.class,
        () -> ROOM_VALIDATOR.validateUpdate(serverUpdateRoomRequest));
  }

  @ParameterizedTest
  @MethodSource("provideInvalidResourceServerUpdateRoomRequests")
  void validateUpdate_ShouldThrowResourceNotFoundException(
      ServerUpdateRoomRequest serverUpdateRoomRequest) {
    Assertions.assertThrows(ResourceNotFoundException.class,
        () -> ROOM_VALIDATOR.validateUpdate(serverUpdateRoomRequest));
  }

  private static Stream<Arguments> provideValidServerCreateRoomRequests() {
    return Stream.of(
        Arguments.of(
            new ServerCreateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER, ROOM_FLOOR, ROOM_TYPE, ROOM_GEOGRAPHICAL_LOCATION))
    );
  }

  private static Stream<Arguments> provideInvalidArgumentServerCreateRoomRequests() {
    return Stream.of(
        //invalid because of null:
        Arguments.of(
            (ServerCreateRoomRequest) null),
        Arguments.of(
            new ServerCreateRoomRequest(null, null,
                null, ROOM_FLOOR, null, null)),
        Arguments.of(
            new ServerCreateRoomRequest(null, ROOM_NAME, ROOM_NUMBER,
                ROOM_FLOOR, ROOM_TYPE, ROOM_GEOGRAPHICAL_LOCATION)),
        Arguments.of(
            new ServerCreateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, null, ROOM_NUMBER,
                ROOM_FLOOR, ROOM_TYPE, ROOM_GEOGRAPHICAL_LOCATION)),
        Arguments.of(
            new ServerCreateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME, null,
                ROOM_FLOOR, ROOM_TYPE, ROOM_GEOGRAPHICAL_LOCATION)),
        Arguments.of(
            new ServerCreateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER,
                ROOM_FLOOR, null, ROOM_GEOGRAPHICAL_LOCATION)),
        Arguments.of(
            new ServerCreateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER,
                ROOM_FLOOR, ROOM_TYPE, null)),
        //invalid because of empty
        Arguments.of(
            new ServerCreateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, EMPTY_STRING,
                ROOM_NUMBER, ROOM_FLOOR, ROOM_TYPE, ROOM_GEOGRAPHICAL_LOCATION)),
        Arguments.of(
            new ServerCreateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                EMPTY_STRING, ROOM_FLOOR, ROOM_TYPE, ROOM_GEOGRAPHICAL_LOCATION)),
        //invalid because of regex
        Arguments.of(
            new ServerCreateRoomRequest(INVALID_PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER, ROOM_FLOOR, ROOM_TYPE, ROOM_GEOGRAPHICAL_LOCATION)),
        //invalid because of invalid geographical location
        Arguments.of(
            new ServerCreateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER,
                ROOM_FLOOR, ROOM_TYPE, INVALID_ROOM_GEOGRAPHICAL_LOCATION_1)),
        Arguments.of(
            new ServerCreateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER,
                ROOM_FLOOR, ROOM_TYPE, INVALID_ROOM_GEOGRAPHICAL_LOCATION_2)),
        Arguments.of(
            new ServerCreateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER,
                ROOM_FLOOR, ROOM_TYPE, INVALID_ROOM_GEOGRAPHICAL_LOCATION_3)),
        Arguments.of(
            new ServerCreateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER,
                ROOM_FLOOR, ROOM_TYPE, INVALID_ROOM_GEOGRAPHICAL_LOCATION_4)),
        Arguments.of(
            new ServerCreateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER,
                ROOM_FLOOR, ROOM_TYPE, INVALID_ROOM_GEOGRAPHICAL_LOCATION_5)),
        Arguments.of(
            new ServerCreateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER,
                ROOM_FLOOR, ROOM_TYPE, INVALID_ROOM_GEOGRAPHICAL_LOCATION_6)),
        Arguments.of(
            new ServerCreateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER,
                ROOM_FLOOR, ROOM_TYPE, INVALID_ROOM_GEOGRAPHICAL_LOCATION_7)),
        //invalid because of floor
        Arguments.of(
            new ServerCreateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER,
                INVALID_ROOM_FLOOR, ROOM_TYPE, ROOM_GEOGRAPHICAL_LOCATION))
    );
  }

  private static Stream<Arguments> provideInvalidResourceServerCreateRoomRequests() {
    return Stream.of(
        Arguments.of(
            new ServerCreateRoomRequest(NOT_EXISTING_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                ROOM_NAME, ROOM_NUMBER, ROOM_FLOOR, ROOM_TYPE, ROOM_GEOGRAPHICAL_LOCATION))
    );
  }

  private static Stream<Arguments> provideValidServerUpdateRoomRequests() {
    return Stream.of(
        Arguments.of(
            new ServerUpdateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER, ROOM_FLOOR, ROOM_TYPE, ROOM_GEOGRAPHICAL_LOCATION,
                ROOM_IDENTIFICATION_NUMBER))
    );
  }

  private static Stream<Arguments> provideInvalidArgumentsServerUpdateRoomRequests() {
    return Stream.of(
        //invalid because of null
        Arguments.of(
            (ServerUpdateRoomRequest) null),
        Arguments.of(
            new ServerUpdateRoomRequest(null, null,
                null, ROOM_FLOOR, null, null, null)),
        Arguments.of(
            new ServerUpdateRoomRequest(null, ROOM_NAME,
                ROOM_NUMBER, ROOM_FLOOR, ROOM_TYPE, ROOM_GEOGRAPHICAL_LOCATION,
                ROOM_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, null,
                ROOM_NUMBER, ROOM_FLOOR, ROOM_TYPE, ROOM_GEOGRAPHICAL_LOCATION,
                ROOM_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                null, ROOM_FLOOR, ROOM_TYPE, ROOM_GEOGRAPHICAL_LOCATION,
                ROOM_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER, ROOM_FLOOR, null, ROOM_GEOGRAPHICAL_LOCATION,
                ROOM_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER, ROOM_FLOOR, ROOM_TYPE, null,
                ROOM_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER, ROOM_FLOOR, ROOM_TYPE, ROOM_GEOGRAPHICAL_LOCATION,
                null)),
        //invalid because of empty
        Arguments.of(
            new ServerUpdateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, EMPTY_STRING,
                ROOM_NUMBER, ROOM_FLOOR, ROOM_TYPE, ROOM_GEOGRAPHICAL_LOCATION,
                ROOM_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                EMPTY_STRING, ROOM_FLOOR, ROOM_TYPE, ROOM_GEOGRAPHICAL_LOCATION,
                ROOM_IDENTIFICATION_NUMBER)),
        //invalid because of regex
        Arguments.of(
            new ServerUpdateRoomRequest(INVALID_PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER, ROOM_FLOOR, ROOM_TYPE, ROOM_GEOGRAPHICAL_LOCATION,
                ROOM_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER, ROOM_FLOOR, ROOM_TYPE, ROOM_GEOGRAPHICAL_LOCATION,
                INVALID_ROOM_IDENTIFICATION_NUMBER)),
        //invalid because of invalid geographical location
        Arguments.of(
            new ServerUpdateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER, ROOM_FLOOR, ROOM_TYPE, INVALID_ROOM_GEOGRAPHICAL_LOCATION_1,
                ROOM_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER, ROOM_FLOOR, ROOM_TYPE, INVALID_ROOM_GEOGRAPHICAL_LOCATION_2,
                ROOM_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER, ROOM_FLOOR, ROOM_TYPE, INVALID_ROOM_GEOGRAPHICAL_LOCATION_3,
                ROOM_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER, ROOM_FLOOR, ROOM_TYPE, INVALID_ROOM_GEOGRAPHICAL_LOCATION_4,
                ROOM_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER, ROOM_FLOOR, ROOM_TYPE, INVALID_ROOM_GEOGRAPHICAL_LOCATION_5,
                ROOM_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER, ROOM_FLOOR, ROOM_TYPE, INVALID_ROOM_GEOGRAPHICAL_LOCATION_6,
                ROOM_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER, ROOM_FLOOR, ROOM_TYPE, INVALID_ROOM_GEOGRAPHICAL_LOCATION_7,
                ROOM_IDENTIFICATION_NUMBER)),
        //invalid because of invalid floor
        Arguments.of(
            new ServerUpdateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER, INVALID_ROOM_FLOOR, ROOM_TYPE, ROOM_GEOGRAPHICAL_LOCATION,
                ROOM_IDENTIFICATION_NUMBER))
    );
  }

  private static Stream<Arguments> provideInvalidResourceServerUpdateRoomRequests() {
    return Stream.of(
        Arguments.of(
            new ServerUpdateRoomRequest(NOT_EXISTING_PARENT_BUILDING_IDENTIFICATION_NUMBER,
                ROOM_NAME, ROOM_NUMBER, ROOM_FLOOR, ROOM_TYPE, ROOM_GEOGRAPHICAL_LOCATION,
                ROOM_IDENTIFICATION_NUMBER)),
        Arguments.of(
            new ServerUpdateRoomRequest(PARENT_BUILDING_IDENTIFICATION_NUMBER, ROOM_NAME,
                ROOM_NUMBER, ROOM_FLOOR, ROOM_TYPE, ROOM_GEOGRAPHICAL_LOCATION,
                NOT_EXISTING_ROOM_IDENTIFICATION_NUMBER))
    );
  }

}
