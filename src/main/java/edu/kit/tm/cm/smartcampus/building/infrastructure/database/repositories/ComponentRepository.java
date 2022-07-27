package edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories;

import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * This repository uses the standard implementation of {@link CrudRepository} and contains {@link
 * Component} entities. Primary keys are here of type {@link String} and have format: 'c-(positive
 * int)'
 */
@Repository
public interface ComponentRepository extends CrudRepository<Component, String> {

  @Query("SELECT component From component component Where component.parentIdentificationNumber = ?1")
  Collection<Component> findAllBuildingComponents(@Param("bin") String parentIdentificationNumber);

  @Query("SELECT component From component component Where component.parentIdentificationNumber = ?1")
  Collection<Component> findAllRoomComponents(@Param("rin") String parentIdentificationNumber);

  @Modifying
  @Query("DELETE From component component Where component.parentIdentificationNumber = ?1")
  void cleanUp(@Param("parentIdentificationNumber") String parentIdentificationNumber);
}
