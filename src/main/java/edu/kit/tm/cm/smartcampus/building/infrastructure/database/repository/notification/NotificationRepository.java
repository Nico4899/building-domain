package edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.notification;

import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import java.util.Collection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * This repository uses the standard implementation of {@link CrudRepository} and contains
 * {@link Notification} entities. Primary keys are here of type {@link String} and have format: 'n-
 * (positive int)'
 *
 * @author Jonathan Kramer, Johannes von Geisau
 */
@Repository
public interface NotificationRepository extends CrudRepository<Notification, String> {

  @Query("SELECT notification From notification notification Where notification"
      + ".parentIdentificationNumber = ?1")
  Collection<Notification> findAllNotificationsByParentId(
      @Param("bin") String parentIdentificationNumber);

  @Transactional
  @Modifying
  @Query("DELETE From notification notification Where notification.parentIdentificationNumber = ?1")
  void deleteByParentId(@Param("parentIdentificationNumber") String parentIdentificationNumber);
}
