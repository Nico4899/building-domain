package edu.kit.tm.cm.smartcampus.building.infrastructure.database.repository.room;

import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import java.util.Collection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * This repository uses the standard implementation of {@link CrudRepository} and contains
 * {@link Room} entities. Primary keys are here of type {@link String} and have format: 'r-(positive
 * int)'
 *
 * @author Jonathan Kramer, Johannes von Geisau
 */
@Repository
public interface RoomRepository extends CrudRepository<Room, String> {

  @Query("SELECT room From room room Where room.parentIdentificationNumber = ?1")
  Collection<Room> findAllRoomsByParentId(@Param("bin") String parentIdentificationNumber);

  @Transactional
  @Modifying
  @Query("DELETE From room room Where room.parentIdentificationNumber = ?1")
  void deleteByParentId(@Param("parentIdentificationNumber") String parentIdentificationNumber);
}
