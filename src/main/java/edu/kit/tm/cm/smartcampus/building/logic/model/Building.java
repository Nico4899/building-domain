package edu.kit.tm.cm.smartcampus.building.logic.model;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.generator.PrefixSequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

import static edu.kit.tm.cm.smartcampus.building.logic.model.Building.BUILDING_TABLE_NAME;

/**
 * This class represents a domain entity building.
 */
@Setter
@Getter
@NoArgsConstructor
@Entity(name = BUILDING_TABLE_NAME)
public class Building {

  /**
   * The constant BUILDING_TABLE_NAME.
   */
  // table name (must be public, else annotation can't find it)
  public static final String BUILDING_TABLE_NAME = "building";

  // constants this class uses
  private static final String BUILDING_SEQUENCE_NAME = "building_sequence";
  private static final String GENERATOR_PATH =
          "edu.kit.tm.cm.smartcampus.building.infrastructure.database.generator.PrefixSequenceGenerator";
  private static final String BUILDING_IDENTIFICATION_NUMBER_PREFIX = "b-";
  private static final String IDENTIFICATION_NUMBER_COLUMN = "identification_number";
  private static final String HIGHEST_FLOOR_COLUMN = "highest_floor";
  private static final String LOWEST_FLOOR_COLUMN = "lowest_floor";
  private static final String CAMPUS_LOCATION_COLUMN = "campus_location";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = BUILDING_SEQUENCE_NAME)
  @SequenceGenerator(name = BUILDING_SEQUENCE_NAME, allocationSize = 1)
  @GenericGenerator(
          name = BUILDING_SEQUENCE_NAME,
          strategy = GENERATOR_PATH,
          parameters = {
                  @Parameter(name = PrefixSequenceGenerator.VALUE_PREFIX_PARAMETER,
                          value = BUILDING_IDENTIFICATION_NUMBER_PREFIX)
          })
  @Column(name = IDENTIFICATION_NUMBER_COLUMN)
  private String identificationNumber;

  @Column(name = HIGHEST_FLOOR_COLUMN)
  private int highestFloor;

  @Column(name = LOWEST_FLOOR_COLUMN)
  private int lowestFloor;

  @Column(name = CAMPUS_LOCATION_COLUMN)
  private CampusLocation campusLocation;

  private String name;
  private String number;
  private double latitude;
  private double longitude;

  /**
   * This enum describes a building's campus location.
   */
  public enum CampusLocation {
    /**
     * North campus.
     */
    NORTH_CAMPUS,
    /**
     * East campus.
     */
    EAST_CAMPUS,
    /**
     * South campus.
     */
    SOUTH_CAMPUS,
    /**
     * West campus.
     */
    WEST_CAMPUS
  }
}
