package edu.kit.tm.cm.smartcampus.building.api.controller.notification;

import edu.kit.tm.cm.smartcampus.building.api.configuration.error.ServerExceptionInterceptor;
import edu.kit.tm.cm.smartcampus.building.api.controller.notification.dto.ServerCreateNotificationRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.notification.dto.ServerUpdateNotificationRequest;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.Service;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class represents the notification controller for this domain service. It holds a Spring
 * {@link Bean} of {@link Service} managing all logical operations and running domain constraint
 * validators. It sends REST-Server responses in JSON format via the Spring internal
 * {@link RestController} annotation. In case of errors the {@link ServerExceptionInterceptor}
 * returns given information as REST error response.
 *
 * @author Jonathan Kramer, Johannes von Geisau
 */
@RestController
public class NotificationController implements NotificationOperations {

  private final Service service;

  /**
   * Instantiates a new notification controller for this domain service, it implements all
   * {@link NotificationOperations}.
   *
   * @param service the service which controls all domain logic (constructor injected)
   */
  @Autowired
  public NotificationController(Service service) {
    this.service = service;
  }

  @Override
  public Notification getNotification(String nin) {
    return service.getNotification(nin);
  }

  @Override
  public Notification updateNotification(
      ServerUpdateNotificationRequest serverUpdateNotificationRequest) {
    return service.updateNotification(serverUpdateNotificationRequest);
  }

  @Override
  public void removeNotification(String nin) {
    service.removeNotification(nin);
  }

  @Override
  public Collection<Notification> listNotifications(String in) {
    return service.listNotifications(in);
  }

  @Override
  public Notification createNotification(
      ServerCreateNotificationRequest serverCreateNotificationRequest) {
    return service.createNotification(serverCreateNotificationRequest);
  }

}
