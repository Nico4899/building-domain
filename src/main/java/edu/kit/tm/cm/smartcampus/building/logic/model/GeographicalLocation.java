package edu.kit.tm.cm.smartcampus.building.logic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static edu.kit.tm.cm.smartcampus.building.logic.model.GeographicalLocation.GEOGRAPHICAL_LOCATION_TABLE_NAME;

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

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = ID_COLUMN)
  private Long id;

  private double latitude;
  private double longitude;
}
