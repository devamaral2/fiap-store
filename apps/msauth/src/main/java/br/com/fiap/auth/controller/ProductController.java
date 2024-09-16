package br.com.fiap.auth.controller;

import br.com.fiap.auth.dto.CreateProductDto;
import br.com.fiap.auth.dto.ProductDto;
import br.com.fiap.auth.dto.UpdateProductDto;
import br.com.fiap.auth.producer.products.ProductCreateProducer;
import br.com.fiap.auth.producer.products.ProductListProducer;
import br.com.fiap.auth.producer.products.ProductUpdateProducer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("products")
@AllArgsConstructor
@Slf4j
public class ProductController {

    private final ProductListProducer productListProducer;
    private final ProductCreateProducer productCreateProducer;
    private final ProductUpdateProducer productUpdateProducer;

    @GetMapping("/list")
    public List<ProductDto> getAllProducts(HttpServletRequest request) {
        return productListProducer.findAllProducts();
    }

    @PostMapping("/add-product")
    public ProductDto createProduct(@RequestBody @Valid CreateProductDto body) {
        return productCreateProducer.create(body);

    }

    @PatchMapping("/{id}")
    public void updateProduct(@PathVariable UUID id, @RequestBody @Valid UpdateProductDto body) {
        productUpdateProducer.update(id, body);
    }

}
