package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ComponentController implements ComponentApi {

  @Override
  public Collection<Component> listBuildingComponents(String bin) {
    return null;
  }

  @Override
  public Component createBuildingComponent(String bin, Component component) {
    return null;
  }

  @Override
  public Collection<Component> listRoomComponents(String rin) {
    return null;
  }

  @Override
  public Component createRoomComponent(String rin, Component component) {
    return null;
  }

  @Override
  public Component getComponent(String cin) {
    return null;
  }

  @Override
  public Component updateComponent(String cin, Component component) {
    return null;
  }

  @Override
  public void removeComponent(String cin) {
  }
}
