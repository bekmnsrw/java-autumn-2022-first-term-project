package ru.kpfu.itis.dao;

import ru.kpfu.itis.models.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findByEmail(String email);
}
