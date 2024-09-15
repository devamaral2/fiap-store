package com.fiap.msproduct.controllers;
import com.fiap.msproduct.controllers.exceptions.ValidationTrigger;
import com.fiap.msproduct.dto.CreateProductDto;
import com.fiap.msproduct.dto.UpdateProdutDto;
import com.fiap.msproduct.models.Product;
import com.fiap.msproduct.models.SoldProduct;
import com.fiap.msproduct.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("list")
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.productService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Product> create(
            @RequestBody @Valid CreateProductDto dto,
            BindingResult bindingResult) {
        new ValidationTrigger(bindingResult).verify();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.productService.create(dto.name(), dto.imageUrl(), dto.quantity(), dto.price()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> update(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateProdutDto dto,
            BindingResult bindingResult) {
        new ValidationTrigger(bindingResult).verify();
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.productService.update(id, dto.name(), dto.imageUrl(), dto.quantity(), dto.price()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> update(
            @PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.productService.findById(id));
    }

    @PatchMapping("remove-product")
    public ResponseEntity<List<SoldProduct>> update(
            @RequestBody List<SoldProduct> body) {
        this.productService.removeProduct(body);
        return ResponseEntity.status(HttpStatus.OK)
                .body(body);
    }
}
