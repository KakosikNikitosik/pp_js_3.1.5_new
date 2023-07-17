package com.example.security.config;


import com.example.security.model.User;
import com.example.security.service.RoleService;
import com.example.security.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstSetting implements CommandLineRunner {

    private final UserService userService;

    private final RoleService roleService;


    public FirstSetting(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    public void run(String... arg) {
        userService.saveUser(new User("admin", "pupa", 22, "admin@m.ru", "admin", roleService.addAdminRoles()));
        userService.saveUser(new User("user", "lupa", 28, "user@m.ru", "user", roleService.addUserRole()));
    }

}