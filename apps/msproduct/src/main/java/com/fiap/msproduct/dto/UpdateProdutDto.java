package com.fiap.msproduct.dto;

public record UpdateProdutDto(
        String name,
        String imageUrl,
        Long quantity,
        Long price
) {
}
