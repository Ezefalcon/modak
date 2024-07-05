package com.modak.notification.service;

import com.modak.notification.model.NotificationConfig;

import java.util.Optional;

public interface NotificationConfigService {
    Optional<NotificationConfig> findByTypeAndUserId(String type, Long userId);
    NotificationConfig save(NotificationConfig notificationConfig);
}
