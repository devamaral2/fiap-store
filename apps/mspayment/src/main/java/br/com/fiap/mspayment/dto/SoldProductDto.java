package br.com.fiap.mspayment.dto;

import lombok.*;

import java.util.UUID;


public record SoldProductDto(
    UUID productId,
    Long quantity

) {
}
