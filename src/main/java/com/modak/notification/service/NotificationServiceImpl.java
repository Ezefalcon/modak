package com.modak.notification.service;

import com.modak.notification.model.Notification;
import com.modak.notification.model.NotificationConfig;
import com.modak.notification.model.User;
import com.modak.notification.repository.NotificationRepository;
import com.modak.notification.repository.UserRepository;
import com.modak.notification.service.exception.NotificationLimitReachedException;
import com.modak.notification.service.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;

    private NotificationConfigService notificationConfigService;

    private UserService userService;

    /**
     * This will check if the user has a notification configuration
     * If the user doesn't have a configuration we will try to find
     * the default config of the user, if there's no config
     * the message will be sent or else will throw a
     * @param type of notification
     * @param userId to be sent to
     * @param message string of notification
     */
    @Override
    public void send(String type, Long userId, String message) throws NotificationLimitReachedException {
        Optional<User> user = userService.findById(userId);
        if (user.isEmpty()) throw new UserNotFoundException();

        checkIfUserIsAllowedToReceiveNewNotification(type, userId);

        notificationRepository.save(new Notification(type, message, LocalDateTime.now(), user.get()));
        log.info("Notification sent to userId " + userId);
    }

    private void checkIfUserIsAllowedToReceiveNewNotification(String type, long userId) {
        Optional<NotificationConfig> byTypeAndUserId = notificationConfigService.findByTypeAndUserId(type, userId);
        if (byTypeAndUserId.isPresent()) {
            NotificationConfig notificationConfig = byTypeAndUserId.get();
            LocalDateTime minus = LocalDateTime.now().minus(notificationConfig.getDuration());
            long notificationCount = notificationRepository.countByDateGreaterThanEqual(minus);
            if (notificationCount + 1 > notificationConfig.getLimitAllowed()) {
                throw new NotificationLimitReachedException(notificationCount, notificationConfig.getLimitAllowed());
            }
        }
    }

    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }
}
