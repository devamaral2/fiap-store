package br.com.fiap.auth.controller;

import br.com.fiap.auth.entity.Product;
import br.com.fiap.auth.producer.products.ProductsListProducer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
@AllArgsConstructor
public class ProductController {

    private final ProductsListProducer productsListProducer;

    @GetMapping("/list")
    public List<Product> getAllProducts() {
        return productsListProducer.findAllProducts();
    }

}
