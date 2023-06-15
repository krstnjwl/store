package com.example.store.controller;

import com.example.store.model.User;
import com.example.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return userService.addUser(user);
    }

    @PutMapping("/{userId}")
    public User editUser(@PathVariable int userId, @RequestBody User user) {
        user.setUserId(userId);
        return userService.editUser(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }

    @PostMapping("/login")
    public String login(@RequestParam(name = "usernameOrEmail") String usernameOrEmail,
                        @RequestParam(name = "password") String password) {
        return userService.login(usernameOrEmail, password);
    }

    @PostMapping("/logout")
    public String logout(@RequestParam(name = "userId") int userId) {
        return userService.logout(userId);
    }
}
