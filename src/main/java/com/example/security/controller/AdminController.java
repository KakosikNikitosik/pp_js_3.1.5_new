/*
package com.example.security.controller;


import com.example.security.model.User;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
public class AdminController {

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;

    @RequestMapping("/admin")
    public String printListOfUsers(Model model, Principal principal) {
        model.addAttribute("admin", userService.findByEmail(principal.getName()));
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("user", new User());
        return "admin";
    }

    */
/*@GetMapping("/{id}")
    public String create(@ModelAttribute("newUser") User user) {
        userService.saveUser(user);
        return "redirect:/admin/";
    }*//*

}
*/
