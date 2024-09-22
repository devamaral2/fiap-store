package br.com.fiap.mspayment.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record ProcessPaymentDto(
        @NotBlank(message = "O campo 'clientId' deve ser preenchido.")
        UUID clientId,
        @NotBlank(message = "O campo 'exprirationDate' deve ser preenchido.")
        String expirationDate,
        @NotBlank(message = "O campo 'name' deve ser preenchido.")
        String name,
        @NotBlank(message = "O campo 'cvv' deve ser preenchido.")
        String cvv,
        @NotBlank(message = "O campo 'cardNumber' deve ser preenchido.")
        String cardNumber,
        @NotBlank(message = "O campo 'value' deve ser preenchido.")
        String value,
        @NotBlank(message = "O campo 'installments' deve ser preenchido.")
        String installments
) {
}
