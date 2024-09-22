package br.com.fiap.auth.dto;

public record UpdateProductDto(
        String imageUrl,
        Double price,
        String description,
        Long quantity
) {
}
