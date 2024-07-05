package com.modak.notification.config;

import com.modak.notification.model.Notification;
import com.modak.notification.model.User;
import com.modak.notification.repository.NotificationRepository;
import com.modak.notification.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

/**
 * This class is to see how the data is saved on
 * the database
 */
@Configuration
@AllArgsConstructor
@ConditionalOnProperty(prefix = "runner", name = "enabled", havingValue = "true")
public class RunnerConfiguration implements ApplicationRunner {

    private NotificationRepository notificationRepository;

    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user1 = userRepository.save(new User(1L, "user1"));
        User user2 = userRepository.save(new User(2L, "user2"));
        User user3 = userRepository.save(new User(3L, "user3"));

        notificationRepository.save(new Notification("news", "asd", LocalDateTime.now(), user1));
        notificationRepository.save(new Notification("news", "asd",
                LocalDateTime.now().plusMinutes(1), user1));

    }
}
