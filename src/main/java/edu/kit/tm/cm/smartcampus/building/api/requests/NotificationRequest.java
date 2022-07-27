package edu.kit.tm.cm.smartcampus.building.api.requests;


import lombok.*;

/**
 * This class represents a request for a Notification.
 */
@Data
@Setter
@Getter
@AllArgsConstructor
public class NotificationRequest {

  private String parentIdentificationNumber;

  private String title;

  private String description;

}
