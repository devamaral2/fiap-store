package com.fiap.msproduct.controllers;
import com.fiap.msproduct.controllers.exceptions.ValidationTrigger;
import com.fiap.msproduct.dto.CreateProductDto;
import com.fiap.msproduct.dto.ProductDto;
import com.fiap.msproduct.dto.UpdateProductDto;
import com.fiap.msproduct.models.Product;
import com.fiap.msproduct.dto.SoldProductDto;
import com.fiap.msproduct.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("products")
@AllArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    @GetMapping("/list")
    public List<ProductDto> findAll() {
        List<Product> products = this.productService.findAll();
        return products.stream().map(p -> new ProductDto(
                p.getId(),
                p.getName(),
                p.getImageUrl(),
                p.getQuantity(),
                p.getPrice()
        )).toList();
    }

    @PostMapping("/add-product")
    public ProductDto addProduct(
            @RequestBody @Valid CreateProductDto dto,
            BindingResult bindingResult) {
        new ValidationTrigger(bindingResult).verify();
        Product product = this.productService.create(dto.name(), dto.imageUrl(), dto.quantity(), dto.price());
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getImageUrl(),
                product.getQuantity(),
                product.getPrice()
                );
    }

    @PatchMapping("/{id}")
    public void update(
            @PathVariable UUID id,
            @RequestBody UpdateProductDto dto,
            BindingResult bindingResult) {
        new ValidationTrigger(bindingResult).verify();
        this.productService.update(id, dto.name(), dto.imageUrl(), dto.quantity(), dto.price());
    }

    @GetMapping("/{id}")
    public ProductDto getOneProduct(
            @PathVariable UUID id) {
        Product product =  this.productService.findById(id);
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getImageUrl(),
                product.getQuantity(),
                product.getPrice()
        );
    }

    @PatchMapping("/remove-product")
    public void removeProduct(
            @RequestBody List<SoldProductDto> body) {
        this.productService.removeProduct(body);
    }
}
