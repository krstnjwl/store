package com.example.store.service;
import com.example.store.model.User;
import com.example.store.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;



@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User addUser(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }

    public User editUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    public String login(String usernameOrEmail, String password) {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            String authToken = generateAuthToken(user);
            return authToken;
        } else {
            return "Invalid username/email or password";
        }
    }

    public String logout(int userId) {
        invalidateAuthToken(userId);
        return "Logout successful";
    }

    private String generateAuthToken(User user) {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime expirationTime = currentTime.plusMinutes(30);

        Date issuedAt = Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant());
        Date expiration = Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant());

        Claims claims = Jwts.claims().setSubject(Integer.toString(user.getUserId()));
        claims.put("username", user.getUsername());
        claims.put("userType", user.getUserType());

        @SuppressWarnings("deprecation")
		String authToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();

        return authToken;
    }

    private void invalidateAuthToken(int userId) {

        // Your implementation here...
    }
}
