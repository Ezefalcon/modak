package com.modak.notification.service;

import com.modak.notification.model.Notification;
import com.modak.notification.model.NotificationConfig;
import com.modak.notification.model.User;
import com.modak.notification.repository.UserRepository;
import com.modak.notification.service.exception.NotificationLimitReachedException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class NotificationServiceTest {

    @Autowired
    NotificationService notificationService;

    @Autowired
    NotificationConfigService notificationConfigService;

    @Autowired
    UserRepository userRepository;

    private User user;

    @BeforeAll
    void beforeAll() {
        this.user = this.userRepository.save(new User(1L, "user1"));
    }

    @Test
    void notification_send_success() {
        notificationService.send("news", this.user.getId(), "test");
    }

    @Test
    void notification_sendWithNotificationConfig_success() {
        NotificationConfig notificationConfig = new NotificationConfig(List.of(this.user), false, "news",
                Duration.ofMinutes(1), 1);
        notificationConfigService.save(notificationConfig);
        notificationService.send("news", this.user.getId(), "test");
    }

    @Test
    void notification_sendWithNotificationConfig_limitReached() {
        NotificationConfig notificationConfig = new NotificationConfig(List.of(this.user), false, "news",
                Duration.ofMinutes(1), 1);
        notificationConfigService.save(notificationConfig);

        notificationService.send("news", this.user.getId(), "test2");
        Assertions.assertThrows(NotificationLimitReachedException.class,
                () -> notificationService.send("news", this.user.getId(), "test2"));
    }

//    @Test
    void notification_sendWithNotificationConfig_limitSleep() throws InterruptedException {
        NotificationConfig notificationConfig = new NotificationConfig(List.of(this.user), false, "news",
                Duration.ofSeconds(5), 1);
        notificationConfigService.save(notificationConfig);

        notificationService.send("news", this.user.getId(), "test2");
        Thread.sleep(5000);
        Assertions.assertDoesNotThrow(() -> notificationService.send("news", this.user.getId(), "test2"));

    }

}
