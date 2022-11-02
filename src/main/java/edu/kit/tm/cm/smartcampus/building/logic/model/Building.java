package edu.kit.tm.cm.smartcampus.building.logic.model;

import static edu.kit.tm.cm.smartcampus.building.logic.model.Building.BUILDING_TABLE_NAME;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.generator.PrefixSequenceGenerator;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * This class represents a domain entity building.
 *
 * @author Jonathan Kramer, Johannes von Geisau
 */
@Setter
@Getter
@ToString
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
  private static final String GENERATOR_PATH = "edu.kit.tm.cm.smartcampus.building.infrastructure"
      + ".database.generator.PrefixSequenceGenerator";
  private static final String BUILDING_IDENTIFICATION_NUMBER_PREFIX = "b-";
  private static final String IDENTIFICATION_NUMBER_COLUMN = "identification_number";
  private static final String CAMPUS_LOCATION_COLUMN = "campus_location";
  private static final String GEOGRAPHICAL_LOCATION_COLUMN = "geographical_location";
  private static final String GEOGRAPHICAL_LOCATION_ID_COLUMN = "geographical_location_id";
  private static final String FLOORS_ID_COLUMN = "floors_id";

  // constants this and other classes use
  public static final String FLOORS_COLUMN = "floors";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = BUILDING_SEQUENCE_NAME)
  @SequenceGenerator(name = BUILDING_SEQUENCE_NAME, allocationSize = 1)
  @GenericGenerator(name = BUILDING_SEQUENCE_NAME, strategy = GENERATOR_PATH, parameters = {
      @Parameter(name = PrefixSequenceGenerator.VALUE_PREFIX_PARAMETER,
          value = BUILDING_IDENTIFICATION_NUMBER_PREFIX)})
  @Column(name = IDENTIFICATION_NUMBER_COLUMN)
  private String identificationNumber;

  @Column(name = CAMPUS_LOCATION_COLUMN)
  private CampusLocation campusLocation;

  private String name;
  private String number;
  private String address;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = GEOGRAPHICAL_LOCATION_ID_COLUMN, referencedColumnName =
      GeographicalLocation.ID_COLUMN)
  private GeographicalLocation geographicalLocation;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = FLOORS_ID_COLUMN, referencedColumnName =
      Floors.ID_COLUMN)
  private Floors floors;

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
