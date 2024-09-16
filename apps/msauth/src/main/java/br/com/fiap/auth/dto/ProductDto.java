package br.com.fiap.auth.dto;

import java.util.UUID;

public record ProductDto(
        UUID id,
        String name,
        String imageUrl,
        Long quantity,
        Double price) {
}
