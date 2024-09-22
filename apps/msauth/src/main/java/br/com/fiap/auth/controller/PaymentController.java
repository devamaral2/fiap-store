package br.com.fiap.auth.controller;

import br.com.fiap.auth.dto.ProcessPaymentDto;
import br.com.fiap.auth.entity.User;
import br.com.fiap.auth.producer.payments.ProcessPaymentProducer;
import br.com.fiap.auth.security.SecurityFilter;
import jakarta.servlet.http.HttpServletRequest;
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
    public void processPayment(HttpServletRequest request, @RequestBody ProcessPaymentDto dto) {
        User user = securityFilter.getUserByToken(request);
        UUID uuid = UUID.fromString(user.getId());
        ProcessPaymentDto processPaymentDto = new ProcessPaymentDto(
                uuid,
                dto.expirationDate(),
                dto.name(),
                dto.cvv(),
                dto.cardNumber(),
                dto.value(),
                dto.installments()
        );


        processPaymentProducer.process(processPaymentDto);
    }

}
