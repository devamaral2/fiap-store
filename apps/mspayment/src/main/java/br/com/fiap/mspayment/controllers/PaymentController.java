package br.com.fiap.mspayment.controllers;
import br.com.fiap.mspayment.controllers.exceptions.ValidationTrigger;
import br.com.fiap.mspayment.dto.ProcessPaymentDto;
import br.com.fiap.mspayment.services.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("payments")
@AllArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;


    @PostMapping("/process-payment")
    public void create(
            @RequestBody ProcessPaymentDto dto,
            BindingResult bindingResult) {
        new ValidationTrigger(bindingResult).verify();
        this.paymentService.processPayment(dto);
    }

}
