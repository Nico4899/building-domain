package edu.kit.tm.cm.smartcampus.building.infrastructure.database;

import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import org.springframework.data.repository.CrudRepository;

public interface ComponentRepository extends CrudRepository<Component, String> {}
