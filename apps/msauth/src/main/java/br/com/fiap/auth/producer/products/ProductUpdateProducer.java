package br.com.fiap.auth.producer.products;

import br.com.fiap.auth.dto.UpdateProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(value = "product-update", url = "http://localhost:8002/products")
public interface ProductUpdateProducer {

    @PatchMapping(value = "/{id}")
    void update(@PathVariable() UUID id, UpdateProductDto dto);
}
