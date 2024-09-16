package br.com.fiap.auth.producer.products;

import br.com.fiap.auth.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "products-list", url = "http://localhost:8081/products")
public interface ProductListProducer {

    @GetMapping(value = "/list")
    List<ProductDto> findAllProducts();

}
