package com.fiap.msproduct.dto;

import java.util.UUID;

public record ProductDto(
        UUID id,
        String name,
        String imageUrl,
        Long quantity,
        Double price,
        String category,
        String description
) {
}
