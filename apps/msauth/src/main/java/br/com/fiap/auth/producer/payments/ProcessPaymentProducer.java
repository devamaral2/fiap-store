package br.com.fiap.auth.producer.payments;

import br.com.fiap.auth.dto.ProcessPaymentDto;
import br.com.fiap.auth.dto.ProcessPaymentResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "process-payment", url = "http://localhost:8083/payments")
public interface ProcessPaymentProducer {

    @PostMapping("/process-payment")
    ProcessPaymentResponseDto process(ProcessPaymentDto dto);
}
