package br.com.fiap.auth.producer.payments;

import br.com.fiap.auth.dto.ProcessPaymentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "process-payment", url = "http://localhost:8004/payments")
public interface ProcessPaymentProducer {

    @PostMapping("/process-payment")
    void process(ProcessPaymentDto dto);
}
