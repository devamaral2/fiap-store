package br.com.fiap.mspayment.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fiap.mspayment.dto.CartItemDto;
import br.com.fiap.mspayment.dto.ProcessPaymentDto;
import br.com.fiap.mspayment.dto.SoldProductDto;
import br.com.fiap.mspayment.models.Payment;
import br.com.fiap.mspayment.producers.CartProducer;
import br.com.fiap.mspayment.producers.ClearCartProducer;
import br.com.fiap.mspayment.producers.ProductProducer;
import br.com.fiap.mspayment.repositories.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class PaymentService {

        private final CartProducer cartProducer;
        private final ProductProducer productProducer;
        private final ProcessPaymentService processPaymentService;
        private final PaymentRepository paymentRepository;
        private final ClearCartProducer clearCartProducer;

        public void processPayment(ProcessPaymentDto dto) {
                List<CartItemDto> items = cartProducer.getCart(dto.clientId());

                Double paymentValue = items.stream()
                                .mapToDouble(CartItemDto::price)
                                .sum();
                Payment payment = Payment.builder()
                                .clientId(dto.clientId())
                                .cpf(dto.cpf())
                                .name(dto.name())
                                .paymentMethod(dto.paymentMethod())
                                .cardNumber(dto.cardNumber())
                                .bankAccount(dto.bankAccount())
                                .value(paymentValue)
                                .build();

                processPaymentService.exec(payment);

                List<SoldProductDto> soldProductDto = items.stream()
                                .map(i -> new SoldProductDto(i.productId(), i.quantity()))
                                .toList();

                productProducer.removeProduct(soldProductDto);
                clearCartProducer.clearCart(dto.clientId());

                paymentRepository.save(payment);
        }

}
