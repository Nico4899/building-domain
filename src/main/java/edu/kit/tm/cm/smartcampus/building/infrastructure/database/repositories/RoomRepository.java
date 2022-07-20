package edu.kit.tm.cm.smartcampus.building.infrastructure.database.repositories;

import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface RoomRepository extends CrudRepository<Room, String> {

  @Query(
      "SELECT room From room room Where room.parentIdentificationNumber =: #{#parentIdentificationNumber}")
  Collection<Room> findAllBuildingRooms(@Param("bin") String parentIdentificationNumber);
}
