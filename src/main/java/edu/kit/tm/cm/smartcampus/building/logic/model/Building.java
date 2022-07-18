package edu.kit.tm.cm.smartcampus.building.logic.model;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.PrefixSequenceGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "building")
public class Building {

  @Column(name = "num_floors")
  private int numFloors;

  @Column(name = "campus_location")
  private CampusLocation campusLocation;

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
                  @Parameter(
                          name = PrefixSequenceGenerator.VALUE_PREFIX_PARAMETER,
                          value = "b-")
          })
  @Column(
          name = "identification_number",
          nullable = false,
          updatable = false,
          columnDefinition = "TEXT")
  private String identificationNumber;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "id", cascade = CascadeType.PERSIST)
  @JoinColumn(name = "geographical_location")
  private GeographicalLocation geographicalLocation;
}
