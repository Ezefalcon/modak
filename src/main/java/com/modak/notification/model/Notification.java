package com.modak.notification.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue
    private long id;

    private String type;

    private String message;

    private LocalDateTime date;

    @ManyToOne
    private User user;

    public Notification(String type, String message, LocalDateTime date, User user) {
        this.type = type;
        this.message = message;
        this.date = date;
        this.user = user;
    }
}
