package edu.kit.tm.cm.smartcampus.building.api.controller.room.dto;

import edu.kit.tm.cm.smartcampus.building.api.controller.GeographicalLocationDTO;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a server update request for a room.
 *
 * @author Jonathan Kramer, Johannes von Geisau
 */
@Setter
@Getter
@AllArgsConstructor
public class ServerUpdateRoomRequest {

  private String parentIdentificationNumber;
  private String name;
  private String number;
  private int floor;
  private Room.Type type;
  private GeographicalLocationDTO geographicalLocation;
  private String identificationNumber;
}
