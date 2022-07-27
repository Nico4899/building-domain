package edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories;

import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import java.util.Collection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This repository uses the standard implementation of {@link CrudRepository} and contains
 * {@link Notification} entities. Primary keys are here of type {@link String} and have format: 'n-
 * (positive int)'
 */
@Repository
public interface NotificationRepository extends CrudRepository<Notification, String> {

  @Query("SELECT notification From notification notification Where notification"
      + ".parentIdentificationNumber = ?1")
  Collection<Notification> findAllBuildingNotifications(
      @Param("bin") String parentIdentificationNumber);

  @Query("SELECT notification From notification notification Where notification"
      + ".parentIdentificationNumber = ?1")
  Collection<Notification> findAllRoomNotifications(
      @Param("rin") String parentIdentificationNumber);

  @Query("SELECT notification From notification notification Where notification"
      + ".parentIdentificationNumber = ?1")
  Collection<Notification> findAllComponentNotifications(
      @Param("cin") String parentIdentificationNumber);

  @Modifying
  @Query("DELETE From notification notification Where notification.parentIdentificationNumber = ?1")
  void cleanUp(@Param("parentIdentificationNumber") String parentIdentificationNumber);
}
