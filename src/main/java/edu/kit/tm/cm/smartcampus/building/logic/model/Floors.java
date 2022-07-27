package edu.kit.tm.cm.smartcampus.building.logic.model;

import static edu.kit.tm.cm.smartcampus.building.logic.model.Floors.FLOORS_TABLE_NAME;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents floors of a building. The ground-floor hast the value 0 by convention.
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
  public static final String ID_COLUMN = "id";

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = ID_COLUMN)
  private Long id;

  @Column(name = HIGHEST_FLOOR_COLUMN)
  private int highestFloor;

  @Column(name = LOWEST_FLOOR_COLUMN)
  private int lowestFloor;

}
