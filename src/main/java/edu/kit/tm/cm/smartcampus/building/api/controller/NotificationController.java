package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class NotificationController implements NotificationApi {

  @Override
  public Notification getNotification(String nin) {
    return null;
  }

  @Override
  public Notification updateNotification(String nin, Notification notification) {
    return null;
  }

  @Override
  public void deleteNotification(String nin) {}

  @Override
  public Collection<Building> listBuildingNotifications(String bin) {
    return null;
  }

  @Override
  public Notification createBuildingNotification(String bin, Notification notification) {
    return null;
  }

  @Override
  public Collection<Notification> listRoomNotifications(String rin) {
    return null;
  }

  @Override
  public Notification createRoomNotification(String rin, Notification notification) {
    return null;
  }

  @Override
  public Collection<Notification> listComponentNotifications(String cin) {
    return null;
  }

  @Override
  public Notification createComponentNotification(String cin, Notification notification) {
    return null;
  }
}
