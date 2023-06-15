package com.example.store.controller;

import com.example.store.model.User;
import com.example.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // Replace with the correct URL of your React application
public class UserController {

    private final UserService userService;
    private static boolean isUserLoggedIn = false;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("username") String username,
                                        @RequestParam("password") String password) {
        if (isUserLoggedIn) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Another user is already logged in");
        }

        if (userService.login(username, password)) {
            isUserLoggedIn = true;
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        if (!isUserLoggedIn) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No user is currently logged in");
        }

        userService.logout();
        isUserLoggedIn = false;
        return ResponseEntity.ok("Logged out successfully");
    }
    
    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile() {
        User loggedInUser = userService.getLoggedInUser();
        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
