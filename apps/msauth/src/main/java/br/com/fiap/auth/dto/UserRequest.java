package br.com.fiap.auth.dto;

import br.com.fiap.auth.entity.enums.UserRole;

public record UserRequest(
        String name,
        String email,
        String password,
        UserRole role) {
}
