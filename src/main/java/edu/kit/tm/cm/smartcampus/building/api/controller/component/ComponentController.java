package edu.kit.tm.cm.smartcampus.building.api.controller.component;

import edu.kit.tm.cm.smartcampus.building.api.configuration.error.ServerExceptionInterceptor;
import edu.kit.tm.cm.smartcampus.building.api.controller.component.dto.ServerCreateComponentRequest;
import edu.kit.tm.cm.smartcampus.building.api.controller.component.dto.ServerUpdateComponentRequest;
import edu.kit.tm.cm.smartcampus.building.infrastructure.service.Service;
import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class represents the component controller for this domain service. It holds a Spring
 * {@link Bean} of {@link Service} managing all logical operations and running domain constraint
 * validators. It sends REST-Server responses in JSON format via the Spring internal
 * {@link RestController} annotation. In case of errors the {@link ServerExceptionInterceptor}
 * returns given information as REST error response.
 *
 * @author Jonathan Kramer, Johannes von Geisau
 */
@RestController
public class ComponentController implements ComponentOperations {

  private final Service service;

  /**
   * Instantiates a new component controller for this domain service, it implements all
   * {@link ComponentOperations}.
   *
   * @param service the service which controls all domain logic (constructor injected)
   */
  @Autowired
  public ComponentController(Service service) {
    this.service = service;
  }


  @Override
  public Component createComponent(ServerCreateComponentRequest serverCreateComponentRequest) {
    return service.createComponent(serverCreateComponentRequest);
  }

  @Override
  public Component getComponent(String cin) {
    return service.getComponent(cin);
  }

  @Override
  public Component updateComponent(ServerUpdateComponentRequest serverUpdateComponentRequest) {
    return service.updateComponent(serverUpdateComponentRequest);
  }

  @Override
  public void removeComponent(String cin) {
    service.removeComponent(cin);
  }

  @Override
  public Collection<Component> listComponents(String in) {
    return service.listComponents(in);
  }


}
