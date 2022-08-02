package edu.kit.tm.cm.smartcampus.building.logic.model;

import static edu.kit.tm.cm.smartcampus.building.logic.model.GeographicalLocation.GEOGRAPHICAL_LOCATION_TABLE_NAME;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents a geographical location.
 */
@Setter
@Getter
@NoArgsConstructor
@Entity(name = GEOGRAPHICAL_LOCATION_TABLE_NAME)
public class GeographicalLocation {

  /**
   * The constant ROOM_TABLE_NAME.
   */
  // table name (must be public, else annotation can't find it)
  public static final String GEOGRAPHICAL_LOCATION_TABLE_NAME = "geographical_location";

  // constants this and other classes use
  public static final String ID_COLUMN = "id";

  // constants this class uses
  private static final String GEOGRAPHICAL_LOCATION_SEQUENCE_NAME =
      "geographical_location_sequence";

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = GEOGRAPHICAL_LOCATION_SEQUENCE_NAME)
  @Column(name = ID_COLUMN)
  private Long id;

  private double latitude;
  private double longitude;
}
