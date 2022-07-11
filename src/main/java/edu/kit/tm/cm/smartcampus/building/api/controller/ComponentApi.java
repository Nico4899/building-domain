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

  @PostMapping("/components")
  Component createComponent(@RequestBody Component component);

  @PutMapping("/components")
  Component updateComponent(@RequestBody Component component);

  @GetMapping("/buildings/{bin}/components")
  Collection<Component> listBuildingComponents(@PathVariable String bin);

  @GetMapping("/rooms/{rin}/components")
  Collection<Component> listRoomComponents(@PathVariable String rin);

  @GetMapping("/components/{cin}")
  Component getComponent(@PathVariable String cin);

  @DeleteMapping("/components/{cin}")
  void removeComponent(@PathVariable String cin);
}
