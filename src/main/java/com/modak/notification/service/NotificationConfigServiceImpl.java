package com.modak.notification.service;

import com.modak.notification.model.NotificationConfig;
import com.modak.notification.repository.NotificationConfigRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class NotificationConfigServiceImpl implements NotificationConfigService {

    private NotificationConfigRepository notificationConfigRepository;

    @Override
    public Optional<NotificationConfig> findByTypeAndUserId(String type, Long userId) {
        return notificationConfigRepository.findByTypeAndUserIdOrGetDefault(type, userId);
    }

    @Override
    public NotificationConfig save(NotificationConfig notificationConfig) {
        return notificationConfigRepository.save(notificationConfig);
    }
}
