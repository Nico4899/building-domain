package edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories;

import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ComponentRepository extends CrudRepository<Component, String> {

  @Query(
          "SELECT component From component component Where component.parentIdentificationNumber =: #{#bin}")
  Collection<Component> findAllBuildingComponents(@Param("bin") String bin);

  @Query(
          "SELECT component From component component Where component.parentIdentificationNumber =: #{#rin}")
  Collection<Component> findAllRoomComponents(@Param("rin") String rin);
}
