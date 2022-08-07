package edu.kit.tm.cm.smartcampus.building;

import edu.kit.tm.cm.smartcampus.building.api.controller.FloorsDTO;
import edu.kit.tm.cm.smartcampus.building.api.controller.GeographicalLocationDTO;
import edu.kit.tm.cm.smartcampus.building.api.controller.building.BuildingOperations;
import edu.kit.tm.cm.smartcampus.building.api.controller.building.dto.ServerCreateBuildingRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.notification.NotificationOperations;
import edu.kit.tm.cm.smartcampus.building.api.controller.notification.dto.ServerCreateNotificationRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.notification.dto.ServerUpdateNotificationRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.room.RoomOperations;
import edu.kit.tm.cm.smartcampus.building.api.controller.room.dto.ServerCreateRoomRequest;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Building.CampusLocation;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room;
import edu.kit.tm.cm.smartcampus.building.logic.model.Room.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

/**
 * The main entry point of the Application.
 *
 * @author Jonathan Kramer, Johannes von Geisau
 */
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



