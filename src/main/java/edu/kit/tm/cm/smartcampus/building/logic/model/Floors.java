package edu.kit.tm.cm.smartcampus.building.logic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static edu.kit.tm.cm.smartcampus.building.logic.model.Floors.FLOORS_TABLE_NAME;

/**
 * This class represents floors of a building.
 * The ground-floor hast the value 0 by convention.
 */
@Setter
@Getter
@NoArgsConstructor
@Entity(name = FLOORS_TABLE_NAME)
public class Floors {

  /**
   * The constant ROOM_TABLE_NAME.
   */
  // table name (must be public, else annotation can't find it)
  public static final String FLOORS_TABLE_NAME = "floors";

  // constants this class uses
  private static final String HIGHEST_FLOOR_COLUMN = "highest_floor";
  private static final String LOWEST_FLOOR_COLUMN = "lowest_floor";

  // constants this and other classes use
  public static final String BUILDING_COLUMN = "building"; //TODO überprüfen

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = HIGHEST_FLOOR_COLUMN)
  private int highestFloor;

  @Column(name = LOWEST_FLOOR_COLUMN)
  private int lowestFloor;

  //TODO falls das beim building mit geoLocation klappt kann das ganze attribut raus und wir
  // bleiben einfach unidirektional was ja vollkommen ausreicht
  @Column(name = BUILDING_COLUMN)
  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = Building.FLOORS_COLUMN, nullable = false)
  private Building building;

}
