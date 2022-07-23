package edu.kit.tm.cm.smartcampus.building.logic.model;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.generator.PrefixSequenceGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "building")
public class Building {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "building_sequence")
  @SequenceGenerator(name = "building_sequence", allocationSize = 1)
  @GenericGenerator(
      name = "building_sequence",
      strategy =
          "edu/kit/tm/cm/smartcampus/building/infrastructure/database/PrefixSequenceGenerator.java",
      parameters = {
        @Parameter(name = PrefixSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "b-")
      })
  @Column(name = "building_identification_number")
  private String identificationNumber;

  @Column(name = "highest_floor")
  private int highestFloor;

  @Column(name = "lowest_floor")
  private int lowestFloor;

  @Column(name = "campus_location")
  private CampusLocation campusLocation;

  private String name;
  private String number;
  private double latitude;
  private double longitude;

  /** This enum describes campus location. */
  public enum CampusLocation {
    NORTH_CAMPUS,
    EAST_CAMPUS,
    SOUTH_CAMPUS,
    WEST_CAMPUS
  }
}
