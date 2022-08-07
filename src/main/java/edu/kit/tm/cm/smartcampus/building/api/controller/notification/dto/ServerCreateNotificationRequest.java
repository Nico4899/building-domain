package edu.kit.tm.cm.smartcampus.building.api.controller.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a request for a Notification.
 */
@Setter
@Getter
@AllArgsConstructor
public class ServerCreateNotificationRequest {

  private String parentIdentificationNumber;
  private String title;
  private String description;
}
