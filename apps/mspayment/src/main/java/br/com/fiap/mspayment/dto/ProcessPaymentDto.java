package br.com.fiap.mspayment.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ProcessPaymentDto(
        @NotBlank(message = "O campo 'clientId' deve ser preenchido.")
        UUID clientId,
        @NotBlank(message = "O campo 'cpf' deve ser preenchido.")
        String cpf,
        @NotBlank(message = "O campo 'name' deve ser preenchido.")
        String name,
        @NotBlank(message = "O campo 'paymentMethod' deve ser preenchido.")
        String paymentMethod,
        @NotBlank(message = "O campo 'cardNumber' deve ser preenchido.")
        String cardNumber,
        @NotBlank(message = "O campo 'bankAccount' deve ser preenchido.")
        String bankAccount
) {
}
