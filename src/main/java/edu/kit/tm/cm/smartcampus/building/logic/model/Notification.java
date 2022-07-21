package edu.kit.tm.cm.smartcampus.building.logic.model;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.generator.PrefixSequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "notification")
public class Notification {

  @Column(name = "notification_title")
  private String notificationTitle;

  @Column(name = "notification_description")
  private String notificationDescription;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_sequence")
  @SequenceGenerator(name = "notification_sequence", allocationSize = 1)
  @GenericGenerator(
      name = "notification_sequence",
      strategy =
          "edu/kit/tm/cm/smartcampus/building/infrastructure/database/PrefixSequenceGenerator.java",
      parameters = {
        @Parameter(name = PrefixSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "n-")
      })
  @Column(name = "identification_number")
  private String identificationNumber;

  @Column(name = "parent_identification_number")
  private String parentIdentificationNumber;

  @Column(name = "creation_time")
  private Timestamp creationTime;
}
