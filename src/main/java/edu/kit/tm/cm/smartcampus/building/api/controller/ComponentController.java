package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.api.payload.ComponentRequest;
import edu.kit.tm.cm.smartcampus.building.api.payload.ComponentResponse;
import edu.kit.tm.cm.smartcampus.building.api.payload.ComponentsResponse;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComponentController implements ComponentApi {

  @Override
  public ComponentsResponse getBuildingComponents(String bin) {
    return null;
  }

  @Override
  public ComponentResponse createBuildingComponent(String bin, ComponentRequest componentRequest) {
    return null;
  }

  @Override
  public ComponentsResponse getRoomComponents(String rin) {
    return null;
  }

  @Override
  public ComponentResponse createRoomComponent(String rin, ComponentRequest componentRequest) {
    return null;
  }

  @Override
  public ComponentResponse getComponent(String cin) {
    return null;
  }

  @Override
  public ComponentResponse editComponent(String cin, ComponentRequest componentRequest) {
    return null;
  }

  @Override
  public void deleteComponent(String cin) {}
}
