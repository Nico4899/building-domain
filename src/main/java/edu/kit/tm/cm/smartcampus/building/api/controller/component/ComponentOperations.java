package edu.kit.tm.cm.smartcampus.building.api.controller.component;

import edu.kit.tm.cm.smartcampus.building.api.controller.component.dto.ServerCreateComponentRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.component.dto.ServerUpdateComponentRequest;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import java.util.Collection;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This interface provides all rest server operations connected "/components" requests.
 */
@RequestMapping
public interface ComponentOperations {

  /**
   * Create a new {@link Component} on "/components" url in this domain service.
   *
   * @param serverCreateComponentRequest the request for the component to be created in this
   *                                     service
   * @return the created component
   */
  @PostMapping("/components")
  Component createComponent(@RequestBody ServerCreateComponentRequest serverCreateComponentRequest);

  /**
   * Get a specific {@link Component} on "/components/{cin}" url from tis domain service.
   *
   * @param cin identification number of the requested component in this domain service
   * @return the requested component
   */
  @GetMapping("/components/{cin}")
  Component getComponent(@PathVariable String cin);

  /**
   * Update a specific {@link Component} on "/components" url in this domain service.
   *
   * @param serverUpdateComponentRequest the component to be updated in this service
   * @return the updated component with updated attributes
   */
  @PutMapping("/components")
  Component updateComponent(@RequestBody ServerUpdateComponentRequest serverUpdateComponentRequest);

  /**
   * Remove a {@link Component} on "/components/{cin}" from this domain service.
   *
   * @param cin identification number of the component to be removed
   */
  @DeleteMapping("/components/{cin}")
  void removeComponent(@PathVariable String cin);


  /**
   * List all components that belong to a certain building on "/buildings/{bin}/components" url.
   *
   * @param in identification of the parent
   * @return a collection of all {@link Component} this domain service manages that belong to the
   *     building
   */
  @GetMapping("{in}/components")
  Collection<Component> listComponents(@PathVariable String in);
}
