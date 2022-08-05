package edu.kit.tm.cm.smartcampus.building.api.controller.building;

import static org.mockito.Mockito.mock;

import edu.kit.tm.cm.smartcampus.building.infrastructure.service.Service;
import org.junit.jupiter.api.Test;

public class BuildingControllerTest {

  private static final Service SERVICE = mock(Service.class);
  private static final BuildingController BUILDING_CONTROLLER = new BuildingController(SERVICE);

  @Test
  void createBuilding_ShouldCreateBuilding(){

  }

  @Test
  void getBuilding_ShouldGetBuilding(){

  }

  @Test
  void listBuildings_ShouldListBuildings(){

  }

  @Test
  void updateBuilding_ShouldUpdateBuilding(){

  }

  @Test
  void removeBuilding_ShouldRemoveBuilding(){

  }

}
