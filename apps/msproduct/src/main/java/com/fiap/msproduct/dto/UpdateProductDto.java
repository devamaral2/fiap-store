package com.fiap.msproduct.dto;


public record UpdateProductDto(
        String name,
        String imageUrl,
        Double price,
        Long quantity,
        String description
) {
}
