package edu.kit.tm.cm.smartcampus.building.logic.model;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.generator.PrefixSequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

//TODO javadocs
@Setter
@Getter
@NoArgsConstructor
@Entity(name = "component")
public class Component {

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
  @Column(name = "identification_number")
  private String identificationNumber;

  @Column(name = "parent_identification_number")
  private String parentIdentificationNumber;

  private Type type;
  private String description;
  private double latitude;
  private double longitude;

  /** This enum represents all component types. */
  public enum Type {
    ELEVATOR,
    STAIRS
  }
}
