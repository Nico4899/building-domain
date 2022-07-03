package edu.kit.tm.cm.smartcampus.building.api.controller;

import edu.kit.tm.cm.smartcampus.building.api.NotificationApi;
import edu.kit.tm.cm.smartcampus.building.api.payload.NotificationRequest;
import edu.kit.tm.cm.smartcampus.building.api.payload.NotificationResponse;

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
    public NotificationResponse getBuildingNotifications(String bin) {
        return null;
    }

    @Override
    public NotificationResponse addBuildingNotification(String bin, NotificationRequest notificationRequest) {
        return null;
    }

    @Override
    public NotificationResponse getRoomNotifications(String rin) {
        return null;
    }

    @Override
    public NotificationResponse addRoomNotification(String rin, NotificationRequest notificationRequest) {
        return null;
    }

    @Override
    public NotificationResponse getComponentsNotifications(String cin) {
        return null;
    }

    @Override
    public NotificationResponse addComponentsNotification(String cin, NotificationRequest notificationRequest) {
        return null;
    }
}
