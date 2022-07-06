package edu.kit.tm.cm.smartcampus.building.logic.model;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface BuildingRepository {

    Optional<Building> findByBin(String bin);

    Collection<Building> findAll();

    String create(Building building);

    boolean delete(String bin);

    boolean update(Building building);

}
