package edu.kit.tm.cm.smartcampus.building.api.controller.notification;

import edu.kit.tm.cm.smartcampus.building.api.controller.notification.dto.ServerCreateNotificationRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.notification.dto.ServerUpdateNotificationRequest;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import java.util.Collection;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This interface provides all rest server operations connected "/notifications" requests.
 */
@RequestMapping
public interface NotificationOperations {

  /**
   * Create a new {@link Notification} on "/notifications" url in this domain service.
   *
   * @param serverCreateNotificationRequest the request for the notification to be created in this
   *                                        service
   * @return the created notification
   */
  @PostMapping("/notifications")
  Notification createNotification(
      @RequestBody ServerCreateNotificationRequest serverCreateNotificationRequest);

  /**
   * Get a specific {@link Notification} on "/notifications/{nin}" url from tis domain service.
   *
   * @param nin identification number of the requested notification in this domain service
   * @return the requested notification
   */
  @GetMapping("/notifications/{nin}")
  Notification getNotification(@PathVariable String nin);

  /**
   * Update a specific {@link Notification} on "/notifications" url in this domain service.
   *
   * @param serverUpdateNotificationRequest the notification to be updated in this service
   * @return the updated notification with updated attributes
   */
  @PutMapping("/notifications")
  Notification updateNotification(
      @RequestBody ServerUpdateNotificationRequest serverUpdateNotificationRequest);

  /**
   * Remove a {@link Notification} on "/notifications/{nin}" from this domain service.
   *
   * @param nin identification number of the notification to be removed
   */
  @DeleteMapping("/notifications/{nin}")
  void removeNotification(@PathVariable String nin);

  /**
   * List all notifications that belong to a certain parent on "{in}/notifications" url.
   *
   * @param in identification number of the parent
   * @return a collection of all {@link Notification} this domain service manages that belong to the
   *     building
   */
  @GetMapping("{in}/notifications")
  Collection<Notification> listNotifications(@PathVariable String in);
}
