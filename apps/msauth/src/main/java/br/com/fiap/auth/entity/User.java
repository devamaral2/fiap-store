package br.com.fiap.auth.entity;

import br.com.fiap.auth.entity.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

@Table(name = "users")
@Entity(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Slf4j
public class User implements UserDetails {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"
    );
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$"
    );

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String login;
    private String password;
    private UserRole role;
    private String salt;
    private String name;

    public static User builder() {
        return new User();
    }

    public User build() {
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Setter with validation for ID
    public User id(Object id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID não pode estar vazio.");
        }
        if (!(id instanceof String)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID deve ser uma string.");
        }
        this.id = (String) id;
        return this;
    }

    // Setter with validation for login (email format)
    public User login(Object login) {
        if (login == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login não pode estar vazio.");
        }
        if (!(login instanceof String)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login deve ser uma string.");
        }
        if (!EMAIL_PATTERN.matcher((String) login).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de email inválido.");
        }
        this.login = (String) login;
        return this;
    }

    // Setter com validação de senha usando regex
    public User password(Object password) {
        log.info((String) password);
        if (password == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha não pode estar vazia.");
        }
        if (!(password instanceof String passwordStr)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha deve ser uma string.");
        }
        if (!PASSWORD_PATTERN.matcher(passwordStr).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Senha deve ter pelo menos 8 caracteres, incluindo uma letra maiúscula, um número e um caractere especial.");
        }
        this.password = passwordStr;
        return this;
    }

    // Setter with validation for role (must not be null)
    public User role(Object role) {
        if (role == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Função do usuário não pode estar vazia.");
        }
        if (!(role instanceof UserRole)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Função do usuário deve ser do tipo UserRole.");
        }
        this.role = (UserRole) role;
        return this;
    }

    // Setter with validation for salt (must not be null or empty)
    public User salt(Object salt) {
        if (salt == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Salt não pode estar vazio.");
        }
        if (!(salt instanceof String)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Salt deve ser uma string.");
        }
        this.salt = (String) salt;
        return this;
    }

    // Setter with validation for name (must not be null or empty)
    public User name(Object name) {
        if (name == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome não pode estar vazio.");
        }
        if (!(name instanceof String)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome deve ser uma string.");
        }
        this.name = (String) name;
        return this;
    }
}