package com.modak.notification.service;

import com.modak.notification.model.NotificationConfig;
import com.modak.notification.model.User;
import com.modak.notification.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class NotificationConfigServiceTest {

    @Autowired
    NotificationConfigServiceImpl notificationConfigService;

    @Autowired
    UserRepository userRepository;

    @Test
    void save_notificationConfig_success() {
        User test = userRepository.save(new User(1L, "test"));
        NotificationConfig notificationConfig = new NotificationConfig(List.of(test), false, "news", Duration.ofDays(365), 1);
        NotificationConfig save = notificationConfigService.save(notificationConfig);
        Assertions.assertNotNull(save);
    }

    @Test
    void notificationConfig_findByTypeAndUserId_success() {
        User user = userRepository.save(new User(1L, "test"));
        NotificationConfig notificationConfig = new NotificationConfig(List.of(user), false, "news", Duration.ofDays(365), 1);
        notificationConfigService.save(notificationConfig);

        Optional<NotificationConfig> news = notificationConfigService.findByTypeAndUserId("news", user.getId());

        Assertions.assertTrue(news.isPresent());
        Assertions.assertEquals("news", news.get().getType());
    }
}
