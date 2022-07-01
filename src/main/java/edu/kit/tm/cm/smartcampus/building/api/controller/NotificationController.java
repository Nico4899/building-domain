package main.java.edu.kit.tm.cm.smartcampus.building.api.controller;

import main.java.edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class NotificationController {

    @PostMapping("/buildings/{bin}/notifications")
    Building createNotification(@RequestBody String )

}
