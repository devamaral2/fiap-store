package br.com.fiap.auth.producer.carts;

import br.com.fiap.auth.dto.CartItemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(value = "find-cart", url = "http://localhost:8003/carts")
public interface CartProducer {
    @GetMapping("/{id}/find")
    List<CartItemDto> findCart(@PathVariable() UUID id);
}
