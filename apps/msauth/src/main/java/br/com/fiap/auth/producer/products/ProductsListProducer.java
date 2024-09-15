package br.com.fiap.auth.producer.products;

import br.com.fiap.auth.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "products", url = "http://localhost:8081")
public interface ProductsListProducer {

    @GetMapping(value = "products/list")
    List<Product> findAllProducts();
}
