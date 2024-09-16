package br.com.fiap.auth.controller;

import br.com.fiap.auth.dto.ProcessPaymentDto;
import br.com.fiap.auth.dto.ProcessPaymentResponseDto;
import br.com.fiap.auth.producer.payments.ProcessPaymentProducer;
import br.com.fiap.auth.security.SecurityFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("payments")
@AllArgsConstructor
@Slf4j
public class PaymentController {

    private final ProcessPaymentProducer processPaymentProducer;
    private final SecurityFilter securityFilter;

    @PostMapping("/process-payment")
    public ProcessPaymentResponseDto processPayment(HttpServletRequest request, @RequestBody @Valid ProcessPaymentDto dto) {
        String id = securityFilter.getById(request);
        UUID uuid = UUID.fromString(id);
        ProcessPaymentDto processPaymentDto = new ProcessPaymentDto(
                uuid,
                dto.cpf(),
                dto.name(),
                dto.paymentMethod(),
                dto.cardNumber(),
                dto.bankAccount());


        return processPaymentProducer.process(processPaymentDto);
    }

}
