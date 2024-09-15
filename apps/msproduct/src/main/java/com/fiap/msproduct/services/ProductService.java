package com.fiap.msproduct.services;
import com.fiap.msproduct.controllers.exceptions.BadRequestException;
import com.fiap.msproduct.controllers.exceptions.ControllerNotFoundException;
import com.fiap.msproduct.models.Product;
import com.fiap.msproduct.models.SoldProduct;
import com.fiap.msproduct.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(UUID id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ControllerNotFoundException("Não existe este produto na nossa base de dados");
        }
        return  optionalProduct.get();
    }

    public Product create(String name, String imageUrl, Long quantity, Long price) {
        Product product = Product
                .builder()
                .name(name)
                .imageUrl(imageUrl)
                .price(price)
                .quantity(quantity)
                .build();
        return productRepository.save(product);
    }

    public Product update(UUID uuid, String name, String imageUrl, Long quantity, Long price) {
        Optional<Product> optionalProduct = productRepository.findById(uuid);
        if (optionalProduct.isEmpty()) {
            throw new ControllerNotFoundException("Não existe este produto na nossa base de dados");
        }
        String newName = optionalProduct.get().getName();
        String newImageUrl = optionalProduct.get().getImageUrl();
        Long newQuantity = optionalProduct.get().getQuantity();
        Long newPrice = optionalProduct.get().getPrice();
        if (name != null) {
            newName = name;
        }
        if (imageUrl != null) {
            newImageUrl = imageUrl;
        }
        if (quantity != null) {
            newQuantity = quantity;
        }
        if (price != null) {
            newPrice = price;
        }
        Product product = Product
                .builder()
                .name(newName)
                .imageUrl(newImageUrl)
                .quantity(newQuantity)
                .price(newPrice)
                .id(optionalProduct.get().getId())
                .build();
        return productRepository.save(product);
    }

    public void removeProduct(List<SoldProduct> soldProduct) {
        soldProduct.forEach(p -> {
        Optional<Product> optionalProduct = productRepository.findById(p.getProductId());
        if (optionalProduct.isEmpty()) {
            throw new ControllerNotFoundException("Não existe este produto na nossa base de dados: " + p.getProductId());
        }
        if (optionalProduct.get().getQuantity() < p.getQuantity()) {
            throw new BadRequestException("O produto " + optionalProduct.get().getName() + " não tem quantidade o suficiente no estoque");
        }
        Product produto = Product
                .builder()
                .id(optionalProduct.get().getId())
                .name(optionalProduct.get().getName())
                .imageUrl(optionalProduct.get().getImageUrl())
                .price(optionalProduct.get().getPrice())
                .quantity(optionalProduct.get().getQuantity() - p.getQuantity())
                .build();
            productRepository.save(produto);
        });
    }


}
