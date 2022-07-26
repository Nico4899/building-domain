package edu.kit.tm.cm.smartcampus.building.api.payload;

import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a request for a Component
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentRequest {

  private String parentIdentificationNumber;

  private Component.Type type;

  private String description;

  private GeographicalLocation geographicalLocation;

}
