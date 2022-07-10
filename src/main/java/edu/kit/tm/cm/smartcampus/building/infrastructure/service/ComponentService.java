package edu.kit.tm.cm.smartcampus.building.infrastructure.service;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.ComponentRepository;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ComponentService {

  private final ComponentRepository componentRepository;

  @Autowired
  public ComponentService(ComponentRepository componentRepository) {
    this.componentRepository = componentRepository;
  }

  public Collection<Component> listBuildingComponents(String bin) {
    return componentRepository.findAllComponents(bin);
  }

  public Component createBuildingComponent(String bin, Component component) {
    return componentRepository.createBuildingComponent(bin, component);
  }

  public Component createRoomComponent(String rin, Component component) {
    return componentRepository.createRoomComponent(rin, component);
  }

  public Collection<Component> listRoomComponents(String rin) {
    return componentRepository.findAllComponents(rin);
  }

  public Component getComponent(String cin) {
    return componentRepository.findById(cin).get();
  }

  public Component updateComponent(Component component) {
    return componentRepository.save(component);
  }

  public void deleteComponent(String cin) {
    componentRepository.deleteById(cin);
  }

}
