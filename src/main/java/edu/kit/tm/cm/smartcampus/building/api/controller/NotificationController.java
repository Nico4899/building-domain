package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.api.NotificationApi;
import edu.kit.tm.cm.smartcampus.building.api.payload.NotificationRequest;
import edu.kit.tm.cm.smartcampus.building.api.payload.NotificationResponse;
import edu.kit.tm.cm.smartcampus.building.api.payload.NotificationsResponse;

public class NotificationController implements NotificationApi {
    @Override
    public NotificationResponse getNotification(String nin) {
        return null;
    }

    @Override
    public NotificationResponse editNotification(String nin, NotificationRequest notificationRequest) {
        return null;
    }

    @Override
    public void deleteNotification(String nin) {

    }

    @Override
    public NotificationsResponse getBuildingNotifications(String bin) {
        return null;
    }

    @Override
    public NotificationResponse addBuildingNotification(String bin, NotificationRequest notificationRequest) {
        return null;
    }

    @Override
    public NotificationsResponse getRoomNotifications(String rin) {
        return null;
    }

    @Override
    public NotificationResponse addRoomNotification(String rin, NotificationRequest notificationRequest) {
        return null;
    }

    @Override
    public NotificationsResponse getComponentNotifications(String cin) {
        return null;
    }

    @Override
    public NotificationResponse addComponentNotification(String cin, NotificationRequest notificationRequest) {
        return null;
    }
}
