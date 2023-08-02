package com.example.security.service;

import com.example.security.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getUsers();

    void saveUser(User user);

    void update(User updatedUser);

    User findById(Long id);

    Optional<User> findByEmail(String email);

    void deleteUser(Long id);
}
