package edu.kit.tm.cm.smartcampus.building.infrastructure.database;

import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ComponentRepository {

  Component findById(String cin);

  Component update(Component component);

  void delete(String cin);

  Collection<Component> findAllBuildingComponents();

  Collection<Component> findAllRoomComponents();

  Component createBuildingComponent(String bin, Component component);

  Component createRoomComponent(String rin, Component component);


}
