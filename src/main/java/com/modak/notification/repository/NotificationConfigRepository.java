package com.modak.notification.repository;

import com.modak.notification.model.NotificationConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationConfigRepository extends JpaRepository<NotificationConfig, Long> {
    @Query(value = "SELECT n from NotificationConfig n LEFT JOIN n.users u WHERE (u.id = :userId " +
            "AND n.type = :type) OR (n.type = :type AND n.isDefault = true) order by n.isDefault limit 1")
    Optional<NotificationConfig> findByTypeAndUserIdOrGetDefault(String type, Long userId);
}
