package br.com.fiap.auth.service;

import br.com.fiap.auth.dto.RegisterResponse;
import br.com.fiap.auth.dto.UserRequest;
import br.com.fiap.auth.entity.User;
import br.com.fiap.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16]; // Define o tamanho do salt (16 bytes, por exemplo)
        random.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes); // Converte os bytes para uma string
    }

    public RegisterResponse register(UserRequest data) {
        if (this.repository.findByLogin(data.email()) != null) return null;

        String salt = generateSalt();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password() + salt);

        User newUser = User.builder()
                .name(data.name())
                .login(data.email())
                .password(encryptedPassword)
                .salt(salt)
                .build();

        User createdUser = this.repository.save(newUser);
        return new RegisterResponse(createdUser.getName(), createdUser.getLogin());

    }
}
