package edu.kit.tm.cm.smartcampus.building.logic.model;

import static edu.kit.tm.cm.smartcampus.building.logic.model.Room.ROOM_TABLE_NAME;

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
 * This class represents a domain entity room.
 *
 * @author Jonathan Kramer, Johannes von Geisau
 */
@Setter
@Getter
@NoArgsConstructor
@Entity(name = ROOM_TABLE_NAME)
public class Room {

  /**
   * The constant ROOM_TABLE_NAME.
   */
  // table name (must be public, else annotation can't find it)
  public static final String ROOM_TABLE_NAME = "room";

  // constants this class uses
  private static final String ROOM_SEQUENCE_NAME = "room_sequence";
  private static final String GENERATOR_PATH =
      "edu.kit.tm.cm.smartcampus.building.infrastructure"
          + ".database.generator.PrefixSequenceGenerator";
  private static final String ROOM_IDENTIFICATION_NUMBER_PREFIX = "r-";
  private static final String IDENTIFICATION_NUMBER_COLUMN = "identification_number";
  private static final String PARENT_BUILDING_IDENTIFICATION_NUMBER_COLUMN =
      "parent_building_identification_number";
  private static final String GEOGRAPHICAL_LOCATION_COLUMN = "geographical_location";
  private static final String GEOGRAPHICAL_LOCATION_ID_COLUMN = "geographical_location_id";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ROOM_SEQUENCE_NAME)
  @SequenceGenerator(name = ROOM_SEQUENCE_NAME, allocationSize = 1)
  @GenericGenerator(
      name = ROOM_SEQUENCE_NAME,
      strategy = GENERATOR_PATH,
      parameters = {
          @Parameter(
              name = PrefixSequenceGenerator.VALUE_PREFIX_PARAMETER,
              value = ROOM_IDENTIFICATION_NUMBER_PREFIX)
      })
  @Column(name = IDENTIFICATION_NUMBER_COLUMN)
  private String identificationNumber;

  @Column(name = PARENT_BUILDING_IDENTIFICATION_NUMBER_COLUMN)
  private String parentIdentificationNumber;

  private String name;
  private String number;
  private int floor;
  private Type type;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(
      name = GEOGRAPHICAL_LOCATION_ID_COLUMN,
      referencedColumnName = GeographicalLocation.ID_COLUMN)
  private GeographicalLocation geographicalLocation;

  /**
   * This enum represents the possible room types.
   */
  public enum Type {
    /**
     * Cafeteria.
     */
    CAFETERIA,
    /**
     * Restroom.
     */
    RESTROOM,
    /**
     * Restroom for Handicapped people.
     */
    RESTROOM_HANDICAPPED,
    /**
     * Office.
     */
    OFFICE,
    /**
     * Laboratory.
     */
    LABORATORY,
    /**
     * Seminar room.
     */
    SEMINAR_ROOM,
    /**
     * Lecture Room.
     */
    LECTURE_ROOM,
    /**
     * Sports.
     */
    SPORTS
  }
}
