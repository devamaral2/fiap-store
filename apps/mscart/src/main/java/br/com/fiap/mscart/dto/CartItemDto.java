package br.com.fiap.mscart.dto;

import java.util.UUID;

public record CartItemDto(
        UUID id,
        UUID productId,
        Long quantity,
        Double price,
        String imageUrl,
        String name,
        UUID cartId
) {
}
