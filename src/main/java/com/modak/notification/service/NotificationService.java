package com.modak.notification.service;


import com.modak.notification.model.Notification;

public interface NotificationService {
    void send(String type, Long userId, String message);

    Notification save(Notification notification);
}
