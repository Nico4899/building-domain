package edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories;

import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import java.util.Collection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This repository uses the standard implementation of {@link CrudRepository} and contains
 * {@link Room} entities. Primary keys are here of type {@link String} and have format: 'r-(positive
 * int)'
 */
@Repository
public interface RoomRepository extends CrudRepository<Room, String> {

  @Query("SELECT room From room room Where room.parentIdentificationNumber = ?1")
  Collection<Room> findAllBuildingRooms(@Param("bin") String parentIdentificationNumber);

  @Modifying
  @Query("DELETE From room room Where room.parentIdentificationNumber = ?1")
  void cleanUp(@Param("parentIdentificationNumber") String parentIdentificationNumber);
}
