package com.modak.notification.service;

import com.modak.notification.model.NotificationConfig;
import com.modak.notification.model.User;
import com.modak.notification.repository.NotificationConfigRepository;
import com.modak.notification.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class NotificationConfigRepositoryTest {
    @Autowired
    NotificationConfigRepository notificationConfigRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    NotificationService notificationService;

    @Test
    void repo_findByTypeAndUserIdOrGetDefault_success() {
        //Arrange
        User user = userRepository.save(new User(1L, "test"));
        NotificationConfig notificationConfig = new NotificationConfig(List.of(user), false,
                "news", Duration.ofDays(1), 1);
        notificationConfigRepository.save(notificationConfig);
        var news = notificationConfigRepository.findByTypeAndUserIdOrGetDefault("news", user.getId());

        Assertions.assertTrue(news.isPresent());
        Assertions.assertEquals(Duration.ofDays(1), news.get().getDuration());
    }

    @Test
    void repo_findByTypeAndUserIdOrGetDefault_shouldFindNotDefault() {
        User user = userRepository.save(new User("test"));
        NotificationConfig notificationConfig = new NotificationConfig(new ArrayList<>(), true,
                "news", Duration.ofDays(1), 1);
        NotificationConfig notificationConfig2 = new NotificationConfig(List.of(user), false,
                "news", Duration.ofMinutes(1), 2);
        notificationConfigRepository.save(notificationConfig2);
        notificationConfigRepository.save(notificationConfig);

        var news = notificationConfigRepository.findByTypeAndUserIdOrGetDefault("news", user.getId());

        Assertions.assertTrue(news.isPresent());
        Assertions.assertFalse(news.get().isDefault());
        Assertions.assertEquals(Duration.ofMinutes(1), news.get().getDuration());
    }

    @Test
    void repo_findByTypeAndUserIdOrGetDefault_shouldFindDefault() {
        User test = userRepository.save(new User(1L, "test"));
        NotificationConfig notificationConfig = new NotificationConfig(new ArrayList<>(), true,
                "news", Duration.ofDays(1), 1);
        notificationConfigRepository.save(notificationConfig);

        var news = notificationConfigRepository.findByTypeAndUserIdOrGetDefault("news", 1L);

        Assertions.assertTrue(news.isPresent());
        Assertions.assertTrue(news.get().isDefault());
        Assertions.assertEquals(Duration.ofDays(1), news.get().getDuration());
    }
}
