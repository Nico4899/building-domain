package edu.kit.tm.cm.smartcampus.building.logic.model;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.generator.PrefixSequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "building")
public class Building {

  @Column(name = "building_name")
  private String buildingName;

  @Column(name = "building_number")
  private String buildingNumber;

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

  @OneToOne(
      fetch = FetchType.LAZY,
      mappedBy = "id",
      cascade = CascadeType.PERSIST) // TODO testen ob des so passt
  @Column(name = "building_floors")
  private Floors buildingFloors;

  @Column(name = "campus_location")
  private CampusLocation campusLocation;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "id", cascade = CascadeType.PERSIST)
  @JoinColumn(name = "geographical_location")
  private GeographicalLocation geographicalLocation;
}
