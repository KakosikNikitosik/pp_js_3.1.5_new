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

    @RequestMapping("/admin/users")
    public String printListOfUsers(Model model, Principal principal) {
        model.addAttribute("user", userService.findByEmail(principal.getName()));
        model.addAttribute("users", userService.getUsers());
        return "/admin/users";
    }
    @GetMapping(value = "/admin/users/new")
    public String newUserForCreating(Model model) {
        model.addAttribute("newUser", new User());
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

    @PatchMapping("/admin/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
            userService.update(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
