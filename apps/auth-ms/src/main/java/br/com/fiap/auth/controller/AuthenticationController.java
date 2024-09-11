package br.com.fiap.auth.controller;

import br.com.fiap.auth.controller.exceptions.StandardError;
import br.com.fiap.auth.dto.RegisterResponse;
import br.com.fiap.auth.dto.UserAuthRequest;
import br.com.fiap.auth.dto.UserRequest;
import br.com.fiap.auth.dto.UserResponse;
import br.com.fiap.auth.entity.User;
import br.com.fiap.auth.repository.UserRepository;
import br.com.fiap.auth.security.TokenService;
import br.com.fiap.auth.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
@Slf4j
public class AuthenticationController {


    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;
    private final TokenService tokenService;
    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserAuthRequest data) {
        UserDetails userDetails = repository.findByLogin(data.email());
        User user = (User) userDetails;
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password() + user.getSalt());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new UserResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserRequest data) throws BadRequestException {
        try {

            RegisterResponse response = this.userService.register(data);
            if (response == null) {
                return ResponseEntity.badRequest().body(
                        StandardError.builder()
                                .status(HttpStatus.BAD_REQUEST.value())
                                .message("Este email já está cadastrado no nosso banco de dados")
                                .path("/register")
                );
            }
            return ResponseEntity.ok(response);
        } catch (Exception error) {
            log.info("klajsdlkjasdlkjadklfjkaçsflçjasçkfljafçlsfalçkjfsalkfjslçsfaklçjs");
            return ResponseEntity.badRequest().body(
                    StandardError.builder()
                            .status(HttpStatus.BAD_REQUEST.value())
                            .message(error.getMessage())
                            .path("/register")
            );
        }
    }
}
