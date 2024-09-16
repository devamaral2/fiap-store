package br.com.fiap.auth.dto;

public record ProcessPaymentBodyDto(
        String cpf,
        String name,
        String paymentMethod,
        String cardNumber,
        String bankAccount
) {
}
