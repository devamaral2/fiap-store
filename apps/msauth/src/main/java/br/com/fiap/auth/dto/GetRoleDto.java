package br.com.fiap.auth.dto;

import br.com.fiap.auth.entity.enums.UserRole;

public record GetRoleDto(UserRole role) {
}
