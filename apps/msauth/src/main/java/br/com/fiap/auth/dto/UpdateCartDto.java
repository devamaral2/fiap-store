package br.com.fiap.auth.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record UpdateCartDto(
                @NotNull(message = "A lista de CartItems deve estar na requisição") List<CartItemDto> cartItems) {
}
