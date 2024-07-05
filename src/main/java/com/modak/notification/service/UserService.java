package com.modak.notification.service;

import com.modak.notification.model.User;

import java.util.Optional;

public interface UserService {
    User save(User user);

    Optional<User> findById(Long userId);
}
