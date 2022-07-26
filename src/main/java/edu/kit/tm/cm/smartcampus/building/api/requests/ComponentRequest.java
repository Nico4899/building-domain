package edu.kit.tm.cm.smartcampus.building.api.requests;

import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.GeographicalLocation;
import lombok.*;

/**
 * This class represents a request for a Component.
 */
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ComponentRequest {

  private String parentIdentificationNumber;

  private Component.Type type;

  private String description;

  private GeographicalLocation geographicalLocation;

}
