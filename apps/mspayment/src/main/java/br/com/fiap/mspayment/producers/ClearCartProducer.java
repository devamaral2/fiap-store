package br.com.fiap.mspayment.producers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.UUID;

@FeignClient(value = "clear-carts", url = "http://cart-ms:8003")
public interface ClearCartProducer {

    @PatchMapping("carts/{id}/clear-cart")
    void clearCart(@PathVariable UUID id);
}
