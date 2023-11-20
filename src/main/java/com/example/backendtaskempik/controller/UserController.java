package com.example.backendtaskempik.controller;

import com.example.backendtaskempik.model.User;
import com.example.backendtaskempik.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{login}")
    public ResponseEntity<User> getUserByLogin(@PathVariable String login) {
        User user = userService.getUserByLogin(login);
        userService.incrementRequestCount(login); // Increment request count and save it to the DB
        return ResponseEntity.ok(user.withCalculations());
    }
}
