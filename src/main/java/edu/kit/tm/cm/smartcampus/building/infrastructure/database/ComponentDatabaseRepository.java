package edu.kit.tm.cm.smartcampus.building.infrastructure.database;

import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

@Repository
public class ComponentDatabaseRepository implements ComponentRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Component findById(String cin) {
    return entityManager.find(Component.class, cin); //Todo funktioniert das? 0_o
  }

  @Override
  public Component update(Component component) {
    return entityManager.merge(component);
  }

  @Override
  public void delete(String cin) {
    entityManager.remove(entityManager.find(Component.class, cin));
  }

  @Override
  public Collection<Component> findAllBuildingComponents() {
    Query query = entityManager.createQuery("SELECT c FROM component c",
            Component.class); //Todo SQL Abfrage richtig

    return query.getResultList();
  }

  @Override
  public Collection<Component> findAllRoomComponents() {
    // Todo analog s. oben
    return null;
  }

  @Override
  public Component createBuildingComponent(String bin, Component component) {
    entityManager.persist(component);
    return entityManager.find(Component.class, component.getIdentificationNumber());
  }

  @Override
  public Component createRoomComponent(String rin, Component component) {
    return null; //Todo unnoetig?
  }
}
