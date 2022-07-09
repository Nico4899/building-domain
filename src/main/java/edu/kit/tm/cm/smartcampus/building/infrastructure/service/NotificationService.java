package edu.kit.tm.cm.smartcampus.building.infrastructure.service;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.NotificationRepository;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class NotificationService {

  private final NotificationRepository notificationRepository;

  public NotificationService(NotificationRepository notificationRepository) {
    this.notificationRepository = notificationRepository;
  }

  public Collection<Notification> getNotifications() {
    Collection<Notification> notifications = new ArrayList<>();
    for (Notification notification : notificationRepository.findAll()) notifications.add(notification);
    return notifications;
  }



}
