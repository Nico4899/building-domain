package edu.kit.tm.cm.smartcampus.building.infrastructure.database;

import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ComponentRepository extends CrudRepository<Component, String> {

  @Query(value = "SELECT '*' From component Where 'parent_identification_number' = 'parent_id'", nativeQuery = true)
  Collection<Component> findAllComponents(@Param("parent_id") String parent_id);

  Component createComponent(Component component);


}
