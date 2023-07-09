package com.example.security.controller;



import com.example.security.model.Role;
import com.example.security.model.User;
import com.example.security.repository.RoleRepository;
import com.example.security.service.RoleService;
import com.example.security.service.UserService;
import com.sun.xml.bind.v2.util.QNameMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.security.pkcs.SignerInfo;

import java.util.Collections;


@Controller
public class AdminController {

    private BCryptPasswordEncoder bCryptpasswordEncoder;

    private RoleService roleService;

    @Autowired
    public AdminController(@Lazy BCryptPasswordEncoder bCryptpasswordEncoder, RoleRepository roleRepository, UserService userService, RoleService roleService) {
        this.roleService = roleService;
        this.bCryptpasswordEncoder = bCryptpasswordEncoder;
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    /*@Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }*/

    private RoleRepository roleRepository;

   /* @Autowired
    public void setbCryptpasswordEncoder(BCryptPasswordEncoder bCryptpasswordEncoder) {
        this.bCryptpasswordEncoder = bCryptpasswordEncoder;
    }*/

    /*public AdminController(UserService userService) {
        this.userService = userService;
    }*/

    private UserService userService;
    /*@Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }*/

    @RequestMapping("/admin/users")
    public String printListOfUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "/admin/users";
    }
    @GetMapping(value = "/admin/users/new")
    public String newUserForCreating(Model model, String username, String password) {
        model.addAttribute("newUser", new User(username, password));
        return "/admin/new";
    }

    @PostMapping("/admin/save")
    public String create(@ModelAttribute("newUser") User user) {
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/show")
    public String show(@RequestParam (required = false) Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "/admin/show";
    }

    @GetMapping("/admin/edit/{id}")
    public String updateUserForm(@PathVariable("id") Long id,Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "/admin/edit";
    }
    @PostMapping("/admin/edit")
    public String updateUser(@ModelAttribute("user") User user) {
        User userFromDB = userService.findById(user.getId());
        if (user.getPassword().equals("")) {

            userFromDB.setRoles(user.getRoles());
            userService.update(userFromDB);
        } else {
            user.setRoles(user.getRoles());
            user.setPassword(bCryptpasswordEncoder.encode(user.getPassword()));
            userService.update(user/*, roleId*/);}
        return "redirect:/admin/users";
    }


    @PostMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }


}
