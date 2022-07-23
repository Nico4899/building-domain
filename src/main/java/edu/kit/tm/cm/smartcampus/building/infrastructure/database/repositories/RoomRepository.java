package edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories;

import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

//TODO javadocs
public interface RoomRepository extends CrudRepository<Room, String> {

  @Query(
      "SELECT room From room room Where room.parentIdentificationNumber =: #{#parentIdentificationNumber}")
  Collection<Room> findAllBuildingRooms(@Param("bin") String parentIdentificationNumber);

  @Modifying
  @Query(
      "DELETE From room Where room.parentIdentificationNumber =: #{#parentIdentificationNumber}")
  void cleanUp(@Param("bin") String parentIdentificationNumber);
}
