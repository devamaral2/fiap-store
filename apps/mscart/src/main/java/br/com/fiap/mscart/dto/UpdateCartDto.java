package br.com.fiap.mscart.dto;

import br.com.fiap.mscart.models.CartItem;

import java.util.List;
import java.util.UUID;

public record UpdateCartDto(
        UUID clienteId,
        List<CartItem> cartItems
) {
}
