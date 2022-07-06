package edu.kit.tm.cm.smartcampus.building.api;

import edu.kit.tm.cm.smartcampus.building.api.payload.NotificationRequest;
import edu.kit.tm.cm.smartcampus.building.api.payload.NotificationResponse;

import edu.kit.tm.cm.smartcampus.building.api.payload.NotificationsResponse;
import org.springframework.web.bind.annotation.*;
@RequestMapping
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
    NotificationsResponse getBuildingNotifications(@PathVariable String bin);

    @PostMapping("buildings/{bin}/notifications")
    NotificationResponse addBuildingNotification(@PathVariable String bin,
                                                 @RequestBody NotificationRequest notificationRequest);

    //Rooms
    @GetMapping("/rooms/{rin}/notifications")
    NotificationsResponse getRoomNotifications(@PathVariable String rin);

    @PostMapping("rooms/{rin}/notifications")
    NotificationResponse addRoomNotification(@PathVariable String rin,
                                             @RequestBody NotificationRequest notificationRequest);

    //Components
    @GetMapping("/components/{cin}/notifications")
    NotificationsResponse getComponentNotifications(@PathVariable String cin);

    @PostMapping("components/{cin}/notifications")
    NotificationResponse addComponentNotification(@PathVariable String cin,
                                             @RequestBody NotificationRequest notificationRequest);

}
