package br.com.fiap.auth.dto;

import br.com.fiap.auth.entity.enums.UserRole;

public record UserDTO(String id, String name, String login, UserRole role) {}


