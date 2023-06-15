package com.example.store.service;

import com.example.store.model.User;
import com.example.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private User loggedInUser;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
    	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
    	String encryptedPassword = bcrypt.encode(user.getPassword());
    	user.setPassword(encryptedPassword);
        userRepository.save(user);
    }

    public boolean login(String username, String password) {
    	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        User user = userRepository.findByUsername(username);
        if (user != null && bcrypt.matches(password, user.getPassword())) {
            setLoggedInUser(user);
            return true;
        }
        return false;
    }

    public void logout() {
        setLoggedInUser(null);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}