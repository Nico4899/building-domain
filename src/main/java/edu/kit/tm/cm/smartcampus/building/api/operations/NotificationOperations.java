package edu.kit.tm.cm.smartcampus.building.api.operations;

import edu.kit.tm.cm.smartcampus.building.api.requests.NotificationRequest;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
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
@RequestMapping("/notifications")
public interface NotificationOperations {

  /**
   * Create a new {@link Notification} on "/notifications" url in this domain service.
   *
   * @param notificationRequest the request for the notification to be created in this service
   * @return the created notification
   */
  @PostMapping("")
  Notification createNotification(@RequestBody NotificationRequest notificationRequest);

  /**
   * Get a specific {@link Notification} on "/notifications/{nin}" url from tis domain service.
   *
   * @param nin identification number of the requested notification in this domain service
   * @return the requested notification
   */
  @GetMapping("/{nin}")
  Notification getNotification(@PathVariable String nin);

  /**
   * Update a specific {@link Notification} on "/notifications" url in this domain service.
   *
   * @param notification the notification to be updated in this service
   * @return the updated notification with updated attributes
   */
  @PutMapping("")
  Notification updateNotification(@RequestBody Notification notification);

  /**
   * Remove a {@link Notification} on "/notifications/{nin}" from this domain service.
   *
   * @param nin identification number of the notification to be removed
   */
  @DeleteMapping("/{nin}")
  void removeNotification(@PathVariable String nin);

}
