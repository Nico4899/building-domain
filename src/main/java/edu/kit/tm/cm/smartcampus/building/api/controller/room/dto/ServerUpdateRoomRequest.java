package edu.kit.tm.cm.smartcampus.building.api.controller.room.dto;

import edu.kit.tm.cm.smartcampus.building.logic.model.GeographicalLocation;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ServerUpdateRoomRequest {
  private String parentIdentificationNumber;
  private String name;
  private String number;
  private int floor;
  private Room.Type type;
  private GeographicalLocation geographicalLocation;
  private String identificationNumber;
}
