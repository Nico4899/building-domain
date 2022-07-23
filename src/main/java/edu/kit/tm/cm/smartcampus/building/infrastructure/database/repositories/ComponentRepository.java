package edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories;

import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

//TODO javadocs
@Repository
public interface ComponentRepository extends CrudRepository<Component, String> {

  @Query(
      "SELECT component From component component Where component.parentIdentificationNumber =: "
          + "#{#parentIdentificationNumber}")
  Collection<Component> findAllBuildingComponents(@Param("bin") String parentIdentificationNumber);

  @Query(
      "SELECT component From component component Where component.parentIdentificationNumber =: "
          + "#{#parentIdentificationNumber}")
  Collection<Component> findAllRoomComponents(@Param("rin") String parentIdentificationNumber);

  @Modifying
  @Query(
      "DELETE From component Where component.parentIdentificationNumber =: #{#parentIdentificationNumber}")
  void cleanUp(@Param("bin") String parentIdentificationNumber);
}
