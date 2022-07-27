package edu.kit.tm.cm.smartcampus.building.logic.model;

import static edu.kit.tm.cm.smartcampus.building.logic.model.Notification.NOTIFICATION_TABLE_NAME;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.generator.PrefixSequenceGenerator;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * This class represents a domain entity notification.
 */
@Setter
@Getter
@NoArgsConstructor
@Entity(name = NOTIFICATION_TABLE_NAME)
public class Notification {

  /**
   * The constant NOTIFICATION_TABLE_NAME.
   */
  // table name (must be public, else annotation can't find it)
  public static final String NOTIFICATION_TABLE_NAME = "notification";

  // constants this class uses
  private static final String NOTIFICATION_SEQUENCE_NAME = "notification_sequence";
  private static final String GENERATOR_PATH = "edu.kit.tm.cm.smartcampus.building.infrastructure"
      + ".database.generator.PrefixSequenceGenerator";
  private static final String NOTIFICATION_IDENTIFICATION_NUMBER_PREFIX = "n-";
  private static final String IDENTIFICATION_NUMBER_COLUMN = "identification_number";
  private static final String PARENT_IDENTIFICATION_NUMBER_COLUMN = "parent_identification_number";
  private static final String CREATION_TIME_COLUMN = "creation_time";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = NOTIFICATION_SEQUENCE_NAME)
  @SequenceGenerator(name = NOTIFICATION_SEQUENCE_NAME, allocationSize = 1)
  @GenericGenerator(name = NOTIFICATION_SEQUENCE_NAME, strategy = GENERATOR_PATH, parameters = {
      @Parameter(name = PrefixSequenceGenerator.VALUE_PREFIX_PARAMETER,
          value = NOTIFICATION_IDENTIFICATION_NUMBER_PREFIX)})
  @Column(name = IDENTIFICATION_NUMBER_COLUMN)
  private String identificationNumber;

  @Column(name = PARENT_IDENTIFICATION_NUMBER_COLUMN)
  private String parentIdentificationNumber;

  @Column(name = CREATION_TIME_COLUMN)
  private Timestamp creationTime;

  private String title;
  private String description;
}
