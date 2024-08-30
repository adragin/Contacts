package com.demo.contacts.services.auth;


import com.demo.contacts.models.User;
import com.demo.contacts.repository.user.UserRepositoryJPA;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Base64;

@Service
public class AuthService {

    private final UserRepositoryJPA userRepository;

    public AuthService(UserRepositoryJPA userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(String email, String password, String name) throws SQLException {
        User user = new User();
        user.setEmail(email);
        user.setPassword(hashPassword(password));
        user.setName(name);
        userRepository.save(user);
    }

    public String authenticate(String email, String password) throws SQLException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            if (user.getPassword().equals(hashPassword(password))) {
                return generateToken(email, password);
            }
        }
        return null;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    private String generateToken(String email, String password) {
        return "001" + Base64.getEncoder().encodeToString((email + "|" + password).getBytes());
    }

    public Integer getUserIdFromToken(String token) {
        String decodedToken = new String(Base64.getDecoder().decode(token.substring(3)));
        String email = decodedToken.split("\\|")[0];

        User optionalUser = userRepository.findByEmail(email);
        return optionalUser.getId();
    }
}
