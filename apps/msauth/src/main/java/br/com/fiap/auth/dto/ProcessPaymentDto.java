package br.com.fiap.auth.dto;

import java.util.UUID;

public record ProcessPaymentDto(
        UUID clientId,
        String cpf,
        String name,
        String paymentMethod,
        String cardNumber,
        String bankAccount
) {

}
