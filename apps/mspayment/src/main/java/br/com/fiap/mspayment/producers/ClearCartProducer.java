package br.com.fiap.mspayment.producers;

import br.com.fiap.mspayment.models.CartItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(value = "clear-carts", url = "http://localhost:8082")
public interface ClearCartProducer {

    @PatchMapping("carts/{id}/clear-cart")
    void clearCart(@PathVariable UUID id);
}
