package edu.kit.tm.cm.smartcampus.building.api.controller.component;

import edu.kit.tm.cm.smartcampus.building.api.controller.GeographicalLocationDTO;
import edu.kit.tm.cm.smartcampus.building.api.controller.component.dto.ServerCreateComponentRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.component.dto.ServerUpdateComponentRequest;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.Service;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class ComponentControllerTests {

  private static final Service SERVICE = mock(Service.class);
  private static final ComponentController COMPONENT_CONTROLLER = new ComponentController(SERVICE);
  //Attributes
  private static final String COMPONENT_IDENTIFICATION_NUMBER = "c-1";
  private static final String BUILDING_IDENTIFICATION_NUMBER = "b-1";
  private static final String COMPONENT_DESCRIPTION = "Component for testing";
  private static final Component.Type COMPONENT_TYPE = Component.Type.ELEVATOR;
  private static final GeographicalLocationDTO COMPONENT_GEOGRAPHICAL_LOCATION =
      new GeographicalLocationDTO(42.123, 43.111111);
  //Requests
  private static final ServerCreateComponentRequest SERVER_CREATE_COMPONENT_REQUEST =
      new ServerCreateComponentRequest(BUILDING_IDENTIFICATION_NUMBER, COMPONENT_TYPE, COMPONENT_DESCRIPTION,
          COMPONENT_GEOGRAPHICAL_LOCATION);
  private static final ServerUpdateComponentRequest SERVER_UPDATE_COMPONENT_REQUEST =
      new ServerUpdateComponentRequest(BUILDING_IDENTIFICATION_NUMBER, COMPONENT_TYPE, COMPONENT_DESCRIPTION,
          COMPONENT_GEOGRAPHICAL_LOCATION, COMPONENT_IDENTIFICATION_NUMBER);
  @Test
  void createComponent_ShouldCreateComponent() {
    COMPONENT_CONTROLLER.createComponent(SERVER_CREATE_COMPONENT_REQUEST);
    Mockito.verify(SERVICE).createComponent(SERVER_CREATE_COMPONENT_REQUEST);
  }

  @Test
  void getComponent_ShouldGetComponent() {
    COMPONENT_CONTROLLER.getComponent(COMPONENT_IDENTIFICATION_NUMBER);
    Mockito.verify(SERVICE).getComponent(COMPONENT_IDENTIFICATION_NUMBER);
  }

  @Test
  void listComponents_ShouldListComponents() {
    COMPONENT_CONTROLLER.listComponents(BUILDING_IDENTIFICATION_NUMBER);
    Mockito.verify(SERVICE).listComponents(BUILDING_IDENTIFICATION_NUMBER);
  }

  @Test
  void updateComponent_ShouldUpdateComponent() {
    COMPONENT_CONTROLLER.updateComponent(SERVER_UPDATE_COMPONENT_REQUEST);
    Mockito.verify(SERVICE).updateComponent(SERVER_UPDATE_COMPONENT_REQUEST);
  }

  @Test
  void removeComponent_ShouldRemoveComponent() {
    COMPONENT_CONTROLLER.removeComponent(COMPONENT_IDENTIFICATION_NUMBER);
    Mockito.verify(SERVICE).removeComponent(COMPONENT_IDENTIFICATION_NUMBER);
  }

}
