package edu.kit.tm.cm.smartcampus.building.api.requests;

import edu.kit.tm.cm.smartcampus.building.logic.model.GeographicalLocation;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import lombok.*;

/**
 * This class represents a request for a Room.
 */
@Data
@Setter
@Getter
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
