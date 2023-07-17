package com.example.security.service;

import com.example.security.model.Role;
import com.example.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;
    Role roleAdmin = new Role(1L,"ROLE_ADMIN");
    Role roleUser = new Role(2L,"ROLE_USER");

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Set<Role> addAdminRoles() {
        Set<Role> adminRoles = new HashSet<>();
        roleRepository.save(roleAdmin);
        adminRoles.add(roleAdmin);
        adminRoles.add(roleUser);
        return adminRoles;
    }

    @Override
    public Set<Role> addUserRole() {
        Set<Role> userRoles = new HashSet<>();
        roleRepository.save(roleUser);
        userRoles.add(roleUser);
        return userRoles;
    }

}
