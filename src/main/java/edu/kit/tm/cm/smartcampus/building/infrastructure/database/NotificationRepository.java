package edu.kit.tm.cm.smartcampus.building.infrastructure.database;

import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface NotificationRepository extends CrudRepository<Notification, String> {

  @Query(
      "SELECT notification From notification notification Where notification.parentIdentificationNumber =: #{#bin}")
  Collection<Component> findAllBuildingNotifications(@Param("bin") String bin);

  @Query(
      "SELECT notification From notification notification Where notification.parentIdentificationNumber =: #{#rin}")
  Collection<Component> findAllRoomNotifications(@Param("rin") String rin);

  @Query(
      "SELECT notification From notification notification Where notification.parentIdentificationNumber =: #{#cin}")
  Collection<Component> findAllComponentNotifications(@Param("cin") String cin);
}
