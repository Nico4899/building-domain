package edu.kit.tm.cm.smartcampus.building.logic.model;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.generator.PrefixSequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

import static edu.kit.tm.cm.smartcampus.building.logic.model.Room.ROOM_TABLE_NAME;

/**
 * This class represents a domain entity room.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
          "edu.kit.tm.cm.smartcampus.building.infrastructure.database.generator.PrefixSequenceGenerator";
  private static final String ROOM_IDENTIFICATION_NUMBER_PREFIX = "r-";
  private static final String IDENTIFICATION_NUMBER_COLUMN = "identification_number";
  private static final String PARENT_IDENTIFICATION_NUMBER_COLUMN = "parent_identification_number";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ROOM_SEQUENCE_NAME)
  @SequenceGenerator(name = ROOM_SEQUENCE_NAME, allocationSize = 1)
  @GenericGenerator(
          name = ROOM_SEQUENCE_NAME,
          strategy =
                  GENERATOR_PATH,
          parameters = {
                  @Parameter(name = PrefixSequenceGenerator.VALUE_PREFIX_PARAMETER, value = ROOM_IDENTIFICATION_NUMBER_PREFIX)
          })
  @Column(name = IDENTIFICATION_NUMBER_COLUMN)
  private String identificationNumber;

  @Column(name = PARENT_IDENTIFICATION_NUMBER_COLUMN)
  private String parentIdentificationNumber;

  private String name;
  private String number;
  private int floor;
  private Type type;
  private double latitude;
  private double longitude;

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
     * Library.
     */
    LIBRARY,
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
