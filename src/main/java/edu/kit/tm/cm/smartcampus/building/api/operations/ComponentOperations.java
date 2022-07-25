package edu.kit.tm.cm.smartcampus.building.api.operations;

import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import edu.kit.tm.cm.smartcampus.building.logic.model.Notification;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * This interface provides all rest server operations connected "/components" requests.
 */
@RequestMapping("/components")
public interface ComponentOperations {

  /**
   * Create a new {@link Component} on "/components" url in this domain service.
   *
   * @param component the component to be created in this service
   * @return the created component
   */
  @PostMapping("")
  Component createComponent(@RequestBody Component component);

  /**
   * Get a specific {@link Component} on "/components/{cin}" url from tis domain service.
   *
   * @param cin identification number of the requested component in this domain service
   * @return the requested component
   */
  @GetMapping("/{cin}")
  Component getComponent(@PathVariable String cin);

  /**
   * Update a specific {@link Component} on "/components" url in this domain service.
   *
   * @param component the component to be updated in this service
   * @return the updated component with updated attributes
   */
  @PutMapping("")
  Component updateComponent(@RequestBody Component component);

  /**
   * Remove a {@link Component} on "/components/{cin}" from this domain service.
   *
   * @param cin identification number of the component to be removed
   */
  @DeleteMapping("/{cin}")
  void removeComponent(@PathVariable String cin);

  /**
   * List all notifications that belong to a certain component on "/components/{cin}/notifications" url.
   *
   * @param cin identification number of the component
   * @return a collection of all {@link Notification} this domain service manages that belong to the component
   */
  @GetMapping("/{cin}/notifications")
  Collection<Notification> listComponentNotifications(@PathVariable String cin);
}
