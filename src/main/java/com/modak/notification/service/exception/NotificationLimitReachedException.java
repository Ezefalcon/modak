package com.modak.notification.service.exception;

public class NotificationLimitReachedException extends RuntimeException {
    public NotificationLimitReachedException(long actual, int limit) {
        super(String.format("Notification Limit Reached. Actual: %d, limit: %d", actual, limit));
    }
}
