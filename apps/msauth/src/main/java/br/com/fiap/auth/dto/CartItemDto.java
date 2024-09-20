package br.com.fiap.auth.dto;

import java.util.UUID;

public record CartItemDto(
                UUID id,
                UUID productId,
                Long quantity,
                Double price,
                UUID cartId,
                String imageUrl,
                String name) {
}
