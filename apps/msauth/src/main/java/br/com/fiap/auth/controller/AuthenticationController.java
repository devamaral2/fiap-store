package br.com.fiap.auth.controller;

import br.com.fiap.auth.controller.exceptions.StandardError;
import br.com.fiap.auth.dto.RegisterResponse;
import br.com.fiap.auth.dto.UserAuthRequest;
import br.com.fiap.auth.dto.UserAuthResponse;
import br.com.fiap.auth.dto.UserRequest;
import br.com.fiap.auth.service.LoginService;
import br.com.fiap.auth.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
@Slf4j
public class AuthenticationController {


    private final LoginService loginService;
    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<UserAuthResponse> login(@RequestBody @Valid UserAuthRequest data) {
        UserAuthResponse userAuthResponse = this.loginService.login(data);

        return ResponseEntity.ok(userAuthResponse);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserRequest data) {
        try {

            RegisterResponse response = this.userService.register(data);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        StandardError.builder()
                                .status(HttpStatus.BAD_REQUEST.value())
                                .message("Este email já está cadastrado no nosso banco de dados")
                                .path("/register")
                );
            }
            return ResponseEntity.ok(response);
        } catch (Exception error) {
            return ResponseEntity.badRequest().body(
                    StandardError.builder()
                            .status(HttpStatus.BAD_REQUEST.value())
                            .message(error.getMessage())
                            .path("/register")
            );
        }
    }
}
