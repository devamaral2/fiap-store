package br.com.fiap.mscart.dto;


import jakarta.validation.constraints.NotNull;
import java.util.List;


public record UpdateCartDto(
        @NotNull(message = "A lista de CartItems deve estar na requisição")
        List<CartItemDto> cartItems
) {
}
