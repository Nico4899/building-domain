package edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories;

import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface NotificationRepository extends CrudRepository<Notification, String> {

  @Query(
      "SELECT notification From notification notification Where notification.parentIdentificationNumber =: #{#parentIdentificationNumber}")
  Collection<Notification> findAllBuildingNotifications(
      @Param("bin") String parentIdentificationNumber);

  @Query(
      "SELECT notification From notification notification Where notification.parentIdentificationNumber =: #{#parentIdentificationNumber}")
  Collection<Notification> findAllRoomNotifications(
      @Param("rin") String parentIdentificationNumber);

  @Query(
      "SELECT notification From notification notification Where notification.parentIdentificationNumber =: #{#parentIdentificationNumber}")
  Collection<Notification> findAllComponentNotifications(
      @Param("cin") String parentIdentificationNumber);

  @Query(
      "DELETE From notification Where notification .parentIdentificationNumber =: #{#parentIdentificationNumber}")//TODO prüfen
  void cleanUpBuilding(@Param("bin") String parentIdentificationNumber);

  @Query(
      "DELETE From notification Where notification.parentIdentificationNumber =: #{#parentIdentificationNumber}")
  void cleanUpRoom(@Param("rin") String parentIdentificationNumber);

  @Query(
      "DELETE From notification Where notification.parentIdentificationNumber =: #{#parentIdentificationNumber}")
  void cleanUpComponent(@Param("cin") String parentIdentificationNumber);
}
