package edu.kit.tm.cm.smartcampus.building.api.controller.building;

import static org.mockito.Mockito.mock;

import edu.kit.tm.cm.smartcampus.building.api.controller.FloorsDTO;
import edu.kit.tm.cm.smartcampus.building.api.controller.GeographicalLocationDTO;
import edu.kit.tm.cm.smartcampus.building.api.controller.building.dto.ServerCreateBuildingRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.building.dto.ServerUpdateBuildingRequest;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.Service;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building.CampusLocation;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BuildingControllerTest {

  //Mocks
  private static final Service SERVICE = mock(Service.class);
  private static final BuildingController BUILDING_CONTROLLER = new BuildingController(SERVICE);
  //Attributes
  private static final String BUILDING_IDENTIFICATION_NUMBER = "b-1";
  private static final String BUILDING_NAME = "Testgebäude";
  private static final String BUILDING_NUMBER = "12.12";
  private static final CampusLocation BUILDING_CAMPUS_LOCATION = CampusLocation.SOUTH_CAMPUS;
  private static final GeographicalLocationDTO BUILDING_GEOGRAPHICAL_LOCATION =
      new GeographicalLocationDTO(42.123, 43.111111);
  private static final FloorsDTO BUILDING_FLOORS = new FloorsDTO(5, -1);
  //Requests
  private static final ServerCreateBuildingRequest SERVER_CREATE_BUILDING_REQUEST =
      new ServerCreateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER,
          BUILDING_CAMPUS_LOCATION, BUILDING_GEOGRAPHICAL_LOCATION, BUILDING_FLOORS);
  private static final ServerUpdateBuildingRequest SERVER_UPDATE_BUILDING_REQUEST =
      new ServerUpdateBuildingRequest(BUILDING_NAME, BUILDING_NUMBER,
          BUILDING_CAMPUS_LOCATION,
          BUILDING_GEOGRAPHICAL_LOCATION, BUILDING_FLOORS,
          BUILDING_IDENTIFICATION_NUMBER);

  @Test
  void createBuilding_ShouldCreateBuilding() {
    BUILDING_CONTROLLER.createBuilding(SERVER_CREATE_BUILDING_REQUEST);
    Mockito.verify(SERVICE).createBuilding(SERVER_CREATE_BUILDING_REQUEST);
  }

  @Test
  void getBuilding_ShouldGetBuilding() {
    BUILDING_CONTROLLER.getBuilding(BUILDING_IDENTIFICATION_NUMBER);
    Mockito.verify(SERVICE).getBuilding(BUILDING_IDENTIFICATION_NUMBER);
  }

  @Test
  void listBuildings_ShouldListBuildings() {
    BUILDING_CONTROLLER.listBuildings();
    Mockito.verify(SERVICE).listBuildings();
  }

  @Test
  void updateBuilding_ShouldUpdateBuilding() {
    BUILDING_CONTROLLER.updateBuilding(SERVER_UPDATE_BUILDING_REQUEST);
    Mockito.verify(SERVICE).updateBuilding(SERVER_UPDATE_BUILDING_REQUEST);
  }

  @Test
  void removeBuilding_ShouldRemoveBuilding() {
    BUILDING_CONTROLLER.removeBuilding(BUILDING_IDENTIFICATION_NUMBER);
    Mockito.verify(SERVICE).removeBuilding(BUILDING_IDENTIFICATION_NUMBER);
  }

}
