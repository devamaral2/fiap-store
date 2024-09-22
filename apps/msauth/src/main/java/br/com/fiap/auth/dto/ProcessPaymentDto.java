package br.com.fiap.auth.dto;

import java.util.UUID;

public record ProcessPaymentDto(
        UUID clientId,
        String expirationDate,
        String name,
        String cvv,
        String cardNumber,
        String value,
        String installments
) {
}
