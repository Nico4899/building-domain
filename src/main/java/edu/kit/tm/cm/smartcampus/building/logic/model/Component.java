package edu.kit.tm.cm.smartcampus.building.logic.model;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.PrefixSequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "component")
public class Component {

  @Column(
      name = "component_description",
      nullable = false,
      updatable = false,
      columnDefinition = "TEXT")
  private String componentDescription;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "component_sequence")
  @SequenceGenerator(name = "component_sequence", allocationSize = 1)
  @GenericGenerator(
      name = "component_sequence",
      strategy =
          "edu/kit/tm/cm/smartcampus/building/infrastructure/database/PrefixSequenceGenerator.java",
      parameters = {
        @Parameter(name = PrefixSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "c-")
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

  @Column(
      name = "parent_identification_number",
      nullable = false,
      updatable = false,
      columnDefinition = "TEXT")
  private String parentIdentificationNumber;

  @Column(name = "component_type", nullable = false, updatable = false, columnDefinition = "TEXT")
  private ComponentType componentType;
}
