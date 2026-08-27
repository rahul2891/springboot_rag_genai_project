package main.java.devPliot.backend.services;

import org.springframework.stereotype.Service;
import main.java.devPliot.backend.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    public final UserRepository userRepository;
    public final TextEncryptor textEncryptor;

    @Transactional
    public User requiredById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public String decryptAccessToken(User user) {
        return textEncryptor.decrypt(user.getAccessToken());
    }

    private static Long toLong(Object value) {
        if(value instanceof Number number) {
            return number.longValue();
        } else if(value instanceof String str) {
            try {
                return Long.parseLong(str);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Cannot convert value to Long: " + value, e);
            }
        } else {
            throw new IllegalArgumentException("Cannot convert value to Long: " + value);   
        }
    }
}
