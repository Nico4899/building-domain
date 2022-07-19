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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "component")
public class Component {

  @Column(name = "component_description")
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
  private String cin;

  private String parentIn;

  @Column(name = "component_type")
  private ComponentType componentType;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "id", cascade = CascadeType.PERSIST)
  @JoinColumn(name = "geographical_location")
  private GeographicalLocation geographicalLocation;

}
