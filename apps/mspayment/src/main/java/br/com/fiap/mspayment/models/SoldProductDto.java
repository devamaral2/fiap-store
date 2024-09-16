package br.com.fiap.mspayment.models;

import lombok.*;

import java.util.UUID;


public record SoldProductDto(
    UUID productId,
    Long quantity

) {
}
