package com.example.backendtaskempik.controller;

import com.example.backendtaskempik.model.User;
import com.example.backendtaskempik.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    /**
     * Constructor to inject UserService dependency.
     *
     * @param userService The UserService instance.
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint to get user by login.
     *
     * @param login The user's login.
     * @return ResponseEntity containing the user with calculations.
     */
    @GetMapping("/{login}")
    public ResponseEntity<User> getUserByLogin(@PathVariable String login) {
        try {
            User user = userService.getUserByLogin(login);
            userService.incrementRequestCount(login); // Increment request count and save it to the DB
            return ResponseEntity.ok(user.withCalculations());

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
