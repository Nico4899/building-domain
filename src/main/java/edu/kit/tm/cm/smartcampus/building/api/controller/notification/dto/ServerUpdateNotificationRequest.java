package edu.kit.tm.cm.smartcampus.building.api.controller.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ServerUpdateNotificationRequest {
  private String parentIdentificationNumber;
  private String title;
  private String description;
  private String identificationNumber;
}
