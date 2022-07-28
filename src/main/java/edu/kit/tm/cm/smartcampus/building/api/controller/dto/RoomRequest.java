package edu.kit.tm.cm.smartcampus.building.api.controller.dto;

import edu.kit.tm.cm.smartcampus.building.logic.model.GeographicalLocation;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** This class represents a request for a Room. */
@Setter
@Getter
@AllArgsConstructor
public class RoomRequest {

  private String parentIdentificationNumber;

  private String name;

  private String number;

  private int floor;

  private Room.Type type;

  private GeographicalLocation geographicalLocation;
}
