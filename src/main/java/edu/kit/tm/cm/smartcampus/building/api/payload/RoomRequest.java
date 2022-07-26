package edu.kit.tm.cm.smartcampus.building.api.payload;

import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a request for a Room
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequest {

  private String parentIdentificationNumber;

  private String name;

  private String number;

  private int floor;

  private Room.Type type;

  private GeographicalLocation geographicalLocation;

}
