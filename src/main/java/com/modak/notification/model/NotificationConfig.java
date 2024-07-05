package com.modak.notification.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class NotificationConfig {
    @Id
    @GeneratedValue
    private Long id;

    /** List of users with this configuration */
    @OneToMany
    private List<User> users;

    /** Is default configuration or it's by userId */
    private boolean isDefault;

    /** Type of the notification */
    @Column(nullable = false)
    private String type;

    private Duration duration;

    /** Limit of notifications for the duration */
    private int limitAllowed;

    public NotificationConfig(List<User> users, boolean isDefault, String type, Duration duration, int limitAllowed) {
        this.users = users;
        this.isDefault = isDefault;
        this.type = type;
        this.duration = duration;
        this.limitAllowed = limitAllowed;
    }
}
