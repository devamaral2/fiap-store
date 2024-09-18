package br.com.fiap.auth.controller;

import br.com.fiap.auth.dto.UserAuthRequest;
import br.com.fiap.auth.dto.UserAuthResponse;
import br.com.fiap.auth.dto.UserRequest;
import br.com.fiap.auth.service.LoginService;
import br.com.fiap.auth.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public void register(@RequestBody UserRequest data) {
        try {
            this.userService.register(data);
        } catch (Exception e) {
            log.info(data.toString());
            log.error(e.toString());
        }
    }
}
