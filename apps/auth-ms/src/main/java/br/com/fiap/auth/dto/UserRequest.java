package br.com.fiap.auth.dto;

import br.com.fiap.auth.entity.enums.UserRole;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank(message = "çalksdaçkldsaçlkjdslçad")
        String name,
        String email,
        String password,
        UserRole role) {
}
