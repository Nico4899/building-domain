package edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.building;

import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This repository uses the standard implementation of {@link CrudRepository} and contains
 * {@link Building} entities. Primary keys are here of type {@link String} and have format:
 * 'b-(positive int)'
 */
@Repository
public interface BuildingRepository extends CrudRepository<Building, String> {

}
