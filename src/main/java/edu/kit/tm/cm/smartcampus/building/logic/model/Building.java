package edu.kit.tm.cm.smartcampus.building.logic.model;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.PrefixSequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "Building")
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
