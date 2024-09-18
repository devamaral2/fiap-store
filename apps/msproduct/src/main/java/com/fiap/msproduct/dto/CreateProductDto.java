package com.fiap.msproduct.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProductDto(
        @NotBlank(message = "O campo 'name' deve ser preenchido.")
        String name,
        @NotBlank(message = "O campo 'imageUrl' deve ser preenchido.")
        String imageUrl,
        @NotNull(message = "O campo 'price' deve ser preenchido.")
        Double price,
        @NotNull(message = "O campo 'quantity' deve ser preenchido.")
        Long quantity,
        @NotNull(message = "O campo 'category' deve ser preenchido.")
        String category,
        @NotNull(message = "O campo 'description' deve ser preenchido.")
        String description
) {
}
