package edu.kit.tm.cm.smartcampus.building.api.controller.notification;

import edu.kit.tm.cm.smartcampus.building.api.controller.notification.dto.ServerCreateNotificationRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.notification.dto.ServerUpdateNotificationRequest;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.Service;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class NotificationControllerTests {

  private static final Service SERVICE = mock(Service.class);
  private static final NotificationController NOTIFICATION_CONTROLLER = new NotificationController(SERVICE);
  //Attributes
  private static final String NOTIFICATION_IDENTIFICATION_NUMBER = "c-1";
  private static final String BUILDING_IDENTIFICATION_NUMBER = "b-1";
  private static final String NOTIFICATION_DESCRIPTION = "Notification for testing";
  private static final String NOTIFICATION_TITLE = "TestNotification";
  //Requests
  private static final ServerCreateNotificationRequest SERVER_CREATE_NOTIFICATION_REQUEST =
      new ServerCreateNotificationRequest(BUILDING_IDENTIFICATION_NUMBER, NOTIFICATION_TITLE, NOTIFICATION_DESCRIPTION);
  private static final ServerUpdateNotificationRequest SERVER_UPDATE_NOTIFICATION_REQUEST =
      new ServerUpdateNotificationRequest(BUILDING_IDENTIFICATION_NUMBER, NOTIFICATION_TITLE, NOTIFICATION_DESCRIPTION,
          NOTIFICATION_IDENTIFICATION_NUMBER);
  @Test
  void createNotification_ShouldCreateNotification() {
    NOTIFICATION_CONTROLLER.createNotification(SERVER_CREATE_NOTIFICATION_REQUEST);
    Mockito.verify(SERVICE).createNotification(SERVER_CREATE_NOTIFICATION_REQUEST);
  }

  @Test
  void getNotification_ShouldGetNotification() {
    NOTIFICATION_CONTROLLER.getNotification(NOTIFICATION_IDENTIFICATION_NUMBER);
    Mockito.verify(SERVICE).getNotification(NOTIFICATION_IDENTIFICATION_NUMBER);
  }

  @Test
  void listNotifications_ShouldListNotifications() {
    NOTIFICATION_CONTROLLER.listNotifications(BUILDING_IDENTIFICATION_NUMBER);
    Mockito.verify(SERVICE).listNotifications(BUILDING_IDENTIFICATION_NUMBER);
  }

  @Test
  void updateNotification_ShouldUpdateNotification() {
    NOTIFICATION_CONTROLLER.updateNotification(SERVER_UPDATE_NOTIFICATION_REQUEST);
    Mockito.verify(SERVICE).updateNotification(SERVER_UPDATE_NOTIFICATION_REQUEST);
  }

  @Test
  void removeNotification_ShouldRemoveNotification() {
    NOTIFICATION_CONTROLLER.removeNotification(NOTIFICATION_IDENTIFICATION_NUMBER);
    Mockito.verify(SERVICE).removeNotification(NOTIFICATION_IDENTIFICATION_NUMBER);
  }
}
