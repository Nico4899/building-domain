package edu.kit.tm.cm.smartcampus.building.api.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a request for a Notification
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {

  private String parentIdentificationNumber;

  private String title;

  private String description;

}
