package br.com.fiap.auth.producer.products;

import br.com.fiap.auth.dto.CreateProductDto;
import br.com.fiap.auth.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "product-create", url = "http://localhost:8081/products")
public interface ProductCreateProducer {

    @PostMapping("/add-product")
    ProductDto create(CreateProductDto dto);

}
