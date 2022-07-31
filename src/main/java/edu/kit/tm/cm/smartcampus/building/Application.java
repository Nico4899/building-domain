package edu.kit.tm.cm.smartcampus.building;

import edu.kit.tm.cm.smartcampus.building.api.controller.building.BuildingOperations;
import edu.kit.tm.cm.smartcampus.building.api.controller.building.dto.ServerCreateBuildingRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.component.ComponentOperations;
import edu.kit.tm.cm.smartcampus.building.api.controller.component.dto.ServerCreateComponentRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.notification.NotificationOperations;
import edu.kit.tm.cm.smartcampus.building.api.controller.notification.dto.ServerCreateNotificationRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.room.RoomOperations;
import edu.kit.tm.cm.smartcampus.building.api.controller.room.dto.ServerCreateRoomRequest;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Floors;
import edu.kit.tm.cm.smartcampus.building.logic.model.GeographicalLocation;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import static edu.kit.tm.cm.smartcampus.building.logic.model.Component.Type.ELEVATOR;

/** The main entry point of the Application. */
@SpringBootApplication
public class Application {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Component
  public static class Test implements CommandLineRunner {

    @Autowired
    BuildingOperations buildingOperations;
    @Autowired
    RoomOperations roomOperations;

    @Autowired
    ComponentOperations componentOperations;

    @Autowired
    NotificationOperations notificationOperations;

    @Override
    public void run(final String... args) throws Exception {
      /*GeographicalLocation[] geographicalLocations = new GeographicalLocation[100];
      for(int i = 0; i < 100; i++) {
        geographicalLocations[i] = new GeographicalLocation();
        geographicalLocations[i].setLatitude(1);
        geographicalLocations[i].setLongitude(2);
      }

      Floors[] floors = new Floors[100];
      for(int i = 0; i < 100; i++) {
        floors[i] = new Floors();
        floors[i].setHighestFloor(9);
        floors[i].setLowestFloor(0);
      }

      for(int i = 0; i < 15; i++) {
        ServerCreateBuildingRequest br = new ServerCreateBuildingRequest("t", "30.30",
            Building.CampusLocation.EAST_CAMPUS, geographicalLocations[i], floors[i]
        );
        Building b = buildingOperations.createBuilding(br);
        roomOperations.createRoom(new ServerCreateRoomRequest(b.getIdentificationNumber(),"raumname",
            "362", 0, Room.Type.LECTURE_ROOM, geographicalLocations[i + 15]
          )
        );
        Room r = roomOperations.createRoom(new ServerCreateRoomRequest(b.getIdentificationNumber(),"raumname",
                "362", 0, Room.Type.LECTURE_ROOM, geographicalLocations[i + 30]
            )
        );

        edu.kit.tm.cm.smartcampus.building.logic.model.Component c = componentOperations.createComponent(
            new ServerCreateComponentRequest(
                r.getIdentificationNumber(),
                ELEVATOR,
                "richtig cooler component",
                geographicalLocations[i + 45]
              )
            );

        notificationOperations.createNotification(new ServerCreateNotificationRequest(
            c.getIdentificationNumber(), "Heizung ist kacke", "Die Heizung heizt mit Gas"
            )
        );

      }*/
      notificationOperations.createNotification(new ServerCreateNotificationRequest("b-18",
          "aram", "samsam"));
    }
  }
}



