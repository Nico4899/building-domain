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

}



