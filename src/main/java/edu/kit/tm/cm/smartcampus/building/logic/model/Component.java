package edu.kit.tm.cm.smartcampus.building.logic.model;

import static edu.kit.tm.cm.smartcampus.building.logic.model.Component.COMPONENT_TABLE_NAME;

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
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * This class represents a domain entity component.
 */
@Setter
@Getter
@NoArgsConstructor
@Entity(name = COMPONENT_TABLE_NAME)
public class Component {

  /**
   * The constant COMPONENT_TABLE_NAME.
   */
  // table name (must be public, else annotation can't find it)
  public static final String COMPONENT_TABLE_NAME = "component";

  // constants this class uses
  private static final String COMPONENT_SEQUENCE_NAME = "component_sequence";
  private static final String GENERATOR_PATH = "edu.kit.tm.cm.smartcampus.building.infrastructure"
      + ".database.generator.PrefixSequenceGenerator";
  private static final String COMPONENT_IDENTIFICATION_NUMBER_PREFIX = "c-";
  private static final String IDENTIFICATION_NUMBER_COLUMN = "identification_number";
  private static final String PARENT_IDENTIFICATION_NUMBER_COLUMN = "parent_identification_number";
  private static final String GEOGRAPHICAL_LOCATION_COLUMN = "geographical_location";
  private static final String GEOGRAPHICAL_LOCATION_ID_COLUMN = "geographical_location_id";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = COMPONENT_SEQUENCE_NAME)
  @SequenceGenerator(name = COMPONENT_SEQUENCE_NAME, allocationSize = 1)
  @GenericGenerator(name = COMPONENT_SEQUENCE_NAME, strategy = GENERATOR_PATH, parameters = {
      @Parameter(name = PrefixSequenceGenerator.VALUE_PREFIX_PARAMETER,
          value = COMPONENT_IDENTIFICATION_NUMBER_PREFIX)})
  @Column(name = IDENTIFICATION_NUMBER_COLUMN)
  private String identificationNumber;

  @Column(name = PARENT_IDENTIFICATION_NUMBER_COLUMN)
  private String parentIdentificationNumber;

  private Type type;
  private String description;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = GEOGRAPHICAL_LOCATION_ID_COLUMN, referencedColumnName =
      GeographicalLocation.ID_COLUMN)
  private GeographicalLocation geographicalLocation;

  /**
   * This enum represents all component types.
   */
  public enum Type {
    /**
     * Elevator.
     */
    ELEVATOR,
    /**
     * Stairs.
     */
    STAIRS
  }
}
