package edu.kit.tm.cm.smartcampus.building.infrastructure.database;

import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends CrudRepository<Building, String> {}
