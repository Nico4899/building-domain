package edu.kit.tm.cm.smartcampus.building.infrastructure.database;

import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, String> {}
