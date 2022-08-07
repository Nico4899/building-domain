package edu.kit.tm.cm.smartcampus.building.api.controller.room.dto;

import edu.kit.tm.cm.smartcampus.building.api.controller.GeographicalLocationDTO;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a request for a Room.
 *
 * @author Jonathan Kramer, Johannes von Geisau
 */
@Setter
@Getter
@AllArgsConstructor
public class ServerCreateRoomRequest {

  private String parentIdentificationNumber;
  private String name;
  private String number;
  private int floor;
  private Room.Type type;
  private GeographicalLocationDTO geographicalLocation;
}
