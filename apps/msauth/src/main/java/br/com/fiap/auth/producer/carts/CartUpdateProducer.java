package br.com.fiap.auth.producer.carts;

import br.com.fiap.auth.dto.UpdateCartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(value = "cart-update", url = "http://localhost:8082/carts")
public interface CartUpdateProducer {
    @PatchMapping("/{id}/update-cart")
    void update(@PathVariable() UUID id, UpdateCartDto dto);
}
