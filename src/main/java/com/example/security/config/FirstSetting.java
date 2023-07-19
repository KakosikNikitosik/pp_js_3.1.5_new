package com.example.security.config;


import com.example.security.model.Role;
import com.example.security.model.User;
import com.example.security.repository.RoleRepository;
import com.example.security.service.RoleService;
import com.example.security.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class FirstSetting implements CommandLineRunner {

    private final UserService userService;

    private final RoleRepository roleRepository;

    /*private final RoleService roleService;*/



    public FirstSetting(UserService userService, /*RoleService roleService,*/ RoleRepository roleRepository) {
        this.userService = userService;
        /*this.roleService = roleService;*/
        this.roleRepository = roleRepository;
    }

    public void run(String... arg) {

        Role roleAdmin = new Role(1L,"ROLE_ADMIN");
        Role roleUser = new Role(2L,"ROLE_USER");
        Set<Role> rolesAd = new HashSet<>();
        Set<Role> rolesUs = new HashSet<>();
        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);
        rolesAd.add(roleAdmin);
        rolesAd.add(roleUser);
        rolesUs.add(roleUser);

        userService.saveUser(new User("admin", "pupa", 22, "admin@m.ru", "admin", rolesAd));
        userService.saveUser(new User("user", "lupa", 28, "user@m.ru", "user", rolesUs));
    }

}