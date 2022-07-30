package edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.component;

import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import java.util.Collection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This repository uses the standard implementation of {@link CrudRepository} and contains
 * {@link Component} entities. Primary keys are here of type {@link String} and have format:
 * 'c-(positive int)'
 */
@Repository
public interface ComponentRepository extends CrudRepository<Component, String> {

  @Query("SELECT component From component component Where component.parentIdentificationNumber = "
      + "?1")
  Collection<Component> findAllComponents(@Param("bin") String parentIdentificationNumber);

  /**
   * Deletes all components that have the given parentIdentificationNumber.
   * @param parentIdentificationNumber the parentIdentificationNumber
   */
  @Modifying
  @Query("DELETE From component component Where component.parentIdentificationNumber = ?1")
  void cleanUp(@Param("parentIdentificationNumber") String parentIdentificationNumber);
}
