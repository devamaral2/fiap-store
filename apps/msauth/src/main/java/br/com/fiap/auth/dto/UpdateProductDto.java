package br.com.fiap.auth.dto;

public record UpdateProductDto(
        String name,
        String imageUrl,
        Double price,
        Long quantity
) {
}
