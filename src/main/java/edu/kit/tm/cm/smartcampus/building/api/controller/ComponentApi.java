package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.logic.model.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@RequestMapping
public interface ComponentApi {

  @GetMapping("/buildings/{bin}/components")
  Collection<Component> listBuildingComponents(@PathVariable String bin);

  @PostMapping("/buildings/{bin}/components")
  Component createBuildingComponent(@PathVariable String bin, @RequestBody Component component);

  @GetMapping("/rooms/{rin}/components")
  Collection<Component> listRoomComponents(@PathVariable String rin);

  @PostMapping("/rooms/{rin}/components")
  Component createRoomComponent(@PathVariable String rin, @RequestBody Component component);

  @GetMapping("/components/{cin}")
  Component getComponent(@PathVariable String cin);

  @PutMapping("/components/{cin}")
  Component updateComponent(@PathVariable String cin, @RequestBody Component component);

  @DeleteMapping("/components/{cin}")
  void removeComponent(@PathVariable String cin);
}
