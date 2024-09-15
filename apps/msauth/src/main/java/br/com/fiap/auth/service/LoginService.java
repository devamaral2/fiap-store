package br.com.fiap.auth.service;

import br.com.fiap.auth.dto.UserAuthRequest;
import br.com.fiap.auth.dto.UserAuthResponse;
import br.com.fiap.auth.entity.User;
import br.com.fiap.auth.repository.UserRepository;
import br.com.fiap.auth.security.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public UserAuthResponse login(UserAuthRequest login) {
        UserDetails userDetails = repository.findByLogin(login.email());
        User user = (User) userDetails;
        var usernamePassword = new UsernamePasswordAuthenticationToken(login.email(), login.password() + user.getSalt());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return new UserAuthResponse(token);
    }
}
