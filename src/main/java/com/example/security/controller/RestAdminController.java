package com.example.security.controller;


import com.example.security.model.User;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api")
public class RestAdminController {

    @Autowired
    public RestAdminController(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;

    @GetMapping("/admin")
    public ResponseEntity<List<User>> printListOfUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PutMapping("/admin")
    public ResponseEntity<User> create(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<User> show(/*@RequestParam (required = false)*/@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PatchMapping("/admin")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
            userService.update(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<HttpStatus> deleteUser (@PathVariable("id") Long id) {
            userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
