package com.example.security.service;

import com.example.security.config.FirstSetting;
import com.example.security.model.Role;
import com.example.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    /*@Override
    @Transactional
    public Set<Role> addAdminRoles() {
        Set<Role> adminRoles = new HashSet<>();
        roleRepository.save(firstSetting.roleAdmin);
        *//*adminRoles.add(firstSetting.roleAdmin);
        adminRoles.add(firstSetting.roleUser);*//*
        return adminRoles;
    }*/

    /*@Override
    @Transactional
    public Set<Role> addUserRole() {
        Set<Role> userRoles = new HashSet<>();
        roleRepository.save(firstSetting.roleUser);
        userRoles.add(firstSetting.roleUser);
        return userRoles;
    }*/

}
