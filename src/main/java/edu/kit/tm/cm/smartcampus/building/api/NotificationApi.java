package edu.kit.tm.cm.smartcampus.building.api;

import edu.kit.tm.cm.smartcampus.building.api.payload.NotificationRequest;
import edu.kit.tm.cm.smartcampus.building.api.payload.NotificationResponse;

import org.springframework.web.bind.annotation.*;

public interface NotificationApi {

    @GetMapping("/notifications/{nin}")
    NotificationResponse getNotification(@PathVariable String nin);

    @PutMapping("/notifications/{nin}")
    NotificationResponse editNotification(@PathVariable String nin,
                                          @RequestBody NotificationRequest notificationRequest);

    @DeleteMapping("/notifications/{nin}")
    void deleteNotification(@PathVariable String nin);

    //Buildings
    @GetMapping("/buildings/{bin}/notifications")
    NotificationResponse getBuildingNotifications(@PathVariable String bin);
    @PostMapping("buildings/{bin}/notifications")
    NotificationResponse addBuildingNotification(@PathVariable String bin,
                                                 @RequestBody NotificationRequest notificationRequest);

    //Rooms
    @GetMapping("/rooms/{rin}/notifications")
    NotificationResponse getRoomNotifications(@PathVariable String rin);

    @PostMapping("rooms/{rin}/notifications")
    NotificationResponse addRoomNotification(@PathVariable String rin,
                                             @RequestBody NotificationRequest notificationRequest);

    //Components
    @GetMapping("/components/{cin}/notifications")
    NotificationResponse getComponentsNotifications(@PathVariable String cin);

    @PostMapping("components/{cin}/notifications")
    NotificationResponse addComponentsNotification(@PathVariable String cin,
                                             @RequestBody NotificationRequest notificationRequest);

}
