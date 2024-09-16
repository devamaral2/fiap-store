package br.com.fiap.mspayment.producers;

import br.com.fiap.mspayment.models.SoldProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;

@FeignClient(value = "products", url = "http://localhost:8081")
public interface ProductProducer {
    @PatchMapping("products/remove-product")
    void removeProduct(List<SoldProductDto> soldProductDto);
}
