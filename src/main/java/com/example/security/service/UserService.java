package com.example.security.service;

import com.example.security.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {



    List<User> getUsers();

    boolean saveUser(User user/*,Long roleId*/);

    void update(User updatedUser/*, Long roleId*/);

    public User findById(Long id);

    public User findByUsername(String username);
    void deleteUser(Long id);
}
