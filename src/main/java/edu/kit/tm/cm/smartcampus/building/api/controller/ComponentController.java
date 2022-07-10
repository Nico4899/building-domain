package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.infrastructure.service.ComponentService;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ComponentController implements ComponentApi {

  private final ComponentService componentService;

  public ComponentController(ComponentService componentService) {
    this.componentService = componentService;
  }

  @Override
  public Collection<Component> listBuildingComponents(String bin) {
    return componentService.listBuildingComponents(bin);
  }

  @Override
  public Component createBuildingComponent(String bin, Component component) {
    return componentService.createBuildingComponent(bin, component);
  }

  @Override
  public Collection<Component> listRoomComponents(String rin) {
    return componentService.listRoomComponents(rin);
  }

  @Override
  public Component createRoomComponent(String rin, Component component) {
    return componentService.createRoomComponent(rin, component);
  }

  @Override
  public Component getComponent(String cin) {
    return componentService.getComponent(cin);
  }

  @Override
  public Component updateComponent(String cin, Component component) {//Todo warum cin hier , Api anpasen?
    return componentService.updateComponent(component);
  }

  @Override
  public void removeComponent(String cin) {
    componentService.deleteComponent(cin);
  }
}
