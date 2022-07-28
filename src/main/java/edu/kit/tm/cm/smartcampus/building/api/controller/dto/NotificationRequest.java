package edu.kit.tm.cm.smartcampus.building.api.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** This class represents a request for a Notification. */
@Setter
@Getter
@AllArgsConstructor
public class NotificationRequest {

  private String parentIdentificationNumber;

  private String title;

  private String description;
}
