package br.com.fiap.auth.dto;

import br.com.fiap.auth.entity.CartItem;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UpdateCartDto(
        @NotNull(message = "A lista de CartItems deve estar na requisição")
        List<CartItem> cartItems
) {
}
