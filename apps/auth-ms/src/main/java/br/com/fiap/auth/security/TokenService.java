package br.com.fiap.auth.security;

import br.com.fiap.auth.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
@Slf4j
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    /**
     * Gera um token JWT com base no algoritimo HMAC256 para o usuário escolhido.
     *
     * @Param {Person} person o usuário para o qual o jwt será criado
     * @Returns {JWT} o jwt gerado pela rota de login
     **/
    public String generateToken(User user) {
        try {
            log.info(secret);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getLogin())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    /**
     * Verifica se o token escolhido já está espirado ou se ele é um token válido para o usuário escolhido
     *
     * @Param {String} O token JWT
     * @Returns {String} Retorna o subject do JWT
     **/
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    /**
     * Cria um LocalDateTime que é duas horas da data atual
     *
     * @Returns {LocalDateTime} A data de expiração do token JWT
     **/
    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
