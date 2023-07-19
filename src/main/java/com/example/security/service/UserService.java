package com.example.security.service;

import com.example.security.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    void saveUser(User user);

    void update(User updatedUser);

    User findById(Long id);

    User findByEmail(String email);
    void deleteUser(Long id);
}
